package kz.iitu.midterm.controllers;

import kz.iitu.midterm.entities.Items;
import kz.iitu.midterm.entities.Order;
import kz.iitu.midterm.entities.OrderItems;
import kz.iitu.midterm.entities.Users;
import kz.iitu.midterm.repositories.ItemsRepository;
import kz.iitu.midterm.repositories.OrderItemsRepository;
import kz.iitu.midterm.repositories.OrderRepository;
import kz.iitu.midterm.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/createOrder")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    public String createOrder(
            @RequestParam(name = "userID") Long userID
    ){

        Users user = userRepository.findById(userID).orElse(null);
        Order order = new Order(user);

        orderRepository.save(order);
        return "redirect:/items/allItems";
    }

    @PostMapping(value = "/addToOrder")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    public String addToOrder(
            @RequestParam(name = "itemID") Long itemId,
            @RequestParam(name = "orderID") Long orderId,
            @RequestParam(name = "quantity") int quantity
    ){

        Items item = itemsRepository.findByIdAndDeletedAtNull(itemId).orElse(null);
        Order order = orderRepository.findById(orderId).orElse(null);
        OrderItems orderItems = new OrderItems(item, order, quantity);

        orderItemsRepository.save(orderItems);
        return "redirect:/items/allItems";
    }



    @GetMapping(value = "/allOrders")
    public String ordersList(ModelMap model, Long id){
        List<OrderItems> items = orderItemsRepository.findAllById(id);
        model.addAttribute("orderList", items);
        return "ordersList";
    }


    @GetMapping(path = "/order")
    public String orderPage(Model model){

        return "order";

    }
}
