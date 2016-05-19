package com.itao.sample.excelExporterSample.exporter;

import com.itao.excelExporter.ExcelExporter;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-20 02:46.
 */
public class DoggyExporter<T> extends ExcelExporter<T> {
    /**
     * 重写fillInCells
     * <p>
     * 处理额外字段
     *
     * @param rowFlag            表示当前处理行数-1;不可省却，虽然在此处并未用上，然在子类重写后的方法中，结合dataMap或许就有其用武之地了
     * @param cell               当前处理单元格
     * @param t                  当前处理对象
     * @param fieldName          字段名
     * @param fileTypeIndex
     * @param defaultDatePattern 默认时间格式
     * @param defaultScale       默认浮点数精度
     * @param formatConfigMap
     * @param dataMap            人工附加数据载体
     */
    public void fillInCells(int rowFlag, Cell cell, T t, String fieldName, Integer fileTypeIndex, String defaultDatePattern, Integer defaultScale, Map<String, String> formatConfigMap, Map<String, List<String>> dataMap) {
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            String textValue;
            if (("0").equals(fieldName)) {
                List<String> list = dataMap.get("add0");
                textValue = list.get(rowFlag);
            } else if (("1").equals(fieldName)) {
                List<String> list = dataMap.get("add1");
                textValue = list.get(rowFlag);
            } else {
                textValue = formatCellTextValue(t, fieldName, defaultDatePattern, formatConfigMap, getMethodName, dataMap);
            }
            writeToCell(cell, fieldName, fileTypeIndex, defaultScale, formatConfigMap, textValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写formatCellTextValue
     * <p>
     * 修改字段写入cell的内容
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
        } else if ("sex".equals(fieldName.trim())) {
            Character sex = (Character) value;
            if ('m' == sex) {
                textValue = "公的";
            } else if ('f' == sex) {
                textValue = "母的";
            } else {
                textValue = "天妒";
            }
        } else {
            textValue = value.toString();
        }
        return textValue;
    }
}
