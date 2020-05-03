package kz.iitu.midterm.services.impl;

import kz.iitu.midterm.entities.Items;
import kz.iitu.midterm.repositories.ItemsRepository;
import kz.iitu.midterm.services.ItemService;

public class ItemServiceImpl implements ItemService {
    private ItemsRepository itemsRepository;

    @Override
    public Items update(Items item) {
        if(item.getId() == null){
            return itemsRepository.save(item);
        }
        return null;
    }
}
