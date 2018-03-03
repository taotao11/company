package com.example.company.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.company.entity.Admin;
import com.example.company.mapper.AdminMapper;
import com.example.company.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试mybaits-plus
 */
@RestController
public class AdminController {
    @Autowired
    private IAdminService adminService;

    /**
     *
     * 测试添加
     * @return
     */
    @RequestMapping("/addAdmin")
    public Admin addAdmin(){
        Admin admin = new Admin();
        admin.setUname("李四");
        admin.setUpass("123");
        admin.setUsex("男");
        admin.setUemil("www.baidu.com");
        boolean isInsert = adminService.insert(admin);
        System.out.println(isInsert);
    return admin;
    }

    /**
     * 测试单个查询 有错误
     * @return
     */
    @RequestMapping("/selectAdmin")
    public Admin selectAdmin(){
        Admin admin1 = new Admin();
        admin1.setUid(1);
        long id = 1;
      Admin admin = adminService.selectById(id);
        return admin;
    }

    /**
     * 查询所有方法
     * @return
     */
    @RequestMapping("/selectAdminAll")
    public List<Admin> selectAllAdmin(){
        List<Admin> list = adminService.selectList(new EntityWrapper<Admin>());
        return list;
    }

    /**
     *更新方法 错误
     * @return
     */
    @RequestMapping("/upadateAdmin")
    public boolean upadateAdmin(){
        Admin admin = new Admin();
        admin.setUname("李四");
        admin.setUpass("123");
        admin.setUsex("男");
        admin.setUemil("www.baidu.com");
        boolean isInsert = adminService.updateById(admin);
        System.out.println(isInsert);
        return false;
    }

    /**
     * 测试删除方法
     * @return
     */
    @RequestMapping("deletAdmin")
    public boolean deletAdmin(){
        return  adminService.deleteById(1);

    }
    @RequestMapping("pageFindAdmin")
    public Page<Admin> pageFindAdmin(){
        Page<Admin> page = adminService.selectPage(new Page<Admin>(1,5));

        //条件查询
//        page = adminService.selectPage(
//                new Page<Admin>(1, 10),
//                new EntityWrapper<Admin>().eq("name", "张三")
//                        .eq("sex", 0)
//                        .between("age", "18", "50")
//        );
//        SELECT *
//                FROM sys_user
//        WHERE (name='张三' AND sex=0 AND age BETWEEN '18' AND '50')
//        LIMIT 0,10
        return page;
    }

    /**
     * 测试继承实体类继承Model的方法
     * AR model 封装了Mapper的方法
     * @return
     */
    @RequestMapping("testAr")
    public Admin testAr(){
        Admin admin = new Admin();
        admin.setUemil("wwww");
        admin.setUsex("女");
        admin.setUpass("123");
        admin.setUname("画质");
        //插入
//        System.out.println(admin.insert());
        //更新
        admin.setUname("好看");
        System.out.println(admin.updateById());
        return null;
    }
    @RequestMapping("code")
    public String code(){
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(true);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("taotao");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImap");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/taotao?useUnicode=true&amp;characterEncoding=UTF-8&amp;generateSimpleParameterMetadata=true");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.");
        pc.setModuleName("generator");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
        return "success";
    }
}
