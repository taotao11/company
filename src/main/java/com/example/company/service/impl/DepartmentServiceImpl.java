package com.example.company.service.impl;

import com.example.company.entity.DepartmentBean;
import com.example.company.mapper.DepartmentMapper;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper mapper;
    /**
     * 添加部门
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public int insertUser(List<DepartmentBean> list) throws Exception {
        return mapper.insertUser(list);
    }

    /**
     * 更新部门
     * @param department
     * @return
     * @throws Exception
     */
    @Override
    public int updateUser(DepartmentBean department) throws Exception {
        return mapper.updateUser(department);
    }

    /**
     * 删除部门
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deletUser(int id) throws Exception {
        return mapper.deletUser(id);
    }

    /**
     *单个部门查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public DepartmentBean selectUser(int id) throws Exception {
        return mapper.selectUser(id);
    }

    /**
     * 多个查询
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public List<DepartmentBean> selectAllUser(int cid) throws Exception {
        return mapper.selectAllUser(cid);
    }

    @Override
    public List<DepartmentBean> dtsqtest(DepartmentBean department) throws Exception {
        return null;
    }

    /**
     * 查询所有
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public int countUser(int cid) throws Exception {
        return mapper.countUser(cid);
    }
}
