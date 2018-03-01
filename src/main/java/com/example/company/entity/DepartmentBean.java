package com.example.company.entity;

/**
 * 部门实体类
 */
public class DepartmentBean {
    //部门id
    private int d_id;
    //部门名称
    private String d_name;
    //部门做什么
    private String d_do;
    //外键 公司id
    private  int c_id;

    public DepartmentBean() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "d_id=" + d_id +
                ", d_name='" + d_name + '\'' +
                ", d_do='" + d_do + '\'' +
                ", c_id=" + c_id +
                '}';
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_do() {
        return d_do;
    }

    public void setD_do(String d_do) {
        this.d_do = d_do;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}
