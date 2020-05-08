package cn.lgw.learn.to.req;

import lombok.Data;

@Data
public class LoginReq {
    private String username;
    private String passport;
    private String code;
}
