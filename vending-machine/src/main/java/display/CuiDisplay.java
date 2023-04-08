package main.java.display;

import main.java.LocaleConfig;
import main.java.menu.Menu;
import main.java.message.MessageConst;

import java.util.List;

public class CuiDisplay implements Display{

    private LocaleConfig lc = LocaleConfig.getLocaleInstance();

    @Override
    //초기 화면
    public String initMessage() {
        return MessageConst.INIT_MASSAGES;
    }


    @Override
    //메뉴소개
    public String menuIntro() {
        return MessageConst.INTRO_MESSAGES[lc.getLocaleNum()];
    }
    @Override
    //메뉴 리스트
    public String menuList(List<Menu> menuList){
        String Result = "";
        for (Menu menu : menuList) {
            Result += menu.getId()+". ";
            Result += menu.getMenuName(lc.getLocaleNum()) + " ";
            Result += MessageConst.PRICE[lc.getLocaleNum()]+" : " + menu.getPrice() + " ";
            Result += MessageConst.QUANTITY[lc.getLocaleNum()]+" : "+menu.getQuantity()+"\n";
        }
        return Result;
    }
    @Override
    //현재금액
    public String currentCash(int cash){
        return MessageConst.CURRENT_CASH[lc.getLocaleNum()]+" : "+cash;
    }
    @Override
    //반환금액
    public String refundCash(int cash){
        return MessageConst.REFUND_CASH[lc.getLocaleNum()]+" : "+cash;
    }
    @Override
    //주문성공
    public String success(){
        return MessageConst.SUCCESS_ORDER_MESSAGES[lc.getLocaleNum()];
    }
    @Override
    //주문 취소
    public String cancel(){
        return MessageConst.CANCEL_ORDER_MESSAGES[lc.getLocaleNum()];
    }
    @Override
    //재고부족
    public String soldOut(){
        return MessageConst.QUANTITY_ERROR_MESSAGES[lc.getLocaleNum()];
    }
    @Override
    public String insertMoney(){return MessageConst.INSERT_MONEY_MESSAGE[lc.getLocaleNum()];}
    @Override
    public String selectOrderAndCancel(){return "1."+MessageConst.ORDER[lc.getLocaleNum()]+" "+"2."+MessageConst.CANCEL[lc.getLocaleNum()];}
}
