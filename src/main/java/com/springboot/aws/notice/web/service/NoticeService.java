package com.springboot.aws.notice.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springboot.aws.notice.web.domain.Posts;
import com.springboot.aws.notice.web.domain.PostsRepository;
import com.springboot.aws.notice.web.dto.PostsRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final PostsRepository postsRepository;

    public List<Posts> selectAll() {
        return postsRepository.findAll();
    }
    
    public Posts select(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> 
                                new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
    }

    @Transactional
    public Long post(PostsRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    
    @Transactional
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> 
                    new IllegalArgumentException("해당 id가 없습니다. id = " + id));
        
        posts.update(requestDto.getTitle(), requestDto.getContent());
        
        return id;
    }
    
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> 
                                         new IllegalArgumentException("해당 id가 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
    
    /*
     * private PostsResponseDto findById(Long id) { Posts posts =
     * postsRepository.findById(id) .orElseThrow(() -> new
     * IllegalArgumentException("해당 id가 없습니다. id = " + id)); return new
     * PostsResponseDto(posts); }
     */
}
