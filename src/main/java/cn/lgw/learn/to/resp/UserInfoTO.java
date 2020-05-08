package cn.lgw.learn.to.resp;

import lombok.Data;

@Data
public class UserInfoTO {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 学生学号
     */
    private String stuId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户性别。0->男；1->女；
     */
    private Byte sex;

    /**
     * 用户学院
     */
    private Integer school;

    /**
     * 用户年级
     */
    private Integer grade;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户状态。0：正常。1：无法借阅。2：已注销
     */
    private Byte status;

    /**
     * 用户当前借阅数量
     */
    private Integer borrowAmount;

    /**
     * 用户可借书籍的最大数量
     */
    private Integer canBorrowAmount;
}
