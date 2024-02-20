package com.kbtg.bootcamp.posttest.userticket.controller;

import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketsRequestDto;
import com.kbtg.bootcamp.posttest.userticket.service.UserTicketService;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserTicketController {
    private UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<UserTicketsRequestDto> buyLotteries(
            @PathVariable("userId")
            @Pattern(regexp = "^0\\d{9}$", message = "must be a number only  and start with '0'")
            @Size(min = 10, max = 10, message = "must be a 10 digit")
            String userId,
            @PathVariable("ticketId")
            @Pattern(regexp = "^\\d{6}$", message = "must be a number only")
            @Size(min = 6, max = 6, message = "must be a 6 digit")
            String ticketId) {
        return new ResponseEntity<>(userTicketService.buyLotteries(userId, ticketId), HttpStatus.OK);
    }
}
