package com.BlogApplication.BlogApplicationProject.services.Impl;

import com.BlogApplication.BlogApplicationProject.entity.Category;
import com.BlogApplication.BlogApplicationProject.entity.Post;
import com.BlogApplication.BlogApplicationProject.entity.User;
import com.BlogApplication.BlogApplicationProject.exception.ResourceNotFoundException;
import com.BlogApplication.BlogApplicationProject.payloads.PostDto;
import com.BlogApplication.BlogApplicationProject.payloads.PostResponse;
import com.BlogApplication.BlogApplicationProject.repositories.CategoryRepo;
import com.BlogApplication.BlogApplicationProject.repositories.PostRepo;
import com.BlogApplication.BlogApplicationProject.repositories.UserRepo;
import com.BlogApplication.BlogApplicationProject.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServices {

    @Autowired
    PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Long userId, Long categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id",userId));

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post savedPost = postRepo.save(post);

        PostDto postDto1 = modelMapper.map(savedPost, PostDto.class);

        return postDto1;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));

        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));

        return  modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> content = pagePost.getContent();

        List<PostDto> postDtos = content.stream()
                .map(e->modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();

        postResponse.setConetnt(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setLast(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
        List<Post> posts =  postRepo.findByCategory(category);
        List<PostDto> postDtoList =  posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("userId", "Id", userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtoList = posts.stream()
                .map(e->modelMapper.map(e,PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
         List<Post> post = postRepo.searchByTitle(keyword);
         List<PostDto> postDto = post.stream().map(e->modelMapper.map(e,PostDto.class)).collect(Collectors.toList());
         return postDto;
    }
}
