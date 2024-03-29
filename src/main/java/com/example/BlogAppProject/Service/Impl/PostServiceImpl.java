package com.example.BlogAppProject.Service.Impl;

import com.example.BlogAppProject.DTO.PostDto;
import com.example.BlogAppProject.DTO.PostResponse;
import com.example.BlogAppProject.Entity.PostEntity;
import com.example.BlogAppProject.Exception.ResourceNotFoundException;
import com.example.BlogAppProject.Repository.PostRepository;
import com.example.BlogAppProject.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        PostEntity postEntity=mapToEntity(postDto);
        PostEntity newPost=postRepository.save(postEntity);

        //convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }


    @Override
    public  PostResponse GetAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        //Create Page able instance
        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<PostEntity> postEntity = postRepository.findAll(pageable);

        //get content for page object
        List<PostEntity> listOfPost = postEntity.getContent();

        List<PostDto> content = listOfPost.stream().map(postEntity1 -> mapToDTO(postEntity1)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(postEntity.getNumber());
        postResponse.setPageSize(postEntity.getSize());
        postResponse.setTotalElements(postEntity.getTotalElements());
        postResponse.setTotalPages(postEntity.getTotalPages());
        postResponse.setLast(postEntity.isLast());

        return postResponse;


    }

    @Override
    public PostDto getPostById(long id) {
        PostEntity postEntity=postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PostEntity","id",id));
        return mapToDTO(postEntity);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id from the database
        PostEntity postEntity=postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PostEntity","id",id));
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());

        PostEntity updatePost=postRepository.save(postEntity);
        return mapToDTO(updatePost);
    }

    //Convert Entity into DTO
    private PostDto mapToDTO(PostEntity postEntity){
        PostDto postDto= new PostDto();

        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        return postDto;
    }

    //Converted DTO to Entity
    private PostEntity mapToEntity(PostDto postDto){
        PostEntity postEntity=new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        return postEntity;
    }
}
