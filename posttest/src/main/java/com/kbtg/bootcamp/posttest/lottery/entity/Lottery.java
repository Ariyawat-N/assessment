package com.kbtg.bootcamp.posttest.lottery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "ticket_id", length = 6)
    @Size(min = 6, max = 6)
    private String ticket;

    private Integer price;

    private Integer amount;

}
