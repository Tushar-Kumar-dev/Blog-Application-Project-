package com.BlogApplication.BlogApplicationProject.services;

import com.BlogApplication.BlogApplicationProject.entity.Post;
import com.BlogApplication.BlogApplicationProject.payloads.PostDto;
import com.BlogApplication.BlogApplicationProject.payloads.PostResponse;

import java.util.List;

public interface PostServices {

    PostDto createPost(PostDto postDto,Long userId, Long categoryId);
    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);
    PostDto getPostById(Long postId);

    PostResponse getAllPosts(Integer pageNumber, Integer PageSize, String sortBy, String sortDir);

    List<PostDto> getPostByCategory(Long categoryId);
    List<PostDto> getPostByUser(Long userId);

    List<PostDto> searchPosts(String keyword);
}
