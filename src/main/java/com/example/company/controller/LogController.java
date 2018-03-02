package com.example.company.controller;

import com.example.company.entity.DepartmentBean;
import com.example.company.form.AdminLogForm;
import com.example.company.service.DepartmentService;
import com.example.company.uitl.UtilsMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LogController {
    @Autowired
    private DepartmentService service;
    @RequestMapping("/log")
    public String log(Model model){
        model.addAttribute("adminLogForm",new AdminLogForm());
        return "adminlog";
    }
    @RequestMapping("/selectAllDepartmentByCid/{id}")
    public String selectAllDepartmentByCid(@PathVariable("id") int cid, Model model){
        List<DepartmentBean> list = null;
        try {
            list =  service.selectAllUser(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list != null){
            if (list.size() == 0){
                model.addAttribute("message","该公司没有更新部门信息");
            }
            model.addAttribute("list",list);
            System.out.println(list);
        }else {
            model.addAttribute("message","没有部门请添加");
        }
        return "departments";
    }
    /**
     * 管理员登录
     * @param adminLogForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/adminLog")
    public String adminLog(@Valid AdminLogForm adminLogForm , BindingResult result, Model model){
        if (!UtilsMethods.clickCompanyFormData(result)){
            return "adminlog";
        }
        if ("admin".equals(adminLogForm.getName())){

            if ("root".equals(adminLogForm.getPass())){

                System.out.println("登录成功");
                return "redirect:/company/selectAllCompany/1";
            }else{
                model.addAttribute("message","密码错误");
            }
        }else {
            model.addAttribute("message","用户不存在");
        }
        return "";
    }
}
