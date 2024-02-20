package com.kbtg.bootcamp.posttest.lottery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lottery")
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", length = 6)
    private Long id;
    @Size(min = 6, max = 6)
    @Column(name = "ticket")
    private String ticket;
    @Column(name  = "price")
    private Integer price;
    @Column(name  = "amount")
    private Integer amount;

}
