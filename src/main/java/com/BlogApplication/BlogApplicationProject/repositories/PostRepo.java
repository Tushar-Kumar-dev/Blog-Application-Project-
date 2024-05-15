package com.BlogApplication.BlogApplicationProject.repositories;

import com.BlogApplication.BlogApplicationProject.entity.Category;
import com.BlogApplication.BlogApplicationProject.entity.Post;
import com.BlogApplication.BlogApplicationProject.entity.User;
import com.BlogApplication.BlogApplicationProject.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

}
