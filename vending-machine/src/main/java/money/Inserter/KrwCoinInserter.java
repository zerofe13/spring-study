package main.java.money.Inserter;

import main.java.LocaleConfig;
import main.java.message.MessageConst;
import main.java.money.MoneyInfo;
import main.java.money.cashcontainer.CashContainer;

import java.util.Arrays;

public class KrwCoinInserter implements Inserter{
    private final CashContainer cashContainer;
    private final LocaleConfig lc = LocaleConfig.getLocaleInstance();

    public KrwCoinInserter(CashContainer cashContainer) {
        this.cashContainer = cashContainer;
    }

    @Override
    public int insert(String coin) {
        try{
            if(!validate(coin)){
                throw new Exception(MessageConst.INSERT_COIN_ERROR_MESSAGES[lc.getLocaleNum()]);
            }
            int iCoin = Integer.parseInt(coin.replaceAll("[^0-9]", ""));
            //내부 컨테이너로 접근
            cashContainer.inCoin(iCoin,1);
            return iCoin;
        }catch(Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[lc.getLocaleNum()] + e.getMessage());
            return 0;
        }
    }

    /**
     *  지폐 투입기에 잘못된 지폐가 투입된 경우 오류발생
     *  krwCoinList 를 이용하여 검증
     */

    @Override
    public boolean validate(String coin) {
        return !Arrays.stream(MoneyInfo.krwCoinList).noneMatch(a -> a == coin);
    }
}
