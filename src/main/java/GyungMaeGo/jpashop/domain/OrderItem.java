package GyungMaeGo.jpashop.domain;

import GyungMaeGo.jpashop.domain.Item.Item;
import GyungMaeGo.jpashop.domain.Item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class OrderItem {


    @Id@GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;  //주문 가격

    private int count;  // 주문 수량

}
