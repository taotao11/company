package com.example.company.form;
/**
 * 部门表单接收类
 *
 */

import com.example.company.entity.DepartmentBean;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

public class DepartmentForm {
    private int d_id;
    //部门名称
    @NotBlank(message = "部门名称不为空")
    private String d_name;
    //部门做什么
    @NotBlank(message = "部门方向不为空")
    private String d_do;
    //外键 公司id
    @Range(min = 1,message = "公司c_id不能为0")
    private  int c_id;

    public DepartmentForm() {
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

    /**
     *
     * 返回复制的对象
     * @return
     */
    public  DepartmentBean convert(){
        return new DepartmentFormConvert().convert(this);
    }
    /**
     * 聚合内部类
     */
    private class DepartmentFormConvert implements FormConvert<DepartmentForm,DepartmentBean>{
        /**
         * 对象复制
         * @param departmentForm
         * @return
         */
        @Override
        public DepartmentBean convert(DepartmentForm departmentForm) {
            DepartmentBean departmentBean = new DepartmentBean();
            BeanUtils.copyProperties(departmentForm,departmentBean);
            return departmentBean;
        }
    }
}
