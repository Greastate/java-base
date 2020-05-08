package cn.lgw.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDO {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Boolean bool;
}