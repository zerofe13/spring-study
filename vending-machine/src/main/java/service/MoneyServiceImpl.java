package main.java.service;

import main.java.LocaleConfig;
import main.java.message.MessageConst;
import main.java.money.Inserter.Inserter;
import main.java.money.cashcontainer.CashContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 현재 사용자가 투입한 금액
 * 거스름돈,결제등 돈과 관련된 서비스
 */
public class MoneyServiceImpl implements MoneyService {
    private final CashContainer cashContainer;
    private final Inserter billInserter;
    private final Inserter coinInserter;

    public MoneyServiceImpl(CashContainer cashContainer, Inserter billInserter, Inserter coinInserter) {
        this.cashContainer = cashContainer;
        this.billInserter = billInserter;
        this.coinInserter = coinInserter;
    }

    private LocaleConfig lc = LocaleConfig.getLocaleInstance();

    private int currentCash = 0;
    @Override
    public int getCurrentCash() {
        return currentCash;
    }
    public void setCurrentCash(int currentCash) {
        this.currentCash = currentCash;
    }

    //돈이 투입될시 Inserter 를 통해 검증 및 형변환 후  CashContainer 에 저장
    @Override
    public void inCoinService(String coin){
        int insertedCoin = coinInserter.insert(coin);
        setCurrentCash(getCurrentCash()+insertedCoin);
    }

    @Override
    public void inBillService(String bill){
        int insertedBill = billInserter.insert(bill);
        setCurrentCash(getCurrentCash()+insertedBill);
    }

    @Override
    public int cancelOrder(){
        int refundCash = getCurrentCash();
        RefundValidateAndExec(refundCash);
        setCurrentCash(0);
        return refundCash;
    }
//거스름돈이 없을경우 주문취소
    @Override
    public int order(int price){
        int refundCash = getCurrentCash()-price;
        if(RefundValidateAndExec(refundCash)) {
            setCurrentCash(0);
            return refundCash;
        }else{
            return cancelOrder();
        }
    }



    @Override
    public boolean RefundValidateAndExec(int refundCash){
        List<Integer> allBills = cashContainer.findAllBills();
        Collections.sort(allBills,Collections.reverseOrder());
        Map<Integer,Integer> resultCoin =new HashMap<>();
        Map<Integer,Integer> resultBill =new HashMap<>();
        int tempCash = refundCash;

        for (int bill : allBills) {
            int count = tempCash/bill;
            if(cashContainer.findBillQuantity(bill)>=count){
                resultBill.put(bill,count);
                tempCash-=bill*count;
            }
        }

        List<Integer> allCoins = cashContainer.findAllCoins();
        Collections.sort(allCoins,Collections.reverseOrder());

        for (int coin : allCoins) {
            int count = tempCash/coin;
            if(cashContainer.findCoinQuantity(coin) >= count){
                resultCoin.put(coin,count);
                tempCash-=coin*count;
            }
        }

        try{
            if(tempCash !=0 ){ // 거스름돈이 부족한 경우
                throw new Exception(MessageConst.REFUND_ERROR_MESSAGES[lc.getLocaleNum()]);
            }
            //반환 실행
            resultBill.keySet().forEach(key -> {cashContainer.outBill(key, resultBill.get(key));});
            resultCoin.keySet().forEach(key -> {cashContainer.outCoin(key, resultCoin.get(key));});
            return true;
        }catch (Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[lc.getLocaleNum()] + e.getMessage());
            return false;
        }

    }
}
