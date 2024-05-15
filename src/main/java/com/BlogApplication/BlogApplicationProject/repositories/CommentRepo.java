package com.BlogApplication.BlogApplicationProject.repositories;

import com.BlogApplication.BlogApplicationProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
