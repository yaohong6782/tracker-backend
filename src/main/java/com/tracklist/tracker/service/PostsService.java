package com.tracklist.tracker.service;

import com.tracklist.tracker.dto.PostContentDTO;
import com.tracklist.tracker.dto.PostsDTO;
import com.tracklist.tracker.dto.RetrieveAllPostsContentInner;
import com.tracklist.tracker.entity.Posts;
import com.tracklist.tracker.entity.Users;
import com.tracklist.tracker.mapper.PostsMapper;
import com.tracklist.tracker.mapper.UserMapper;
import com.tracklist.tracker.repository.PostsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private PostsRepository postsRepository;


    private PostsMapper postsMapper;

    private UserMapper userMapper;
    private UserService userService;


    public PostsService( PostsRepository postsRepository, PostsMapper postsMapper, UserMapper userMapper, UserService userService) {
        this.postsRepository = postsRepository;
        this.postsMapper = postsMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    public PostContentDTO createPost(PostContentDTO postContentDTO){
        log.info("Create post function called");

        log.info("post content dto : {} : " , postContentDTO.toString());
        log.info("Post content DTO values : {}, {} , {} , {} ", postContentDTO.getUserId());

        Posts post = new Posts();
        post.setQuestionTitle(postContentDTO.getQuestionTitle());
        post.setQuestionNumber(postContentDTO.getQuestionNumber());
        post.setContent(postContentDTO.getContent());

        // Set only the user ID in the Posts entity
        Users user = new Users();
        user.setId(postContentDTO.getUserId());

        log.info("user : {} ", user.toString());
        post.setUsers(user);

        Posts savedPost = postsRepository.save(post);
        log.info("Saved post dto values : {} " , savedPost.toString());

        PostContentDTO savedPostContentDTO = postsMapper.postsEntityToPostsDTO(savedPost);

        return savedPostContentDTO;
    }

//    public PostContentDTO createPost(PostContentDTO postContentDTO){
//        log.info("Create post function called");
//
//        log.info("post content dto : {} : " , postContentDTO.toString());
//        log.info("Post content DTO values : {}, {} , {} , {} ", postContentDTO.getUserId());
//
//        Posts post = new Posts();
//        post.setQuestionTitle(postContentDTO.getQuestionTitle());
//        post.setQuestionNumber(postContentDTO.getQuestionNumber());
//        post.setContent(postContentDTO.getContent());
//        UsersDTO usersDTO = userSignUpService.findById(postContentDTO.getUserId());
//        log.info("users dto username find by id : {} ", usersDTO.getUsername());
//
//        Users user = userMapper.mapUsersDTOToUsersEntity(usersDTO);
//        log.info("user : {} " , user.toString());
//        post.setUsers(user);
//        Posts savedPost = postsRepository.save(post);
//        log.info("Saved post dto values : {} " , savedPost.toString());
//        PostContentDTO savedPostContentDTO = postsMapper.postsEntityToPostsDTO(savedPost);
//
//        return savedPostContentDTO;
//    }

    public List<RetrieveAllPostsContentInner> getAllPosts() {
        log.info("get all post function called");
        List<RetrieveAllPostsContentInner> allPostsContents = new ArrayList<>();

        List<Posts> postsList = postsRepository.findAllWithUsers();

        List<PostsDTO> postsDTOList = postsList
                .stream()
                .map(postsMapper::postsToPostsDTO)
                .collect(Collectors.toList());

//        for (Posts posts : postsList) {
//            PostsDTO postsDTO = new PostsDTO();
//            postsDTO.setId(posts.getId());
//            postsDTO.setQuestionTitle(posts.getQuestionTitle());
//            postsDTO.setQuestionNumber(posts.getQuestionNumber());
//            postsDTO.setContent(posts.getContent());
//
//            // Users to UsersDTO
//            postsDTO.setUsersDTO(userMapper.usersToUsersDTO(posts.getUsers()));
//
//            postsDTOList.add(postsDTO);
//        }

        allPostsContents = postsDTOList
                .stream()
                .map(postsMapper::mapToRetrieveAllPostsContent)
                .collect(Collectors.toList());

        return allPostsContents;
    }

    public List<RetrieveAllPostsContentInner> getAllPostsRespectiveToUser(String username) {
        log.info("Get all post respective to user function called");
        List<RetrieveAllPostsContentInner> allPostscontents =  new ArrayList<>();
        List<Posts> postsList = postsRepository.findAllPostsRespectiveToUser(username);
        List<PostsDTO> postsDTOList = postsList
                .stream()
                .map(postsMapper::postsToPostsDTO)
                .collect(Collectors.toList());

        allPostscontents = postsDTOList
                .stream()
                .map(postsMapper::mapToRetrieveAllPostsContent)
                .collect(Collectors.toList());

        return allPostscontents;
    }
}
