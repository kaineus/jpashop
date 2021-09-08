package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_category")
@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

}