package main.java.menu;

import java.util.List;

public interface MenuService {
    void addMenu(Menu menu);
    Menu findMenu(int menuId);
    void sell(int menuId);
    void addQuantity(int menuId,int quantity);
    void changePrice(int menuId,int price);
    List<Menu> findAllMenu();
}
