package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.exception.LotteryExistException;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.service.LotteryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LotteryServiceTest {
    @Mock
    private LotteryRepository lotteryRepository;

    @InjectMocks
    private LotteryService lotteryService;

    @Test
    void testCreateLottery_NewLottery_Success() {
        // Arrange
        LotteryRequestDto requestDto = new LotteryRequestDto("012345", 80, 1);
        when(lotteryRepository.findByTicket("012345")).thenReturn(Optional.empty());
        when(lotteryRepository.save(any(Lottery.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        LotteryResponseDto responseDto = lotteryService.createLottery(requestDto);
        // Assert
        assertEquals("012345", responseDto.getTicket());
        verify(lotteryRepository, times(1)).findByTicket("012345");
        verify(lotteryRepository, times(1)).save(any(Lottery.class));
    }


    @Test
    void testCreateLottery_ExistingLottery_ExceptionThrown() {
        // Arrange
        LotteryRequestDto requestDto = new LotteryRequestDto("012345", 80, 1);
        when(lotteryRepository.findByTicket("012345")).thenReturn(Optional.of(new Lottery()));

        // Act and Assert
        assertThrows(LotteryExistException.class, () -> lotteryService.createLottery(requestDto));
        verify(lotteryRepository, times(1)).findByTicket("012345");
        verify(lotteryRepository, never()).save(any(Lottery.class));
    }
}
