package com.kbtg.bootcamp.posttest.Repository;

import com.kbtg.bootcamp.posttest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
