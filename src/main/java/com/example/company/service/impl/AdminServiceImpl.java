package com.example.company.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.company.entity.Admin;
import com.example.company.mapper.AdminMapper;
import com.example.company.service.IAdminService;
import org.springframework.stereotype.Service;

/**
 *admin 服务接口实现类
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements IAdminService {

}
