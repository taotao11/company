package com.example.company.form;

import com.example.company.entity.CompanyBean;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * 公司表单接受对象
 *
 */
public class CompanyForm {
    //公司id
    private  int c_id;
    //公司名称
    @NotBlank(message = "公司名称不为空")
    private String c_name;
    //公司类型
    @NotBlank(message = "公司类型不为空")
    private String c_type;
    //组织机构代码
    @Length(min = 8,message = "组织机构代码不小于8位")
    private String c_code;

    public CompanyForm() {
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
    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
    /**
     * 调用内部类对象复制方法
     * @return
     */
    public CompanyBean convertComanyBean(){
        return new CompanyFormConvert().convert(this);
    }
    /**
     * 聚合内部类
     */
    private class CompanyFormConvert implements  FormConvert<CompanyForm,CompanyBean>{

        @Override
        public CompanyBean convert(CompanyForm companyForm) {
            //返回对象
            CompanyBean companyBean = new CompanyBean();
            //复制对象
            BeanUtils.copyProperties(companyForm,companyBean);
            return companyBean;
        }
    }
}
