package com.BlogApplication.BlogApplicationProject.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.AbstractDocument;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElement;
    private Integer totalPage;
    private Boolean last;
    private List<PostDto> conetnt;


}
