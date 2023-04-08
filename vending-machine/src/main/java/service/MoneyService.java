package main.java.service;

public interface MoneyService {

    int getCurrentCash();

    void inCoinService(String coin);

    void inBillService(String bill);

    int cancelOrder();

    int order(int price);

    boolean RefundValidateAndExec(int refundCash);
}
