package main.java.money;

public class MoneyInfo {
    /**
     * 검증을 위한 데이터를
     * cui 환경에 맞게 검증데이터를 만들었습니다.
     */
    public static final String[] krwCoinList = {"10원","50원","100원","500원"};
    public static final int[] krwIntegerCoinList = {10,50,100,500};
    public static final String[] krwBillList = {"1000원","5000원","10000원","50000원"};
    public static final int[] krwIntegerBillList = {1000,5000,10000,50000};

    // cent 단위로 계산
    public static final String[] usdCoinList = {"1cent","5cent","10cent","25cent","50cent"};
    public static final int[] usdIntegerCoinList = {1,5,10,25,50};
    public static final String[] usdBillList = {"1$","5$","10$","20$","50$","100$"};
    public static final int[] usdIntegerBillList = {100,500,1000,2000,5000,10000};
}
