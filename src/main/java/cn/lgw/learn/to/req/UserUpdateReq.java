package cn.lgw.learn.to.req;

import lombok.Data;

@Data
public class UserUpdateReq {
    private String password;
    private String name;
    private Integer sex;
    private Integer school;
    private Integer grade;
    private String phone;
    private Integer status;
    private Integer borrowAmount;
    private Integer canBorrowAmount;
}
