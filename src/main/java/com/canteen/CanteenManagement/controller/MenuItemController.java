package com.canteen.CanteenManagement.controller;

import com.canteen.CanteenManagement.model.MenuItem;
import com.canteen.CanteenManagement.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Both USER and ADMIN can view all menu items
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> items = menuItemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    // Both USER and ADMIN can view an item by id
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Both USER and ADMIN can filter by category
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> items = menuItemRepository.findByCategory(category);
        return ResponseEntity.ok(items);
    }

    // Both USER and ADMIN can view available menu items
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems() {
        List<MenuItem> items = menuItemRepository.findByAvailableTrue();
        return ResponseEntity.ok(items);
    }

    // Both USER and ADMIN can view vegetarian menu items
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/vegetarian")
    public ResponseEntity<List<MenuItem>> getVegetarianMenuItems() {
        List<MenuItem> items = menuItemRepository.findByVegetarianTrue();
        return ResponseEntity.ok(items);
    }

    // Only ADMIN can create a menu item
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem savedItem = menuItemRepository.save(menuItem);
        return ResponseEntity.ok(savedItem);
    }

    // Only ADMIN can create bulk menu items
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bulk")
    public ResponseEntity<List<MenuItem>> createMenuItems(@RequestBody List<MenuItem> menuItems) {
        List<MenuItem> savedItems = menuItemRepository.saveAll(menuItems);
        return ResponseEntity.ok(savedItems);
    }

    // Only ADMIN can update menu item
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updatedItem) {
        return menuItemRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setPrice(updatedItem.getPrice());
                    item.setCategory(updatedItem.getCategory());
                    item.setAvailable(updatedItem.getAvailable());
                    item.setPreparationTime(updatedItem.getPreparationTime());
                    item.setImageUrl(updatedItem.getImageUrl());
                    item.setVegetarian(updatedItem.getVegetarian());
                    menuItemRepository.save(item);
                    return ResponseEntity.ok(item);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Only ADMIN can delete menu item
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        if (!menuItemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
