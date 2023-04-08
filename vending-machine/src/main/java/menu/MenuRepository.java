package main.java.menu;

import java.util.List;

public interface MenuRepository {
    void save(Menu menu);
    Menu findById(int menuId);
    void updateQuantity(int menuId,int quantity);
    void updatePrice(int menuId,int price);
    List<Menu> findAll();
}
