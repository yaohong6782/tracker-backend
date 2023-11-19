package com.tracklist.tracker.repository;

import com.tracklist.tracker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByUsernameIgnoreCase(String username);

//    @Query("SELECT u from Users u WHERE u.username = :username")
//    Optional<Users> findByUsername(@Param("username") String username);

    Optional<Users> findByUsername(String username);

}
