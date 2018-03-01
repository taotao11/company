package com.example.company.entity;

import java.util.List;

/**
 * 公司实体
 */
public class CompanyBean {
    //公司id
    private int c_id;
    //公司名称
    private String c_name;
    //公司类型
    private String c_type;
    //组织机构代码
    private String c_code;
    //公司下属部门
    private List<DepartmentBean> items;

    public CompanyBean() {
    }

    @Override
    public String toString() {
        return "company{" +
                "c_id=" + c_id +
                ", c_name='" + c_name + '\'' +
                ", c_type='" + c_type + '\'' +
                ", c_code='" + c_code + '\'' +
                ", items=" + items +
                '}';
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public List<DepartmentBean> getItems() {
        return items;
    }

    public void setItems(List<DepartmentBean> items) {
        this.items = items;
    }
}
