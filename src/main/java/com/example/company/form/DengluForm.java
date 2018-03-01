package com.example.company.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单对象
 *
 */
public class DengluForm {

    @NotBlank(message = "公司名不能为空")
    private String c_name;
    @Length(min = 8,message = "长度不能小于八位")
    private String c_code;

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public DengluForm() {

    }
}
