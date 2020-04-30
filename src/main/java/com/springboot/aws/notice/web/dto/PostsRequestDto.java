package com.springboot.aws.notice.web.dto;

import com.springboot.aws.notice.web.domain.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsRequestDto {
    private String title;
    private String content;
    private String author;
    
    @Builder 
    public PostsRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public Posts toEntity() {
        return Posts.builder()
                          .title(title)
                          .content(content)
                          .author(author)
                          .build();
    }
}
