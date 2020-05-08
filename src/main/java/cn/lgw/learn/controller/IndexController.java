package cn.lgw.learn.controller;

import cn.lgw.learn.domain.condition.TestCondition;
import cn.lgw.learn.mapper.TestMapper;
import cn.lgw.learn.to.resp.RestResponse;
import cn.lgw.learn.util.WebContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by leiguowei on 2018/1/26
 */
@RestController
public class IndexController {

    @Resource
    private TestMapper testMapper;

    @RequestMapping(value = "/")
    public RestResponse showIndex(HttpSession session) {
        return RestResponse.ok("this is index!");
    }

    @RequestMapping(value = "/test")
    public RestResponse test(HttpSession session) {
        return RestResponse.ok(testMapper.selectByExample(new TestCondition()));
    }
}