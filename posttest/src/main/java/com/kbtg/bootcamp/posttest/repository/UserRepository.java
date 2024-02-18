package com.kbtg.bootcamp.posttest.repository;

import com.kbtg.bootcamp.posttest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}