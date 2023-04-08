package main.java.message;

public class MessageConst {
    /**
     * 메세지 국제화를 위해
     * 상수를 이용하여 해결하였다.
     *
     */
    public static final String[] PRICE={"","가격","price"};
    public static final String[] QUANTITY={"","수량","quantity"};
    public static final String[] CURRENT_CASH={"","현재금액","Current cash "};
    public static final String[] REFUND_CASH={"","반환금액","Refund cash "};
    public static final String[] ORDER={"","주문","order"};
    public static final String[] CANCEL={"","취소","cancel"};


    public static final String INIT_MASSAGES = "언어를 선택해주세요\nPlease select a language \n\n 1.한국어 2.english ";
    public static final String[] INTRO_MESSAGES = {"","메뉴를 선택해주세요.","Please select a menu."};

    public static final String[] INSERT_MONEY_MESSAGE = {"","돈을 투입해주세요.","Please insert money"};

    public static final String[] SUCCESS_ORDER_MESSAGES ={"","주문이 완료되었습니다.","order has been completed."};
    public static final String[] CANCEL_ORDER_MESSAGES = {"","주문이 취소되었습니다.","order was canceled."};

    public static final String[] ERROR_MESSAGES = {"","오류 정보 : ","Error Info : "};
    public static final String[] REFUND_ERROR_MESSAGES = {"","거스름돈이 부족합니다","Not enough change."};
    public static final String[] INSERT_COIN_ERROR_MESSAGES = {"","잘못된 동전을 투입했습니다.","The wrong coin was inserted."};
    public static final String[] INSERT_Bill_ERROR_MESSAGES = {"","잘못된 지폐을 투입했습니다.","The wrong Bill was inserted."};
    public static final String[] QUANTITY_ERROR_MESSAGES = {"","상품 재고가 부족합니다.","Sold out."};


    public static final String[] REENTER_MESSAGE = {"","재입력 해주세요","Please re-enter"};

}
