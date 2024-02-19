package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.exception.LotteryDuplicateException;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }


    //Create
    public LotteryResponseDto createLottery(LotteryRequestDto requestDto) {
        Optional<Lottery> optional = lotteryRepository.findById(requestDto.getTicket());
        if (optional.isPresent()) {
            throw new LotteryDuplicateException("Duplicated lottery");
        } else {
            return new LotteryResponseDto(Collections.singletonList(lotteryRepository.save(Lottery.builder()
                    .amount(requestDto.getAmount())
                    .price(requestDto.getPrice())
                    .ticket(requestDto.getTicket())
                    .build()
            ).getTicket()));
        }
    }


    //ListAllLotteries
    public LotteryListResponseDto listAllLotteries() {
        return new LotteryListResponseDto(lotteryRepository.findAll()
                .stream()
                .map(Lottery::getTicket).
                collect(Collectors.toList()));
    }
}
