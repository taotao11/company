package com.example.company.controller;

import com.example.company.entity.DepartmentBean;
import com.example.company.form.DepartmentForm;
import com.example.company.service.DepartmentService;
import com.example.company.uitl.UtilsMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门控制类
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    /**
     * 删除部门
     * @param id
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("/deletDepartment/{id}/{cid}")
    public String deletDepartment(@PathVariable("id") int id,@PathVariable("cid") int cid, Model model){
        int index = 0;
        try {
          index = service.deletUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(index);
        if (index > 0){
            model.addAttribute("message","删除成功");
        }
        return "redirect:/department/selectAllDepartmentByCid/" + "" +cid;
    }
    /**
     * 修改部门信息
     * @param departmentForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/UpdateDepartment")
    public String updateDepartment(@Valid DepartmentForm departmentForm,BindingResult result,Model model){
        //检查表单数据
        if(!UtilsMethods.clickCompanyFormData(result)){
            return "department/upatateDepartment";
        }
        int index = 0;
        DepartmentBean departmentBean = departmentForm.convert();
        System.out.println(departmentBean);
        try {
          index = service.updateUser(departmentBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(index);
        if (index > 0){
            System.out.println("修改成功");
            model.addAttribute("message","修改成功");
            //从定向部门列表页
            return "redirect:/department/selectAllDepartmentByCid/" + "" + departmentBean.getC_id();
        }else{
            model.addAttribute("message","修改失败!!");
        }
        return "department/upatateDepartment";
    }
    /**
     * 跳转修改页面
     * @param model
     * @return
     */
    @RequestMapping("/goUpdateDepartment/{id}")
    public String goUpdateDepartment(@PathVariable("id") int id, Model model){
        DepartmentBean departmentBean = null;
        try {
           departmentBean  =  service.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ( departmentBean != null){
            model.addAttribute("departmentForm",departmentBean);
        }else{
            model.addAttribute("message","未知错误！！");
        }

        return "/department/upatateDepartment";
    }
    /**
     * 添加部门信息
     * @param departmentForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/addDepartmentByCid")
    public String addDepartmentByCid(@Valid DepartmentForm departmentForm, BindingResult result, Model model){
        if(!UtilsMethods.clickCompanyFormData(result)){
            return "department/addDepartment";
        }
        System.out.println(departmentForm);
        //对象复制
        DepartmentBean departmentBean = departmentForm.convert();
        List<DepartmentBean> list = new ArrayList<DepartmentBean>();
        list.add(departmentBean);
        int index = 0;
        try {
           index = service.insertUser(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0){
            model.addAttribute("message","新增成功！！！");
            //从定向部门列表页
            return "redirect:/department/selectAllDepartmentByCid/" + "" + departmentBean.getC_id();
        }else {
            model.addAttribute("message","新增失败！！！");
        }
        return "department/departments";
    }

    /**
     * 跳转页面
     * @param model
     * @return
     */
    @RequestMapping("/goAddDeparment/{cid}")
    public String goAddDepartment(@PathVariable("cid") int cid, Model model){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setC_id(cid);
        model.addAttribute("departmentForm",departmentForm);
        return "department/addDepartment";
    }
    /**
     * 查询所有的部门信息
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("/selectAllDepartmentByCid/{id}")
    public String selectAllDepartmentByCid(@PathVariable("id") int cid, Model model){
        List<DepartmentBean> list = null;
        try {
         list =  service.selectAllUser(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list != null){
            model.addAttribute("list",list);
            model.addAttribute("c_id",cid);
            System.out.println(list);
        }else {
            model.addAttribute("message","没有部门请添加");
        }
        return "department/departments";
    }

}
