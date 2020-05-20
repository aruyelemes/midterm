package kz.iitu.midterm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Items items;

    @ManyToOne
    private Order order;

    private int quantity;

    public OrderItems(Items item, Order order, int quantity) {
        this.items = item;
        this.order = order;
        this.quantity = quantity;
    }
}
