package com.kbtg.bootcamp.posttest.userticket.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.exception.LotteryUnavailableException;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.userticket.dto.UserTicketsRequestDto;
import com.kbtg.bootcamp.posttest.userticket.entity.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.repository.UserTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;

    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

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

    public UserTicketResponseDto getLotteriesByUserId(String userId) {
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto();

        List<UserTicket> byUser = userTicketRepository.findByUserId(userId);
        List<String> tickets = byUser.stream().map(b -> b.getLottery().getTicket()).toList();

        userTicketResponseDto.setTickets(tickets);
        userTicketResponseDto.setCount(tickets.size());
        userTicketResponseDto.setTotalPrice(sumAllPrice(byUser));

        return userTicketResponseDto;
    }

    private Integer sumAllPrice(List<UserTicket> byUser) {
        Integer sum = 0;
        for(UserTicket ticket : byUser){
            sum += ticket.getLottery().getPrice();
        }
        return sum;
    }


}
