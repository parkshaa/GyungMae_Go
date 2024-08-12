package GyungMaeGo.jpashop.domain;

import GyungMaeGo.jpashop.domain.Item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
        joinColumns = @JoinColumn(name = "category_id"),   //중간테이블
            inverseJoinColumns = @JoinColumn(name = "item_id")  //아이템테이블
    )   //다대다관계이므로 중간에있는 테이블을 조인해줘야함.
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();



}
