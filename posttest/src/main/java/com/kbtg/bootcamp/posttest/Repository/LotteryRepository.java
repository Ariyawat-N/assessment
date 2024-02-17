package com.kbtg.bootcamp.posttest.Repository;

import com.kbtg.bootcamp.posttest.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery,Long> {
}
