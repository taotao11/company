package com.example.company.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 管理员实体
 */
@TableName("tb_admin")
public class Admin extends Model implements Serializable {
    //主键id 自增
    private int uid;
    //名称
    private String uname;
    //密码
    private String upass;
    //性别
    private String usex;
    //邮箱
    private String uemil;
    /** 指定主键 */
    @Override
    protected Serializable pkVal() {
        return this.uid;
    }
    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", usex='" + usex + '\'' +
                ", uemil='" + uemil + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUemil() {
        return uemil;
    }

    public void setUemil(String uemil) {
        this.uemil = uemil;
    }
}
