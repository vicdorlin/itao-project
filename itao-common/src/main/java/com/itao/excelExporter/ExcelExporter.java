package com.itao.excelExporter;

import static com.itao.exception.CommonCode.NO_LIST_HERE;
import static com.itao.exception.CommonCode.NO_SUCH_TYPE;
import static com.itao.util.CommonUtils.*;

import com.itao.exception.BusinessException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Excel导出体系：
 * Excel数据文件导出功能类
 * （如有特殊需求，在子类中重写本类方法）
 *
 * @author Created by Vicdor(linss) on 2016-05-20 00:31.
 */
public class ExcelExporter<T> {
    /**
     * 对外的导出方法
     * 根据传入的 @param prams 中fileSuffixValue确定导出类型
     *
     * @param dataMap  dataMap为T类中不包含的字段，若无这样的字段则传null
     * @param response
     */
    public void export(ExcelExporterParams<T> prams, Map<String, List<String>> dataMap, HttpServletResponse response) {
        Integer fileSuffixValue = prams.getFileSuffixValue();
        if (fileSuffixValue == null || fileSuffixValue == ExcelExporterConst.FILE_NAME_SUFFIX.XLS.getValue()) {
            exportXls(prams, dataMap, response);
        } else if (fileSuffixValue == ExcelExporterConst.FILE_NAME_SUFFIX.XLSX.getValue()) {
            exportXlsx(prams, dataMap, response);
        } else {
            throw new BusinessException(NO_SUCH_TYPE);
        }
    }

    /**
     * 导出Xls格式Excel
     *
     * @param params
     */
    protected void exportXls(ExcelExporterParams<T> params, Map<String, List<String>> dataMap, HttpServletResponse response) {
        Collection<T> dataSet = params.getDataSet();
        if (isSetEmpty(dataSet)) {
            throw new BusinessException(NO_LIST_HERE);
        }

        String defaultDatePattern = params.getDefaultDatePattern();
        Integer defaultScale = params.getDefaultScale();
        String sheetName = ExcelExporterConst.DEFAULT_SHEEET_NAME;

        String[] headers = params.getHeaders();
        String[] fieldNames = params.getFieldNames();
        Map<String, String> formatConfigMap = params.getFormatConfigMap();
        String fileName = params.getFileName();
        Integer fileSuffixValue = params.getFileSuffixValue();

        fileName += "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + ExcelExporterConst.FILE_NAME_SUFFIX.XLS.getText();

        int dataSize = dataSet.size();
        int sheetFlag = dataSize >> 16;// 65536 = 2^16
        int sheetCount = 1;
        if (sheetFlag > 0) {
            sheetCount = sheetFlag + 1;//总页数
        }
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 生成一个表头样式
        HSSFCellStyle headStyle = workbook.createCellStyle();
        // 设置这些样式
        headStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont headFont = workbook.createFont();
        /*headFont.setColor(HSSFColor.BLACK.index);*/
        headFont.setFontHeightInPoints((short) 11);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);

        // 生成并设置一个内容样式
        HSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        bodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        bodyStyle.setFont(bodyFont);

        for (int page = 1; page <= sheetCount; page++) {
            HSSFSheet sheet = workbook.createSheet(sheetName + page);
            sheet.setDefaultColumnWidth(20);
            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(headStyle);
                cell.setCellValue(new HSSFRichTextString(headers[i]));
            }

            // 遍历集合数据，产生数据行
            Iterator<T> car = dataSet.iterator();
            int index = 0, rowIndex = 0;
            int totalRow = 65536;
            int headRow = 1;/**暂时只考虑表头只有一行的情况**/
            int rowFlag = 0;
            while (car.hasNext() && rowIndex < totalRow - headRow) {
                rowIndex++;
                row = sheet.createRow(++index);
                T t = car.next();
                int flag = 0;
                for (int i = 0; i < fieldNames.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(bodyStyle);
                    try {
                        t.getClass().getDeclaredField(fieldNames[i]);
                    } catch (NoSuchFieldException e) {
                        fieldNames[i] = "" + flag++;
                    }

                    fillInCells(rowFlag, cell, t, fieldNames[i], fileSuffixValue, defaultDatePattern, defaultScale, formatConfigMap, dataMap);
                }
                rowFlag++;
                car.remove();//写完一行,删一行
            }
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);//自适应列宽
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 20);//列宽稍稍加长
            }
        }
        generateExcel(workbook, fileName, response);
    }

    /**
     * 导出Xlsx格式Excel
     *
     * @param params
     */
    protected void exportXlsx(ExcelExporterParams<T> params, Map<String, List<String>> dataMap, HttpServletResponse response) {
        Collection<T> dataSet = params.getDataSet();
        if (isSetEmpty(dataSet)) {
            throw new BusinessException(NO_LIST_HERE);
        }

        String defaultDatePattern = params.getDefaultDatePattern();
        Integer defaultScale = params.getDefaultScale();
        String sheetName = ExcelExporterConst.DEFAULT_SHEEET_NAME;

        String[] headers = params.getHeaders();
        String[] fieldNames = params.getFieldNames();
        Map<String, String> formatConfigMap = params.getFormatConfigMap();
        String fileName = params.getFileName();
        Integer fileSuffixValue = params.getFileSuffixValue();

        fileName += "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + ExcelExporterConst.FILE_NAME_SUFFIX.XLSX.getText();

        int dataSize = dataSet.size();
        int sheetFlag = dataSize >> 16;// 65536 = 2^16
        int sheetCount = 1;
        if (sheetFlag > 0) {
            sheetCount = sheetFlag + 1;//总页数
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表头样式
        XSSFCellStyle headStyle = workbook.createCellStyle();
        // 设置这些样式
        headStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont headFont = workbook.createFont();
        /*headFont.setColor(HSSFColor.BLACK.index);*/
        headFont.setFontHeightInPoints((short) 11);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);

        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        bodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        XSSFFont bodyFont = workbook.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        bodyStyle.setFont(bodyFont);

        for (int page = 1; page <= sheetCount; page++) {
            XSSFSheet sheet = workbook.createSheet(sheetName + page);
            sheet.setDefaultColumnWidth(20);
            // 产生表格标题行
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(headStyle);
                cell.setCellValue(new XSSFRichTextString(headers[i]));
            }
            // 遍历集合数据，产生数据行
            Iterator<T> car = dataSet.iterator();
            int index = 0, rowIndex = 0;
            int totalRow = 65536;
            int headRow = 1;/**暂时只考虑表头只有一行的情况**/
            int rowFlag = 0;
            while (car.hasNext() && rowIndex < totalRow - headRow) {
                rowIndex++;
                row = sheet.createRow(++index);
                T t = car.next();
                int flag = 0;
                for (int i = 0; i < fieldNames.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellStyle(bodyStyle);

                    try {
                        t.getClass().getDeclaredField(fieldNames[i]);
                    } catch (NoSuchFieldException e) {
                        fieldNames[i] = "" + flag++;
                    }

                    fillInCells(rowFlag, cell, t, fieldNames[i], fileSuffixValue, defaultDatePattern, defaultScale, formatConfigMap, dataMap);
                }
                rowFlag++;
                car.remove();//写完一行,删一行
            }
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);//自适应列宽
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 20);//列宽稍稍加长
            }
        }
        generateExcel(workbook, fileName, response);
    }

    /**
     * 将数据填入单元格，生成内容
     * 抽取方法，用以处理自定义额外字段，生成T中不存在的数据
     * <p>
     * 当需要添加额外字段的时候（在集合对象类中不存在对应的字段）
     * 可以在子类中重写此方法
     * 将该字段数据按顺序置入一个List<String>中
     * 再借助dataMap传入此方法
     * 在此处处理即可
     *
     * @param rowFlag            表示当前处理行数-1;不可省却，虽然在此处并未用上，然在子类重写后的方法中，结合dataMap或许就有其用武之地了
     * @param cell               当前处理单元格
     * @param t                  当前处理对象
     * @param fieldName          字段名
     * @param fileSuffixValue    文件后缀枚举值
     * @param defaultDatePattern 默认时间格式
     * @param defaultScale       默认浮点数精度
     * @param dataMap            人工附加数据载体
     */
    protected void fillInCells(int rowFlag, Cell cell, T t, String fieldName, Integer fileSuffixValue, String defaultDatePattern, Integer defaultScale, Map<String, String> formatConfigMap, Map<String, List<String>> dataMap) {
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            String textValue = formatCellTextValue(t, fieldName, defaultDatePattern, formatConfigMap, getMethodName, dataMap);
            writeToCell(cell, fieldName, fileSuffixValue, defaultScale, formatConfigMap, textValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果Excel的列，在类T中存在对应的字段
     * 但，要得到的数据却非来源原貌，比如，m显示为男性，f显示为女性
     * 重写此方法，根据 fieldName 以处理之
     *
     * @param t
     * @param fieldName
     * @param defaultDatePattern
     * @param formatConfigMap
     * @param getMethodName
     * @param dataMap
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected String formatCellTextValue(T t, String fieldName, String defaultDatePattern, Map<String, String> formatConfigMap, String getMethodName, Map<String, List<String>> dataMap) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String textValue;

        Class tCls = t.getClass();
        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
        Object value = getMethod.invoke(t, new Object[]{});
        if (value == null) {
            textValue = "";
        } else if (value instanceof Date) {
            textValue = formatDateValue(fieldName, defaultDatePattern, formatConfigMap, (Date) value);
        } else {
            // 其它数据类型都当作字符串简单处理
            textValue = value.toString();
        }
        return textValue;
    }

    /**
     * 处理日期格式
     *
     * @param fieldName
     * @param defaultDatePattern
     * @param formatConfigMap
     * @param value
     * @return
     */
    protected String formatDateValue(String fieldName, String defaultDatePattern, Map<String, String> formatConfigMap, Date value) {
        String pattern = null;
        if (formatConfigMap != null) {
            pattern = formatConfigMap.get(fieldName + ExcelExporterConst.MAP_CONTENT_SIGN.DATE_PATTERN.getText());
        }

        return new SimpleDateFormat(pattern != null ? pattern : defaultDatePattern).format(value);
    }

    protected void writeToCell(Cell cell, String fieldName, Integer fileSuffixValue, Integer defaultScale, Map<String, String> formatConfigMap, String textValue) {
        if (Pattern.compile("^\\d+\\.\\d+$").matcher(textValue).matches()) {
            /*若是浮点数，瞅瞅是否有自己配置的精度参数*/
            Integer scale = null;
            if (formatConfigMap != null) {
                String s = formatConfigMap.get(fieldName + ExcelExporterConst.MAP_CONTENT_SIGN.FLOAT_SCALE.getText());
                if (Pattern.compile("^[\\d]*$").matcher(s).matches()) {
                    scale = Integer.valueOf(s);
                }
            }

            cell.setCellValue(new BigDecimal(Double.parseDouble(textValue)).setScale(scale != null ? scale : defaultScale, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        } else {
            /*若不是浮点小数，就按字符串处理了当*/
            if (fileSuffixValue == ExcelExporterConst.FILE_NAME_SUFFIX.XLS.getValue()) {
                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                cell.setCellValue(richString);
            } else if (fileSuffixValue == ExcelExporterConst.FILE_NAME_SUFFIX.XLSX.getValue()) {
                XSSFRichTextString richString = new XSSFRichTextString(textValue);
                cell.setCellValue(richString);
            }
        }
    }

    /**
     * Excel导出体系：
     * <p>
     * 生成指定文件名的Excel文件
     *
     * @param workbook
     * @param fileName
     */
    protected static void generateExcel(Workbook workbook, String fileName, HttpServletResponse response) {
        if (notExist(response)) {
            generateExcel(workbook, fileName);
            return;
        }
        OutputStream out = null;
        try {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成指定文件名的Excel文件
     *
     * @param workbook
     * @param fileName
     */
    protected static void generateExcel(Workbook workbook, String fileName) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
