package com.salatin.demouser.repository;

import com.salatin.demouser.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "from User u where u.age > :age")
    List<User> findAllUsersWithAgeGreaterThan(short age);

    @Query(value = "SELECT  u.id, u.name, u.age, u.email, u.password FROM users u\n" +
        "JOIN users_articles ua ON u.id = ua.user_id\n" +
        "JOIN articles a ON a.id = ua.article_id\n" +
        "WHERE a.color = :color", nativeQuery = true)
    List<User> findAllUsersWithArticleColor(String color);
}
