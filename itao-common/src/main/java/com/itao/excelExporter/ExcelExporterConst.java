package com.itao.excelExporter;

/**
 * Excel导出体系：
 * <p>
 * Excel导出功能常量配置
 *
 * @author Created by Vicdor(linss) on 2016-05-20 00:36.
 */
public class ExcelExporterConst {
    public static final String DEFAULT_DATTERN_PATTERN = "yyyy-MM-dd hh:mm:ss";
    public static final Integer DAFAULT_fLOAT_SCALE = 2;
    public static final String DEFAULT_SHEEET_NAME = "sheet";

    /**
     * 导出文件格式后缀枚举
     */
    public enum FILE_NAME_SUFFIX {
        XLS(0, "xls"),
        XLSX(1, "xlsx");

        private int value;
        private String text;

        FILE_NAME_SUFFIX(int value, String text) {
            this.value = value;
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    /**
     * 字段格式限制枚举
     */
    public enum MAP_CONTENT_SIGN {
        DATE_PATTERN(0, "0"),//formatConfigMap中 key为 "字段名+0" 表示  其value 封装的是时间格式字符串
        FLOAT_SCALE(1, "1");//formatConfigMap中 key为 "字段名+1" 表示  其value 封装的是数值精度字符串

        private int value;
        private String text;

        MAP_CONTENT_SIGN(int value, String text) {
            this.value = value;
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }
}
