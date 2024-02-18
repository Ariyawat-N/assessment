package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.entity.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.service.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryRepository lotteryRepository, LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    //Create
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/lotteries")
    public LotteryRequestDto createLottery(@RequestBody Lottery lottery) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.lotteryService.createLottery(lottery);
    }


    //Read
    @GetMapping("/lotteries")
    public LotteryResponseDto listAllLotteries() {
        return this.lotteryService.listAllLotteries();
    }

}
