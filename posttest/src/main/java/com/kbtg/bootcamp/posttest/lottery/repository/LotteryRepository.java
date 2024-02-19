package com.kbtg.bootcamp.posttest.lottery.repository;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LotteryRepository extends JpaRepository<Lottery, String> {
    @Query("SELECT l FROM Lottery l WHERE l.amount > 0")
    List<Lottery> findAll();

    Optional<Lottery> findByTicket(String ticket);
}
