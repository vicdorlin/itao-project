package com.itao.sample.excelExporterSample.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注：字段顺序不可随意变动
 * 若新增字段，请不要影响原有字段的顺序
 * <p>
 * Created by Vicdor on 2015/10/27 0027.
 */
public class Doggy {
    private String name;        //0  doggy的 闺名
    private Character sex;      //1  m or f  阴阳
    private Integer age;        //2  doggy的 芳龄
    private Double height;      //3  doggy的 海拔
    private Double weight;      //4  doggy的 斤两
    private String hobby;       //5  doggy的 怪癖，食色性也
    private Date birthday;      //6  doggy的 诞辰
    private Long id;            //7  爱迪  9527的编号、用以测试长整数
    private String strId;       //8  瞎搞  一万个9527的编号之积，测试超长数
    private Date birthDate;     //9  doggy的 蛋沉


    /**
     * 生成数据集合dataSet
     *
     * @return
     */
    public static List<Doggy> getDoggies(long num) {
        List<Doggy> list = new ArrayList<>();
        for (long i = 0; i < num; i++) {
            Doggy doggy = new Doggy();
            doggy.setName("旺财" + i + "号");
            doggy.setAge((int) (Math.random() * 50) + 1);
            doggy.setWeight(Math.random() * 80 + 1);
            doggy.setHeight(Math.random() * 60 + 1);
            doggy.setHobby("打球");
            doggy.setBirthday(new Date());
            doggy.setSex(Math.random() < 0.5 ? 'm' : 'f');
            doggy.setId(6828288999187722328l);
            doggy.setStrId("4343434342323288884538888326557685");
            doggy.setBirthDate(new Date());
            list.add(doggy);
        }
        return list;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doggy() {
    }

    public Doggy(String name, Character sex, Integer age, Double height, Double weight, String hobby, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.hobby = hobby;
        this.birthday = birthday;
    }

    public static Doggy getDoggy() {
        return new Doggy("旺财", 'm', 3, 20.8, 30.6, "bite bones", new Date());
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
