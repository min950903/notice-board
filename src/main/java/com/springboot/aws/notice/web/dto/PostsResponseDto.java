package com.springboot.aws.notice.web.dto;

import java.io.Serializable;

import com.springboot.aws.notice.web.domain.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostsResponseDto implements Serializable {
    private String title;
    private String content;
    private String author;
    
    public PostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
