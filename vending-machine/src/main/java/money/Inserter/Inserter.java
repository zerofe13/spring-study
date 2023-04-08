package main.java.money.Inserter;

public interface Inserter {
    /**
     *검증에러 체크
     * CashContainer 로 돈 저장
     */
    int insert(String bill);

    /**
     *투입된 돈이 유효한지 검증
     */
    boolean validate(String bill);
}
