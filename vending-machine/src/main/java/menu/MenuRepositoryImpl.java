package main.java.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRepositoryImpl implements MenuRepository{
    //db가 없기 때문에 map 을 이용하여 Menu 관리
    private static Map<Integer, Menu> store =new HashMap<>();
    private static int sequence = 0;

    @Override
    public void save(Menu menu) {
        menu.setId(++sequence);
        store.put(menu.getId(), menu);
    }

    @Override
    public Menu findById(int menuId) {
        return store.get(menuId);
    }

    @Override
    public void updateQuantity(int menuId,int quantity) {
        Menu findMenu = findById(menuId);
        findMenu.setQuantity(quantity);
    }

    @Override
    public void updatePrice(int menuId,int price) {
        Menu findMenu = findById(menuId);
        findMenu.setPrice(price);
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<Menu>(store.values());
    }

}
