package com.BlogApplication.BlogApplicationProject.services.Impl;

import com.BlogApplication.BlogApplicationProject.entity.Comment;
import com.BlogApplication.BlogApplicationProject.entity.Post;
import com.BlogApplication.BlogApplicationProject.exception.ResourceNotFoundException;
import com.BlogApplication.BlogApplicationProject.payloads.CommentDto;
import com.BlogApplication.BlogApplicationProject.payloads.PostDto;
import com.BlogApplication.BlogApplicationProject.repositories.CommentRepo;
import com.BlogApplication.BlogApplicationProject.repositories.PostRepo;
import com.BlogApplication.BlogApplicationProject.services.CommentSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentSerivce {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));
        commentRepo.delete(comment);
    }
}
