package com.tracklist.tracker.repository;

import com.tracklist.tracker.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p JOIN FETCH p.users")
    List<Posts> findAllWithUsers();


    List<Posts> findByQuestionTitleContaining(String questionTitle);
}
