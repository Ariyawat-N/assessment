package com.kbtg.bootcamp.posttest.lottery.controller;

import com.kbtg.bootcamp.posttest.lottery.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.service.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    //Create lottery
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/lotteries")
    public ResponseEntity<LotteryResponseDto> createLottery(@RequestBody LotteryRequestDto requestDto) {

        return new ResponseEntity<>(new LotteryResponseDto(lotteryService.createLottery(requestDto).getTicket()), HttpStatus.CREATED);
    }


    //List all lotteries
    @GetMapping("/lotteries")
    public ResponseEntity<LotteryListResponseDto> listAllLotteries() {
        return new ResponseEntity<>(lotteryService.listAllLotteries(),HttpStatus.OK);
    }

}
