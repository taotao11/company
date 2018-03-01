/**
 * 公司表操作Mapper
 * 
 * 
 */
package com.example.company.mapper;

import com.example.company.entity.DepartmentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DepartmentMapper {
		//插入多条部门数据
		public int insertUser(List<DepartmentBean> list) throws Exception;
		//更新操作当前公司部门数据
		public int updateUser(DepartmentBean department) throws Exception;
		//删除操作当前公司部门数据
		public int deletUser(int id) throws Exception;
		//查询单个当前公司部门数据
		public DepartmentBean selectUser(@Param("id") int id) throws Exception;
		//查询所有当前公司部门数据
		public List<DepartmentBean> selectAllUser(int cid) throws Exception;
		//动态sql
		public List<DepartmentBean> dtsqtest(DepartmentBean department) throws Exception;
		//当前公司总记录数部门数据
		public int countUser(int cid)throws Exception;
}
