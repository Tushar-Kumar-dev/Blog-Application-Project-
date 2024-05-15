package com.BlogApplication.BlogApplicationProject.controllers;

import com.BlogApplication.BlogApplicationProject.config.AppConstant;
import com.BlogApplication.BlogApplicationProject.entity.Post;
import com.BlogApplication.BlogApplicationProject.payloads.ApiResponse;
import com.BlogApplication.BlogApplicationProject.payloads.PostDto;
import com.BlogApplication.BlogApplicationProject.payloads.PostResponse;
import com.BlogApplication.BlogApplicationProject.services.FileService;
import com.BlogApplication.BlogApplicationProject.services.PostServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServices postServices;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>  createPost(@RequestBody PostDto post, @PathVariable Long userId, @PathVariable Long categoryId){
        PostDto post1 = postServices.createPost(post, userId,categoryId);
        return  new ResponseEntity<PostDto>(post1, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/getPostByUser")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
        List<PostDto> posts = postServices.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{catId}/getPostByCategory")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long catId){
        List<PostDto> posts = postServices.getPostByCategory(catId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PutMapping("post/{postId}")
    public ResponseEntity<PostDto> updateUser(@RequestBody PostDto postDto,@PathVariable Long postId){
        PostDto postDto1 =postServices.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/deletePost")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId){
        postServices.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Delete Successfully", true),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto postDto = postServices.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageNumber" ,defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY,required = false) String  sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR, required = false) String sortDir){
        PostResponse  postResponse = postServices.getAllPosts(pageNumber, pageSize, sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
            List<PostDto> postDto = postServices.searchPosts("%"+keywords+"%");
            return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image, @PathVariable Long postId) throws IOException {
        PostDto postDto = postServices.getPostById(postId);
        String fileName = fileService.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatedPost = postServices.updatePost(postDto,postId);
        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

}
