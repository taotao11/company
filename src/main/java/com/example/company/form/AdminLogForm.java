package com.example.company.form;

import javax.validation.constraints.NotBlank;

public class AdminLogForm {
    @NotBlank(message = "用户名不为空")
    private String name;
    @NotBlank(message = "密码不为空！")
    private String pass;

    public AdminLogForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
