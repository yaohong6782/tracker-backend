package com.tracklist.tracker.repository;

import com.tracklist.tracker.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p JOIN FETCH p.users")
    List<Posts> findAllWithUsers();


    @Query("SELECT p from Posts p JOIN FETCH p.users u WHERE u.username = :username")
    List<Posts> findAllPostsRespectiveToUser(@Param("username") String username);

    List<Posts> findByQuestionTitleContaining(String questionTitle);
}
