package com.example.BlogAppProject.Service;

import com.example.BlogAppProject.DTO.PostDto;
import com.example.BlogAppProject.DTO.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse GetAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);
}
