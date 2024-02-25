package com.kbtg.bootcamp.posttest.userticket.controller;

import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketsRequestDto;
import com.kbtg.bootcamp.posttest.userticket.service.UserTicketService;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserTicketController {
    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    //By lottery
    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<UserTicketsRequestDto> buyLotteries(
            @PathVariable("userId")
            //Regexp for only number and start with '0'
            @Pattern(regexp = "^0\\d{9}$")
            @Size(min = 10, max = 10)
            String userId,
            @PathVariable("ticketId")
            //Regexp for number only
            @Pattern(regexp = "^\\d{6}$")
            @Size(min = 6, max = 6, message = "must be a 6 digit")
            String ticketId) {
        return new ResponseEntity<>(userTicketService.buyLotteries(userId, ticketId), HttpStatus.OK);
    }

    //List all lottery by userId
    @GetMapping("/users/{userId}/lotteries")
    public ResponseEntity<UserTicketResponseDto> getLotteriesByUserId(
            @PathVariable("userId")
            //Regexp for only number and start with '0'
            @Pattern(regexp = "^0\\d{9}$")
            @Size(min = 10, max = 10)
            String userId) {
        return new ResponseEntity<>(userTicketService.getLotteriesByUserId(userId), HttpStatus.OK);
    }


    //Sell lottery
    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<LotteryResponseDto> deleteLotteriesByUserId(
            @PathVariable("userId")
            //Regexp for only number and start with '0'
            @Pattern(regexp = "^0\\d{9}$")
            @Size(min = 10, max = 10)
            String userId,
            @PathVariable("ticketId")
            //Regexp for number only
            @Pattern(regexp = "^\\d{6}$")
            @Size(min = 6, max = 6)
            String ticketId) {
        return new ResponseEntity<>(userTicketService.deleteLotteriesByUserId(userId, ticketId), HttpStatus.OK);
    }

}
