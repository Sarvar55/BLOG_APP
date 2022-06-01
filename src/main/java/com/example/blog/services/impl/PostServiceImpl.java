package com.example.blog.services.impl;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;

    public PostServiceImpl(PostRepo postRepo, ModelMapper modelMapper, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Category category = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createPost = this.postRepo.save(post);

        return this.modelMapper.map(createPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

        post.setAddedDate(postDto.getAddedDate());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

        this.postRepo.delete(post);

    }

    @Override
    public PostResponse<PostDto> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortBy.contains("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(pageable);

        PostResponse<PostDto> response = new PostResponse<>();

        response.setContent(pagePost.getContent()
                .stream().map(post -> modelMapper
                        .map(post, PostDto.class))
                .collect(Collectors.toList()));

        response.setPageSize(pageSize);
        response.setPageNumber(pageNumber);
        response.setTotalElements(pagePost.getTotalElements());
        response.setTotalPages(pagePost.getTotalPages());
        response.setLast(pagePost.hasPrevious());


        return response;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);

        return   posts
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.postRepo
                .findByUser(user)
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return this.postRepo.
                findByTitleContaining(keyword)
                .stream().map(post -> modelMapper
                        .map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
