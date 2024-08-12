package GyungMaeGo.jpashop.domain.Item;

import GyungMaeGo.jpashop.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //상속 전략 세우기. , 한테이블에 다 떼려박기.
@DiscriminatorColumn(name="dtype")
@Getter
@Setter
public abstract class Item {


    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
