package kz.iitu.midterm.controllers;

import kz.iitu.midterm.entities.Items;
import kz.iitu.midterm.repositories.ItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping(value = "/allItems")
    public String index(ModelMap model, @RequestParam(name = "page", defaultValue = "1") int page){

        int pageSize = 10;

        if(page<1){
            page = 1;
        }

        int totalItems = itemsRepository.countAllByDeletedAtNull();
        int tabSize = (totalItems+pageSize-1)/pageSize;

        Pageable pageable = PageRequest.of(page-1, pageSize);
        List<Items> items = itemsRepository.findAllByDeletedAtNull(pageable);
        model.addAttribute("itemler", items);
        model.addAttribute("tabSize", tabSize);
        return "index";
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") int price
    ){

        Items item = new Items(name, price);
        itemsRepository.save(item);

        return "redirect:/items/allItems";
    }



    @GetMapping(path = "/details/{id}")
    public String details(ModelMap model, @PathVariable(name = "id") Long id){

        Optional<Items> item = itemsRepository.findByIdAndDeletedAtNull(id);
        model.addAttribute("item", item.orElse(new Items("No Name", 0)));

        return "details";
    }

    @PostMapping(path = "/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String delete(@RequestParam(name = "id") Long id){
        Items item = itemsRepository.findByIdAndDeletedAtNull(id).get();
        item.setDeletedAt(new Date());
        itemsRepository.save(item);
        return "redirect:/items/allItems";
    }


    @PostMapping(path = "/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String update(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "price") int price){
        Items item = itemsRepository.findByIdAndDeletedAtNull(id).get();
        item.setName(name);
        item.setPrice(price);
        item.setUpdatedAt(new Date());
        itemsRepository.save(item);
        return "redirect:/items/allItems";
    }

    @GetMapping(path = "/updateItem")
    public String registerPage(Model model){

        return "updateItem";

    }

}



