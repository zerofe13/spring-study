package main.java.money.cashcontainer;

import java.util.List;

public interface CashContainer {
    void inCoin(int coin,int quantity);
    void outCoin(int coin,int quantity);
    void inBill(int bill,int quantity);
    void outBill(int bill,int quantity);
    int findCoinQuantity(int key);
    int findBillQuantity(int key);
    int totalCash();
    List<Integer> findAllCoins();
    List<Integer> findAllBills();
}
