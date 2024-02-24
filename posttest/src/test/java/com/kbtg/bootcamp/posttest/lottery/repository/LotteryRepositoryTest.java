package com.kbtg.bootcamp.posttest.lottery.repository;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class LotteryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LotteryRepository lotteryRepository;

    @Test
    @Transactional
    void testFindByTicket_ExistingTicket_ReturnsLottery() {
        // Arrange
        Lottery lottery = new Lottery("012345", 80, 1);
        entityManager.persistAndFlush(lottery);

        // Act
        Optional<Lottery> found = lotteryRepository.findByTicket("012345");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("012345", found.get().getTicket());
    }

    @Test
    void testFindByTicket_NonExistingTicket_ReturnsEmptyOptional() {
        // Act
        Optional<Lottery> found = lotteryRepository.findByTicket("nonexistent");

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    @Transactional
    void testFindAll_AmountGreaterThanZero_ReturnsLotteries() {
        // Arrange
        Lottery lottery1 = new Lottery("012345", 80, 1);
        Lottery lottery2 = new Lottery("543210", 80, 1);
        entityManager.persistAndFlush(lottery1);
        entityManager.persistAndFlush(lottery2);

        // Act
        List<Lottery> lotteries = lotteryRepository.findAll();

        // Assert
        assertEquals(2, lotteries.size());
        assertTrue(lotteries.stream().allMatch(l -> l.getAmount() > 0));
    }
}
