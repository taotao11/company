package com.example.company.service.impl;

import com.example.company.entity.CompanyBean;
import com.example.company.entity.PageBean;
import com.example.company.mapper.CompanyMapper;
import com.example.company.service.CompanyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Company 服务层
 *
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    //注入Mapper
    @Autowired
    private CompanyMapper mapper;

    /**
     * 插入操作
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public int insertUser(List<CompanyBean> list) throws Exception {
        return mapper.insertUser(list);
    }

    /**
     * 更新操作
     * @param admin
     * @return
     * @throws Exception
     */
    @Override
    public int updateUser(CompanyBean admin) throws Exception {
        return mapper.updateUser(admin);
    }

    /**
     * 删除操作
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deletUser(int id) throws Exception {
        return mapper.deletUser(id);
    }

    /**
     * 单个查询操作
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public CompanyBean selectUser(String id) throws Exception {
        return mapper.selectUser(id);
    }

    /**
     * 查询所有操作
     * @return
     * @throws Exception
     */
    @Override
    public List<CompanyBean> selectAllUser() throws Exception {
        return mapper.selectAllUser();
    }

    @Override
    public List<CompanyBean> dtsqtest(CompanyBean admin) throws Exception {
        return null;
    }

    /**
     * 分页操作
     * @param currentPage 当前页
     * @param pageSize 多少数据
     * @return
     * @throws Exception
     */
    @Override
    public PageBean findUserByPage(int currentPage, int pageSize) throws Exception {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        //查询信息
        List<CompanyBean> list = mapper.selectAllUser();
        //总记录数
        int count = mapper.countUser();
        PageBean<CompanyBean> pageBean = new PageBean<CompanyBean>(currentPage,pageSize,count);
        pageBean.setItems(list);
        System.out.println(pageBean);
        return pageBean;
    }

    /***
     * 总数查询操作
     * @return
     * @throws Exception
     */
    @Override
    public int countUser() throws Exception {
        return mapper.countUser();
    }
}
