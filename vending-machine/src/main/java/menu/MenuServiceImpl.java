package main.java.menu;

import main.java.LocaleConfig;
import main.java.message.MessageConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;

    //의존성 주입을 위한 생성자
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    //메뉴추가
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    //메뉴찾기
    public Menu findMenu(int menuId) {
        Menu findMenu = menuRepository.findById(menuId);
        return findMenu;
    }

    @Override
    //메뉴 판매
    public void sell(int menuId) {
        Menu findMenu = menuRepository.findById(menuId);
        try {
            if (findMenu.getQuantity() <= 0) {
                throw new Exception(MessageConst.QUANTITY_ERROR_MESSAGES[LocaleConfig.getLocaleInstance().getLocaleNum()]);
            }
            menuRepository.updateQuantity(findMenu.getId(), findMenu.getQuantity()-1);
            return;
        }catch (Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[LocaleConfig.getLocaleInstance().getLocaleNum()]+e.getMessage());
            return;
        }

    }

    @Override
    //재고 추가
    public void addQuantity(int menuId, int quantity) {
        Menu findMenu = menuRepository.findById(menuId);
        menuRepository.updateQuantity(findMenu.getId(), findMenu.getQuantity()+quantity);
    }

    @Override
    //가격 변경
    public void changePrice(int menuId, int price) {
        menuRepository.updatePrice(menuId,price);
    }

    @Override
    public List<Menu> findAllMenu() {
        return menuRepository.findAll();
    }

}
