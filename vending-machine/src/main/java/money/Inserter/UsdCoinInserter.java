package main.java.money.Inserter;

import main.java.LocaleConfig;
import main.java.message.MessageConst;
import main.java.money.MoneyInfo;
import main.java.money.cashcontainer.CashContainer;

import java.util.Arrays;

public class UsdCoinInserter implements Inserter{

    private final CashContainer cashContainer;
    private final LocaleConfig lc = LocaleConfig.getLocaleInstance();

    public UsdCoinInserter(CashContainer cashContainer) {
        this.cashContainer = cashContainer;
    }

    @Override
    public int insert(String coin) {
        try{
            if(!validate(coin)){
                throw new Exception(MessageConst.INSERT_COIN_ERROR_MESSAGES[lc.getLocaleNum()]);
            }
            int iCoin = Integer.parseInt(coin.replaceAll("[^0-9]", ""));
            cashContainer.inCoin(iCoin,1);
            return iCoin;
        }catch(Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[lc.getLocaleNum()] + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean validate(String coin) {
        return !Arrays.stream(MoneyInfo.usdCoinList).noneMatch(a -> a == coin);
    }
}
