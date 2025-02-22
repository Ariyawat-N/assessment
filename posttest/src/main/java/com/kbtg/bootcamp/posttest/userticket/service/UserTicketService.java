package com.kbtg.bootcamp.posttest.userticket.service;

import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.exception.LotteryUnavailableException;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketsRequestDto;
import com.kbtg.bootcamp.posttest.userticket.entity.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.exception.InvalidUserTicketException;
import com.kbtg.bootcamp.posttest.userticket.repository.UserTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;

    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    // Buy Lottery
    public UserTicketsRequestDto buyLotteries(String userId, String ticketId) {
        UserTicket userTicket = new UserTicket();

        Optional<Lottery> optionalLottery = lotteryRepository.findById((ticketId));
        Lottery lottery;

        if (optionalLottery.isEmpty()) {
            throw new LotteryUnavailableException("Lottery Unavailable Exception");
        } else {
            lottery = optionalLottery.get();
            if (lottery.getAmount() <= 0) {
                throw new LotteryUnavailableException("Lottery Unavailable Exception");
            }

        }
        lottery.setTicket(ticketId);

        userTicket.setUserId(userId);
        userTicket.setLottery(lottery);

        UserTicket saved = userTicketRepository.save(userTicket);
        return new UserTicketsRequestDto(saved.getId());
    }

    //Get Lotteries By UserId and Sum total Price
    public UserTicketResponseDto getLotteriesByUserId(String userId) {
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto();

        List<UserTicket> byUser = userTicketRepository.findByUserId(userId);
        List<String> tickets = byUser.stream().map(b -> b.getLottery().getTicket()).toList();

        userTicketResponseDto.setTickets(tickets);
        userTicketResponseDto.setCount(tickets.size());
        userTicketResponseDto.setTotalPrice(sumTotalPrice(byUser));

        return userTicketResponseDto;
    }

    private Integer sumTotalPrice(List<UserTicket> byUser) {
        Integer totalPrice = 0;
        for (UserTicket ticket : byUser) {
            totalPrice += ticket.getLottery().getPrice();
        }
        return totalPrice;
    }

    //Sell Lottery
    public LotteryResponseDto deleteLotteriesByUserId(String userId, String ticketId) {
        List<UserTicket> byUser = userTicketRepository.findByUserIdAndTicketId(userId, ticketId);
        if (!byUser.isEmpty()) {
            userTicketRepository.delete(byUser.get(0));
            Optional<Lottery> optional = lotteryRepository.findById(ticketId);
            if (optional.isPresent()) {
                Lottery lottery = optional.get();
                lottery.setAmount(1);
                lotteryRepository.save(lottery);
                return new LotteryResponseDto(ticketId);
            } else {
                throw new InvalidUserTicketException("Invalid userId or ticketId");
            }
        } else {
            throw new InvalidUserTicketException("Invalid userId or ticketId");
        }
    }
}
