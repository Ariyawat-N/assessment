package com.kbtg.bootcamp.posttest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 6)
    private String ticket;
    private Integer price;
    private Integer amount;

}
