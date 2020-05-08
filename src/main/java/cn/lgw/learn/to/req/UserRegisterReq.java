package cn.lgw.learn.to.req;

import lombok.Data;

@Data
public class UserRegisterReq {
    private String name;
    private String username;
    private String password;
    private String stuId;
    private Integer sex;
    private Integer school;
    private Integer grade;
    private String phone;
}
