package com.springboot.aws.notice.web.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;
    
    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글_불러오기() {
        //given
        String title = "title";
        String content = "content";
        
        //when
        postsRepository.save(Posts.builder()
                                  .title(title)
                                  .content(content)
                                  .build());
        
        //then
        List<Posts> allPosts = postsRepository.findAll();
        assertThat(allPosts.get(0).getTitle(), is(equalTo(title)));
        assertThat(allPosts.get(0).getContent(), is(equalTo(content)));
    }
}
