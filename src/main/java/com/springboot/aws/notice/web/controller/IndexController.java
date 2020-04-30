package com.springboot.aws.notice.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.aws.notice.security.dto.SessionUser;
import com.springboot.aws.notice.security.loginsession.LoginUser;
import com.springboot.aws.notice.web.domain.User;
import com.springboot.aws.notice.web.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final NoticeService noticeSerivce;
    private final HttpSession httpSession;
    
    @GetMapping("/")
    public ModelAndView index(@LoginUser SessionUser user) {
        ModelAndView model = new ModelAndView("index");
        model.addObject("posts", noticeSerivce.selectAll());
        
        if (user != null) {
            model.addObject("userName", user.getName());
        }
        
        return model;
    }
    
    @GetMapping("/notice/post")
    public String post() {
        return "post";
    }
    
    @GetMapping("/notice/update/{id}")
    public ModelAndView select(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("update"); 
        model.addObject("posts", noticeSerivce.select(id));
       
        return model;
    }
}