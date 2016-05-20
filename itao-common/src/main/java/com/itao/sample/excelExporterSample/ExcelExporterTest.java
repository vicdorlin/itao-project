package com.itao.sample.excelExporterSample;

import com.itao.excelExporter.ExcelExporterConst;
import com.itao.excelExporter.ExcelExporterParams;
import com.itao.sample.excelExporterSample.domain.Doggy;
import com.itao.sample.excelExporterSample.exporter.DoggyExporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表导出体系：
 * 导出示例
 *
 * @author Created by Vicdor(linss) on 2016-05-20 02:40.
 */
public class ExcelExporterTest {
    public static void main(String[] args) {
        //数据集
        List<Doggy> dataSet = Doggy.getDoggies(15);
        //表头
        String[] headers = {"闺名", "芳龄", "阴阳", "怪癖", "扯犊子", "诞辰", "蛋沉", "滚犊子", "海拔", "斤两", "爱迪", "瞎搞"};
        //表头平行一一对应字段名（如数据对象所属类中无该字段，则建议传空字符串）
        String[] fieldNames = {"name", "age", "sex", "hobby", "", "birthday", "birthDate", "", "height", "weight", "id", "strId"};
        //自定义返回文件主题名称
        String fileName = "文件主题";
        //导出excel格式
        int fileSuffixValue = ExcelExporterConst.FILE_NAME_SUFFIX.XLS.getValue();
        //附加数据集（需要在子类导出器中重写fillInCells方法针对处理，Map中可以携带多组数据集）
        Map<String, List<String>> dataMap = new HashMap<>();
        List<String> intList = new ArrayList<>();
        for (int i = 0; i < dataSet.size(); i++) {
            intList.add(String.valueOf(i));
        }
        dataMap.put("add0", intList);
        List<String> strList = new ArrayList<>();
        for (Doggy doggy : dataSet) {
            intList.add(doggy.getAge()+"岁的小花狗");
        }
        dataMap.put("add1", strList);
        //时间格式Map<字段名0,日期格式>    精度Map<字段名1,浮点精度>
        Map<String, String> formatConfigMap = new HashMap<>();
        formatConfigMap.put("birthday0", "yy-MM-dd hh:mm:ss");
        formatConfigMap.put("birthDate0", "yy-MM-dd");

        formatConfigMap.put("height1", "2");
        formatConfigMap.put("weight1", "3");

        ExcelExporterParams<Doggy> params = new ExcelExporterParams<>(dataSet, headers, fieldNames, fileName, fileSuffixValue, formatConfigMap);

        DoggyExporter<Doggy> exporter = new DoggyExporter<>();
        exporter.export(params, dataMap, null);
    }
}
