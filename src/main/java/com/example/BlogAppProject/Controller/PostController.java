package com.example.BlogAppProject.Controller;

import com.example.BlogAppProject.DTO.PostDto;
import com.example.BlogAppProject.DTO.PostResponse;
import com.example.BlogAppProject.Service.PostService;
import com.example.BlogAppProject.Utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get")
    public String getPost(){

        return "Connect Successfully";
    }

    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }


    @GetMapping("/getAll")
    public PostResponse GetAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.GetAllPosts(pageNo,pageSize, sortBy, sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    //update post by id rest api
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id ){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
}
