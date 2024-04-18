package com.example.capstone3.Repository;

import com.example.capstone3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
User findUserByUserId (Integer userId);
}
