package com.BlogApplication.BlogApplicationProject.services;

import com.BlogApplication.BlogApplicationProject.payloads.CommentDto;

public interface CommentSerivce {

    CommentDto createComment(CommentDto commentDto, Long postId);
    void deleteComment(Long commentId);
}
