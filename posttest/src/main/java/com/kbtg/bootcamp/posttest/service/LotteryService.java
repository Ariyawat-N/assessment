package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.entity.Lottery;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }


    //Create
    public LotteryRequestDto createLottery(Lottery lottery){
        return new LotteryRequestDto(lotteryRepository.save(lottery).getTicket());
    }



    //ListAllLotteries
    public LotteryResponseDto listAllLotteries(){
        return new LotteryResponseDto(lotteryRepository.findAll()
                .stream()
                .map(Lottery::getTicket).
                collect(Collectors.toList()));
    }
}
