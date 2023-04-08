package main.java.controller;

import main.java.menu.Menu;

public interface VendingMachineController {
    void initVendingMachine();

    void setLanguage(int num);

    void messageToMenuIntro();

    void messageToInsertMoney();

    int findMenuPrice(int menuId);

    int getCurrentCash();

    void messageToCurrentCash();

    void insertCoin(String coin);

    void insertBill(String bill);

    void messageToSelectedOrder();

    boolean orderMenu(int menuId);

    void cancel();
}
