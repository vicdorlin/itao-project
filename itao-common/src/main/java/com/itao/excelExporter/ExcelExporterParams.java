package com.itao.excelExporter;

import com.itao.sample.excelExporterSample.ExcelExporterTest;

import java.util.Collection;
import java.util.Map;

/**
 * Excel导出体系：
 * <p>
 * Excel导出相关参数封装类
 *
 * @author Created by Vicdor(linss) on 2016-05-20 00:34.
 */
public class ExcelExporterParams<T> {

    //以下为必需参数
    private Collection<T> dataSet;                  //待导出的数据对象集合
    private String[] headers;                       //excel表头数组
    /**
     * 要求fieldNames.length == headers.length,且一一对应
     * <p>
     * 说明：若表头中一个或多个元素比如所对应的字段并不属于 T 类的属性，
     * 即需求额外指定要生成的列，那么，这里采取单独生成这些列的方法
     * 但是fieldNames依旧要留位置给对应的header,
     * 这里只要置入任意非T类的字段名即可，建议置入空字符串
     * 按顺序生成某列所有数据，置入list，要求该list长度 == dataSet.size()
     * 将该list置入dataMap 传给 ExcelExporter的子类对象，
     * 在其子类中，重写fillInCells方法专门处理即可
     * 可参见:
     * {@link ExcelExporterTest}
     * 自定义多列元素也不要紧，在dataMap中配置好对应的key即可
     **/
//    private int[] fieldIndexes;                     //对象实体字段索引（第fieldIndexes+1个字段）有序数组
    private String[] fieldNames;                    //字段名
    private String fileName;                        //文件名称
    private Integer fileSuffixValue;                //文件名称后缀枚举Value值

    //以下为非必需参数
    private String defaultDatePattern;              //A,全局时间格式                                C
    private Integer defaultScale;                   //B,全局精度                                   D
    private Map<String, String> formatConfigMap;     //C,时间格式Map<字段名0,指定日期格式字符串>    精度Map<字段名1,指定浮点精度Integer值>

    /**
     * ABC
     * 用户配置 全局 时间、精度 默认参数
     *
     * @param defaultDatePattern 用户配置的全局默认时间格式
     * @param defaultScale       用户配置的全局默认浮点精度
     * @param dataSet            待导出的数据对象集合
     * @param headers            excel表头数组
     * @param fieldNames         字段名数组
     * @param formatConfigMap    时间格式Map<字段名0,格式字符串> 精度Map<字段名1,精度Integer值>
     * @param fileName           文件名称
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, String defaultDatePattern, Integer defaultScale, Map<String, String> formatConfigMap) {
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultDatePattern = defaultDatePattern;
        this.defaultScale = defaultScale;
        this.formatConfigMap = formatConfigMap;
    }

    /**
     * 所有可配参数采用系统配置
     *
     * @param dataSet
     * @param headers
     * @param fieldNames      字段名数组
     * @param fileName
     * @param fileSuffixValue
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue) {
        defaultDatePattern = ExcelExporterConst.DEFAULT_DATTERN_PATTERN;
        defaultScale = ExcelExporterConst.DAFAULT_fLOAT_SCALE;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
    }

    /**
     * A
     * 用户自定义默认时间格式
     * 默认精度采用系统配置
     *
     * @param dataSet
     * @param headers
     * @param fieldNames         字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param defaultDatePattern
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, String defaultDatePattern) {
        defaultScale = ExcelExporterConst.DAFAULT_fLOAT_SCALE;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultDatePattern = defaultDatePattern;
    }

    /**
     * B
     * 用户自定义默认精度
     * 默认时间格式采用系统配置
     *
     * @param dataSet
     * @param headers
     * @param fieldNames      字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param defaultScale
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, Integer defaultScale) {
        defaultDatePattern = ExcelExporterConst.DEFAULT_DATTERN_PATTERN;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultScale = defaultScale;
    }

    /**
     * C
     * 用户自定义默认时间格式及浮点精度
     * 并可能配置 指定字段的 时间格式U浮点精度
     *
     * @param dataSet
     * @param headers
     * @param fieldNames      字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param formatConfigMap
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, Map<String, String> formatConfigMap) {
        defaultDatePattern = ExcelExporterConst.DEFAULT_DATTERN_PATTERN;
        defaultScale = ExcelExporterConst.DAFAULT_fLOAT_SCALE;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.formatConfigMap = formatConfigMap;
    }

    /**
     * AB
     * 用户自己配置所有字段的时间格式与浮点精度
     *
     * @param dataSet
     * @param headers
     * @param fieldNames         字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param defaultDatePattern
     * @param defaultScale
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, String defaultDatePattern, Integer defaultScale) {
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultDatePattern = defaultDatePattern;
        this.defaultScale = defaultScale;
    }

    /**
     * AC
     * 用户自己配置默认时间格式
     * 并可能配置部分指定字段的默认时间格式U浮点精度
     *
     * @param dataSet
     * @param headers
     * @param fieldNames         字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param defaultDatePattern
     * @param formatConfigMap
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, String defaultDatePattern, Map<String, String> formatConfigMap) {
        defaultScale = ExcelExporterConst.DAFAULT_fLOAT_SCALE;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultDatePattern = defaultDatePattern;
        this.formatConfigMap = formatConfigMap;
    }

    /**
     * BC
     * 用户自己配置默认浮点精度
     * 并可能配置部分指定字段的默认时间格式U浮点精度
     *
     * @param dataSet
     * @param headers
     * @param fieldNames      字段名数组
     * @param fileName
     * @param fileSuffixValue
     * @param defaultScale
     * @param formatConfigMap
     */
    public ExcelExporterParams(Collection<T> dataSet, String[] headers, String[] fieldNames, String fileName, Integer fileSuffixValue, Integer defaultScale, Map<String, String> formatConfigMap) {
        defaultDatePattern = ExcelExporterConst.DEFAULT_DATTERN_PATTERN;
        this.dataSet = dataSet;
        this.headers = headers;
        this.fieldNames = fieldNames;
        this.fileName = fileName;
        this.fileSuffixValue = fileSuffixValue;
        this.defaultScale = defaultScale;
        this.formatConfigMap = formatConfigMap;
    }

    public Collection<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(Collection<T> dataSet) {
        this.dataSet = dataSet;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Map<String, String> getFormatConfigMap() {
        return formatConfigMap;
    }

    public void setFormatConfigMap(Map<String, String> formatConfigMap) {
        this.formatConfigMap = formatConfigMap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSuffixValue() {
        return fileSuffixValue;
    }

    public void setFileSuffixValue(Integer fileNameValue) {
        this.fileSuffixValue = fileNameValue;
    }

    public String getDefaultDatePattern() {
        return defaultDatePattern;
    }

    public void setDefaultDatePattern(String defaultDatePattern) {
        this.defaultDatePattern = defaultDatePattern;
    }

    public Integer getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(Integer defaultScale) {
        this.defaultScale = defaultScale;
    }
}
