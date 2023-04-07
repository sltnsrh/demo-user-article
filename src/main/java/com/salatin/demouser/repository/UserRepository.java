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
}
