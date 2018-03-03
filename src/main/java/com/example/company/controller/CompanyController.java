package com.example.company.controller;

import com.example.company.entity.CompanyBean;
import com.example.company.entity.DepartmentBean;
import com.example.company.entity.PageBean;
import com.example.company.form.CompanyForm;
import com.example.company.form.DengluForm;
import com.example.company.service.CompanyService;
import com.example.company.service.DepartmentService;
import com.example.company.uitl.UtilsMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 公司控制层
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService service;
    @Autowired
    private DepartmentService departmentService;

    /**
     *
     * 公司信息查询
     * @param name
     * @return
     */
    @RequestMapping("/selectOneCompany")
    public String selectOneCompany( String name,Model model){
        if (name == null || name.equals("")){
            return  "redirect:/company/selectAllCompany/1";
        }
        CompanyBean companyBean = new CompanyBean();
        DepartmentBean departmentBean = new DepartmentBean();
        try {
            companyBean = service.selectUser(name);
             if (companyBean != null){
                 //查询部门信息
                List<DepartmentBean> list = departmentService.selectAllUser(companyBean.getC_id());
                if (list.size() != 0){
                    companyBean.setItems(list);
                    model.addAttribute("cmessage","hide");
                    model.addAttribute("company",companyBean);
                }else {
                    model.addAttribute("dmessage","没有部门信息请添加!!");
                }
                 model.addAttribute("company",companyBean);
             }else {
                    model.addAttribute("message","公司不存在!!");
                 model.addAttribute("company",new CompanyBean());
             }

        } catch (Exception e) {
            model.addAttribute("message","公司不存在!!");
            model.addAttribute("company",new CompanyBean());
        }

        return "departments";
    }

    /**
     * 登录页跳转
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index","/"})
    public String index(Model model){
        model.addAttribute("dengluForm",new DengluForm());
        return "index";
    }
    /**
     * 公司登录
     * @param dengluForm
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value = "/dengluCompeny",method = RequestMethod.POST)
    public String dengluCompeny(@Valid DengluForm dengluForm, BindingResult result, HttpServletRequest request,
                                final RedirectAttributes attributes){
        //检验数据
        if (!UtilsMethods.clickCompanyFormData(result)){
            return "index";
        }
        //查询
        CompanyBean companyBean = null;
        try {
            companyBean = service.selectUser(dengluForm.getC_name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断是否为空
        if (companyBean != null){
            //判断code是否一致
            if (companyBean.getC_code().equals(dengluForm.getC_code())){
                System.out.println("登录成功");
                request.getSession().setAttribute("company",companyBean);
//               attributes.addFlashAttribute("c_id",companyBean.getC_id()); 闪存
                return "redirect:/department/selectAllDepartmentByCid/" + companyBean.getC_id();

            }else {
                attributes.addAttribute("message","组织机构code错误!!!");
            }
        }else {
            attributes.addAttribute("message","公司不存在!!");
        }
        return "index";
    }

    /**
     * 删除公司信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/deletCompany/{id}")
    public String deletCompany(@PathVariable("id") int id,Model model){
        int index = 0;
        try {
          index = service.deletUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0){
            model.addAttribute("message","删除成功");
        }else {
            model.addAttribute("message","删除失败");
        }
        return "redirect:/company/selectAllCompany/1";
    }
    /**
     * 查询单个公司信息
     * @param id
     * @return
     */
    @RequestMapping("/selectCompany/{id}")
    public String selectCompany(@PathVariable("id") String id, Model model){
        CompanyBean companyBean = null;
        try {
           companyBean = service.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (companyBean != null){
            model.addAttribute("companyForm",companyBean);
        }
        return  "upataeComppany";
    }
    /**
     * 修改个公司信息
     * @param companyForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("upataeComppany")
    public String upataeComppany(@Valid CompanyForm companyForm,BindingResult result,Model model){
        //表单数据检查
        if(!UtilsMethods.clickCompanyFormData(result)){
            return "upataeComppany";
        }
        int index = 0;
        try {
           index = service.updateUser(companyForm.convertComanyBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0){
            model.addAttribute("message","修改成功");
            //重定向
            return "redirect:/company/selectAllCompany/1";
        }
        return "upataeComppany";
    }
    /**
     * 查询所有操作
     * @return
     */
    @RequestMapping("/selectAllCompany/{id}")
    public String selectAllCompany(@PathVariable("id") int id, Model model){
        PageBean<CompanyBean> pageBean = new PageBean<CompanyBean>();
        try {
           pageBean = service.findUserByPage(id,2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("page",pageBean);
        return "CompanyItems";
    }

    /**
     *
     * 跳转添加公司信息页面
     * @param model
     * @return
     */
    @RequestMapping("/addCompany")
    public String goAddCompany(Model model){
        model.addAttribute("companyForm",new CompanyForm());
        return "addCompany";
    }

    
    /**
     * 添加公司信息
     *
     * @return
     */
    @RequestMapping("/addCompanyInfo")
    public String addCompanyInfo(@Valid CompanyForm companyForm, BindingResult result, Model model){
        //判断验证信息
        if(!UtilsMethods.clickCompanyFormData(result)) {
            return "addCompany";
        }
        //对象复制
        CompanyBean companyBean = companyForm.convertComanyBean();
        List<CompanyBean> list = new ArrayList<CompanyBean>();
        list.add(companyBean);
        int index = 0;
        try {
            //插入操作
          index = service.insertUser(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0){
            model.addAttribute("message","添加成功");
            //重定向
            return "redirect:/company/selectAllCompany/1";
        }
        return "addCompany";
    }
}
