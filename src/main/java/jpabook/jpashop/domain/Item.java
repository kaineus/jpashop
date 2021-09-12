package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
@Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id", nullable = false)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    // 비지니스 로직 //
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void reduceStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if(resultStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        }
        this.stockQuantity = resultStock;
    }
}