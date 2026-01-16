package com.canteen.CanteenManagement;

import com.canteen.CanteenManagement.model.MenuItem;
import com.canteen.CanteenManagement.repository.MenuItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if menu is empty to prevent duplicate insertions
        if(menuItemRepository.count() == 0) {
            List<MenuItem> initialMenu = List.of(
                    new MenuItem(null, "Masala Dosa", "Spicy South Indian crepe", BigDecimal.valueOf(40), "Breakfast", true, 15, null, true),
                    new MenuItem(null, "Idli with Sambar", "Steamed rice cakes with sambar", BigDecimal.valueOf(30), "Breakfast", true, 10, null, true),
                    new MenuItem(null, "Samosa", "Crispy fried potato snack", BigDecimal.valueOf(15), "Snacks", true, 5, null, true),
                    new MenuItem(null, "Paneer Butter Masala", "Cottage cheese in creamy tomato gravy", BigDecimal.valueOf(90), "Lunch", true, 20, null, true),
                    new MenuItem(null, "Chicken Curry", "Spiced chicken curry with gravy", BigDecimal.valueOf(110), "Lunch", true, 25, null, false),
                    new MenuItem(null, "Chole Bhature", "Spicy chickpeas with fried bread", BigDecimal.valueOf(60), "Lunch", true, 20, null, true),
                    new MenuItem(null, "Veg Hakka Noodles", "Stir-fried noodles with vegetables", BigDecimal.valueOf(50), "Lunch", true, 15, null, true),
                    new MenuItem(null, "Lassi", "Refreshing yogurt drink", BigDecimal.valueOf(20), "Beverage", true, 2, null, true),
                    new MenuItem(null, "Tea", "Hot Indian masala tea", BigDecimal.valueOf(10), "All Day", true, 2, null, true),
                    new MenuItem(null, "Gulab Jamun", "Sweet fried milk dumplings in syrup", BigDecimal.valueOf(25), "Dessert", true, 10, null, true)
            );
            menuItemRepository.saveAll(initialMenu);
            System.out.println("Initial menu items loaded.");
        }
    }
}
