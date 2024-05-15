package com.BlogApplication.BlogApplicationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Post")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "Post_title", length = 100, nullable = false)
    private String title;
    @Column (name = "Content", length = 150)
    private String  content;
    @Column(name = "Image_name")
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Comment> commentSet = new HashSet<>();

}
