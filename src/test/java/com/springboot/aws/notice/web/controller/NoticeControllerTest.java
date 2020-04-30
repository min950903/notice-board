package com.springboot.aws.notice.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.aws.notice.web.domain.Posts;
import com.springboot.aws.notice.web.domain.PostsRepository;
import com.springboot.aws.notice.web.dto.PostsRequestDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = 
    SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticeControllerTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private PostsRepository postsRepository;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void deleteAll() throws Exception {
        postsRepository.deleteAll();
    }
    
    
    @Test
    public void 게시글_등록한다() {
        //given
        String title = "title";
        String content = "content";
        
        PostsRequestDto requestDto = PostsRequestDto.builder()
                                                      .title(title)
                                                      .content(content)
                                                      .build();
        
        String url = "http://localhost:" + port + "/api/notice/post";
        
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        
        //then
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(responseEntity.getBody(), greaterThan(0L));
        
        List<Posts> allPosts = postsRepository.findAll();
        assertThat(allPosts.get(0).getTitle(), is(equalTo(title)));
        assertThat(allPosts.get(0).getContent(), is(equalTo(content)));
    }
}