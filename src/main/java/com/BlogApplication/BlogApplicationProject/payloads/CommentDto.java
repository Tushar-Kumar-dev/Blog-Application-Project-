package com.BlogApplication.BlogApplicationProject.payloads;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    private Long id ;
    private String content;
}
