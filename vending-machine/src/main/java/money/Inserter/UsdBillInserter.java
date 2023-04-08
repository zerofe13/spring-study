package main.java.money.Inserter;

import main.java.LocaleConfig;
import main.java.message.MessageConst;
import main.java.money.MoneyInfo;
import main.java.money.cashcontainer.CashContainer;

import java.util.Arrays;

public class UsdBillInserter implements Inserter{
    private final CashContainer cashContainer;
    private final LocaleConfig lc = LocaleConfig.getLocaleInstance();

    public UsdBillInserter(CashContainer cashContainer) {
        this.cashContainer = cashContainer;
    }
    @Override
    public int insert(String bill) {
        try{
            if(!validate(bill)){
                throw new Exception(MessageConst.INSERT_Bill_ERROR_MESSAGES[lc.getLocaleNum()]);
            }

            //cent 단위로 계산하기 위해 *100
            int iBill = Integer.parseInt(bill.replaceAll("[^0-9]", ""))*100;
            cashContainer.inBill(iBill,1);
            return iBill;
        }catch(Exception e){
            System.err.println(MessageConst.ERROR_MESSAGES[lc.getLocaleNum()] + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean validate(String bill) {
        return !Arrays.stream(MoneyInfo.usdBillList).noneMatch(a -> a == bill);
    }
}
