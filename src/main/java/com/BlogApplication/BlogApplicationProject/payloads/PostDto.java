package com.BlogApplication.BlogApplicationProject.payloads;

import com.BlogApplication.BlogApplicationProject.entity.Category;
import com.BlogApplication.BlogApplicationProject.entity.Comment;
import com.BlogApplication.BlogApplicationProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long postId;

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comment =  new HashSet<>();

}
