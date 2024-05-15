package com.BlogApplication.BlogApplicationProject.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long categoryId;
    @NotEmpty
    @Size(min = 3)
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
}
