package com.itao.po;

import java.util.Date;

public class TbContentCategory {
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;

    public TbContentCategory() {
    }

    public TbContentCategory(Long id) {
        this.id = id;
    }

    /**
     * 用于修改节点
     * @param name
     * @param id
     */
    public TbContentCategory(String name,Long id) {
        this.name = name;
        this.id = id;
    }

    /**
     * 用于新增节点
     * @param parentId
     * @param name
     */
    public TbContentCategory(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    /**
     * 构建普通的内容分类信息
     */
    public void buildContentCategory(){
        isParent = false;
        sortOrder = 1;
        status = Status.NORMAL.key;
        created = new Date();
        updated = created;
    }

    /**
     * 删除内容分类
     */
    public void delContentCategory(){
        status = Status.DELETED.key;
    }

    /**
     * 改变条目类型
     * @param isParent
     */
    public void ifParent(Boolean isParent){
        this.isParent = isParent;
    }

    enum Status{
        NORMAL(1,"正常"),
        DELETED(2,"删除")
        ;
        private Integer key;
        private String value;

        Status(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}