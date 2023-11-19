package com.tracklist.tracker.mapper;

import com.tracklist.tracker.dto.PostContentDTO;
import com.tracklist.tracker.dto.PostsDTO;
import com.tracklist.tracker.dto.RetrieveAllPostsContentInner;
import com.tracklist.tracker.entity.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostsMapper {

    Posts postsDTOToPostsEntity(PostContentDTO postsDTO);

    @Mapping(source="users.id", target="userId")
    PostContentDTO postsEntityToPostsDTO(Posts posts);

    @Mapping(source = "users", target = "usersDTO")
    PostsDTO postsToPostsDTO(Posts posts);

    @Mapping(source="usersDTO", target="users")
    @Mapping(target="users.password", ignore = true)
    RetrieveAllPostsContentInner mapToRetrieveAllPostsContent (PostsDTO postsDTO);

}
