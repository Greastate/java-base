package cn.lgw.learn.to.resp;

import lombok.Data;

@Data
public class GeneralUserTO {
    private Long userId;
    private String name;
    private Byte sex;
    private Integer school;
    private Integer grade;
}
