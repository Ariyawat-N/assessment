package com.kbtg.bootcamp.posttest.Controller;

import com.kbtg.bootcamp.posttest.Repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.entity.Lottery;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("admin/lotteries")
public class LotteryController {

    private final LotteryRepository lotteryRepository;

    public LotteryController(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }


    @GetMapping
    public ArrayList<Lottery> listAllLottery() {
        return (ArrayList<Lottery>) lotteryRepository.findAll();
    }

    @PostMapping
    public Lottery createLottery(@RequestBody Lottery lottery){
        String data = lottery.toString();
        return lotteryRepository.save(lottery);
    }


}
