package com.BlogApplication.BlogApplicationProject.controllers;

import com.BlogApplication.BlogApplicationProject.entity.Comment;
import com.BlogApplication.BlogApplicationProject.payloads.ApiResponse;
import com.BlogApplication.BlogApplicationProject.payloads.CommentDto;
import com.BlogApplication.BlogApplicationProject.services.CommentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentSerivce commentSerivce;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId){

        CommentDto commentDto1 = commentSerivce.createComment(commentDto, postId);
        return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
        commentSerivce.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment delete sucessfully",true), HttpStatus.OK);
    }

}
