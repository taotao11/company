/**
 * 公司表操作Mapper
 * 
 * 
 */
package com.example.company.mapper;

import com.example.company.entity.CompanyBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CompanyMapper {
	//插入多条
		public int insertUser(List<CompanyBean> list) throws Exception;
		//更新操作
		public int updateUser(CompanyBean admin) throws Exception;
		//删除操作
		public int deletUser(int id) throws Exception;
		//查询单个
		public CompanyBean selectUser(@Param("id") String id) throws Exception;
		//查询所有
		public List<CompanyBean> selectAllUser() throws Exception;
		//动态sql
		public List<CompanyBean> dtsqtest(CompanyBean admin) throws Exception;
		//总记录数
		public int countUser()throws Exception;
}
