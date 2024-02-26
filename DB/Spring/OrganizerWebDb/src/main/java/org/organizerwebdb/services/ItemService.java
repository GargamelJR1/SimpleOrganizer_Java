package org.organizerwebdb.services;

import org.organizerwebdb.models.Item;
import org.organizerwebdb.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService
{
    @Autowired
    private ItemRepository ItemRepository;

    public List<Item> getAllItems() {
        return ItemRepository.findAll();
    }

    public Optional<Item> getOneItem(Long id) {
        return ItemRepository.findById(id);
    }

    public Optional<Item> createItem(Item item) {
        Item item_saved = ItemRepository.save(item);
        return Optional.of(item_saved);
    }

    public Optional<Item> modifyItem(Item item) {
        Optional<Item> o_item_to_modify = ItemRepository.findById(item.getId());
        if (o_item_to_modify.isPresent()) {
            Item item_to_modify = o_item_to_modify.get();
            item_to_modify.setName(item.getName());
            item_to_modify.setDescription(item.getDescription());
            item_to_modify.setCategory(item.getCategory());
            Item item_saved = ItemRepository.save(item_to_modify);
            return Optional.of(item_saved);
        }
        else {
            return Optional.empty();
        }
    }

    public boolean deleteItem(Long id) {
        return ItemRepository.deleteItemById(id) == 1;
    }

    public void deleteAllItems() {
        ItemRepository.deleteAll();
    }

}
