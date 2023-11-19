package com.tracklist.tracker.controller;
import com.tracklist.tracker.api.PostsApi;
import com.tracklist.tracker.dto.PostContentDTO;
import com.tracklist.tracker.dto.RetrieveAllPostsContentInner;
import com.tracklist.tracker.service.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostsControllerImpl implements PostsApi {

    private Logger log = LoggerFactory.getLogger(getClass());

    private PostsService postsService;

    public PostsControllerImpl(PostsService postsService){
        log.info("Post controller initialised");
        this.postsService = postsService;
    }

    @Override
    public ResponseEntity<PostContentDTO> postsPostContentPost(@RequestBody PostContentDTO postContentDTO) {
        log.info("Posting content initialised");

        PostContentDTO postContent = postsService.createPost(postContentDTO);

        return new ResponseEntity<>(postContent, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<RetrieveAllPostsContentInner>> postsGetAllPostsContentGet() {
        log.info("Retriving all post controller called");
        List<RetrieveAllPostsContentInner> allPostsContent = postsService.getAllPosts();

        return new ResponseEntity<>(allPostsContent, HttpStatus.OK);
    }
}