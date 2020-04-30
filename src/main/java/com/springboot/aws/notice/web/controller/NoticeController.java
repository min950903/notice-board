package com.springboot.aws.notice.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.aws.notice.web.dto.PostsRequestDto;
import com.springboot.aws.notice.web.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;
    
    @PostMapping("/api/notice")
    public Long post(@RequestBody PostsRequestDto requestDto) {
        return noticeService.post(requestDto);
    }
    
    @PutMapping("/api/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }
    
    @DeleteMapping("/api/notice/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        
        return id;
    }
}
