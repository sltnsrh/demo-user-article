package com.salatin.demouser.repository;

import com.salatin.demouser.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "from User u where u.age > :age")
    List<User> findAllUsersWithAgeGte(short age);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT  u.id, u.name, u.age, u.email, u.password FROM users u "
        + "JOIN users_articles ua ON u.id = ua.user_id "
        + "JOIN articles a ON a.id = ua.article_id "
        + "WHERE a.color = :color", nativeQuery = true)
    List<User> findAllUsersWithArticleColor(String color);

    @Query(value = "SELECT u.id, u.name, u.age, u.email, u.password FROM users u "
        + "JOIN users_articles ua ON u.id = ua.user_id "
        + "GROUP BY ua.user_id "
        + "HAVING COUNT(ua.article_id) > :count", nativeQuery = true)
    List<User> findAllUsersWithArticleCountGte(int count);
}
