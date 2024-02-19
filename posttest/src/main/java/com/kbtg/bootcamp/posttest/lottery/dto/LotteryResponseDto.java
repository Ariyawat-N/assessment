package com.kbtg.bootcamp.posttest.lottery.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LotteryResponseDto {
    private List<String> tickets;

}
