package GyungMaeGo.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delievery {

    @Id@GeneratedValue
    @Column(name="delievery_id")
    private Long id;

    @OneToOne(mappedBy = "delievery" , fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address adress;

    @Enumerated(EnumType.STRING)
    private DelieveryStatus status; // READY , COMP

}
