package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id", nullable = false)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_category_item",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

}