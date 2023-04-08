package main.java.money.cashcontainer;

import main.java.money.MoneyInfo;

import java.util.*;

public class KrwCashContainer implements CashContainer{
    /**
     * key: cash
     * value: quantity
     * 자판기 내부의 현금을 관리
     */
    private static Map<Integer,Integer> coinQuantity = new HashMap<Integer,Integer>(){
        {
            Arrays.stream(MoneyInfo.krwIntegerCoinList).forEachOrdered(i -> put(i, 0));
        }
    };
    private static Map<Integer,Integer> billQuantity = new HashMap<Integer,Integer>(){
        {
            Arrays.stream(MoneyInfo.krwIntegerBillList).forEachOrdered(i -> put(i, 0));
        }
    };

    @Override
    public void inCoin(int coin,int quantity) {
        int findCoinQuantity = coinQuantity.get(coin);
        coinQuantity.replace(coin,findCoinQuantity+quantity);
    }

    @Override
    public void outCoin(int coin,int quantity) {
        int findCoinQuantity = coinQuantity.get(coin);
        coinQuantity.replace(coin,findCoinQuantity-quantity);

    }

    @Override
    public void inBill(int bill,int quantity) {
        int findBillQuantity = billQuantity.get(bill);
        billQuantity.replace(bill,findBillQuantity+quantity);
    }

    @Override
    public void outBill(int bill,int quantity) {
        int findCoinQuantity = billQuantity.get(bill);
        billQuantity.replace(bill,findCoinQuantity-quantity);
    }
    @Override
    //동전 수량
    public int findCoinQuantity(int key) {
        return coinQuantity.get(key);
    }
    @Override
    //지폐수량
    public int findBillQuantity(int key) {
        return billQuantity.get(key);
    }
    @Override
    //자판기내 전채 금액
    public int totalCash(){
        int coinSum = coinQuantity.keySet().stream().mapToInt(i -> coinQuantity.get(i) * i).sum();
        int billSum = billQuantity.keySet().stream().mapToInt(i -> billQuantity.get(i) * i).sum();

        return coinSum+billSum;
    }
    @Override
    public List<Integer> findAllCoins() {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : coinQuantity.keySet()) {
            result.add(i);
        }
        return result;
    }
    @Override
    public List<Integer> findAllBills() {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : billQuantity.keySet()) {
            result.add(i);
        }
        return result;
    }
}
