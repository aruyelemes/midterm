package kz.iitu.midterm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_items")
public class Items extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "items", fetch = FetchType.EAGER)
    private List<OrderItems> orderItems;

    public Items(String name, int price) {
        this.name=name;
        this.price=price;
    }
}
