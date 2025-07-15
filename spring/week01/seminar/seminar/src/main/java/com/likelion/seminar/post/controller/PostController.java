package com.likelion.seminar.post.controller;

import com.likelion.seminar.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {
    private final List<PostDTO> postDTOList;

    // POST /post
    @PostMapping()
    public void createPost(@RequestBody PostDTO postDto){
        System.out.println(postDto.toString());
        this.postDTOList.add(postDto);
    }

    // GET /post
    @GetMapping()
    public List<PostDTO> readPostAll(){
        System.out.println("포스트 전체 조회");
        return this.postDTOList;
    }

    // @PathVariable
    // GET /post/0
    @GetMapping("{id}")
    public PostDTO readPost(@PathVariable("id") int id){
        System.out.println("포스트 단일 조회");
        return this.postDTOList.get(id);
    }

    // @RequestParam
    // GET /post/param?=id
    @GetMapping("param")
    public PostDTO readPost_parma(@RequestParam("id") int id){
        System.out.println("포스트 단일 조회 2");
        return this.postDTOList.get(id);
    }

    // PUT /post/0
    @PutMapping("{id}")
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody PostDTO postDto
    ) {
        PostDTO targetPost = this.postDTOList.get(id);
        if(postDto.getTitle()!=null){
            targetPost.setTitle(postDto.getTitle());
        }
        if(postDto.getContent()!=null){
            targetPost.setContent(postDto.getContent());
        }
        if(postDto.getWriter()!=null){
            targetPost.setWriter(postDto.getWriter());
        }
        this.postDTOList.set(id, targetPost);
    }


    // DELETE /post/0
    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") int id) {
        this.postDTOList.remove(id);
    }
}
