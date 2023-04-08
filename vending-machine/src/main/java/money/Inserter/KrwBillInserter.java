package main.java.money.Inserter;

import main.java.LocaleConfig;
import main.java.message.MessageConst;
import main.java.money.MoneyInfo;
import main.java.money.cashcontainer.CashContainer;

import java.util.Arrays;

public class KrwBillInserter implements Inserter {
    private final CashContainer cashContainer;
    private final LocaleConfig lc = LocaleConfig.getLocaleInstance();

    public KrwBillInserter(CashContainer cashContainer) {
        this.cashContainer = cashContainer;
    }


    @Override
    public int insert(String bill) {
        try{
            if(!validate(bill)){
                throw new Exception(MessageConst.INSERT_Bill_ERROR_MESSAGES[lc.getLocaleNum()]);
            }
            int iBill = Integer.parseInt(bill.replaceAll("[^0-9]", ""));
            //내부 현금컨테이너로 접근
            cashContainer.inBill(iBill,1);
            return iBill;
        }catch(Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[lc.getLocaleNum()] + e.getMessage());
            return 0;
        }
    }

    /**
     *  지폐 투입기에 잘못된 지폐가 투입된 경우 오류발생
     *  krwBillList 를 이용하여 검증
     */

    @Override
    public boolean validate(String bill) {
        return !Arrays.stream(MoneyInfo.krwBillList).noneMatch(a -> a == bill);
    }
}
