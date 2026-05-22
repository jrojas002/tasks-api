package com.todoapp.tasks.repository;

import com.todoapp.tasks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
