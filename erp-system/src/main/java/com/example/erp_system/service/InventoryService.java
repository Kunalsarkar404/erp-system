package com.example.erp_system.service;

import com.example.erp_system.entity.InventoryItem;
import com.example.erp_system.repository.InventoryRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    public InventoryItem getItemById(long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public InventoryItem addItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryItem updateItem(Long id, InventoryItem itemDetails) {
        InventoryItem item = getItemById(id);
        item.setName(itemDetails.getName());
        item.setQuantity(itemDetails.getQuantity());
        item.setCategory(itemDetails.getCategory());
        item.setPrice(itemDetails.getPrice());
        return inventoryRepository.save(item);
    }

    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}
