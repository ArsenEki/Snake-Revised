package com.snake.app;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a usr given a username.
     * @param username of the user.
     * @return the user if found.
     */
    User findUserByUserName(String username);

    /**
     * Retrieve top 5 users.
     * @return list of users.
     */
    List<User> findTop5ByOrderByHighscoreDesc();

    /**
     * Truncates database.
     */
    @Transactional
    @Modifying
    @Query(
                //value = "truncate table users;"
                //nativeQuery = true
                value = "truncate TABLE users RESTART IDENTITY;",
            nativeQuery = true
    )
    void truncate();

    @Transactional
    void deleteByUserName(String username);




}
