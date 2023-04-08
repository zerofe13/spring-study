package main.java;

import main.java.controller.CuiVendingMachineController;
import main.java.controller.VendingMachineController;
import main.java.menu.Menu;
import main.java.message.MessageConst;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CuiVendingMachine {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        AppConfig ap = new AppConfig();
        VendingMachineController vmc = ap.vendingMachineController();
        /**
         * 테스트를 위한 더미 데이터 추가
         * USD 는 cent 단위를 기준으로 했습니다.
         * AppConfig 를 수정하여 쉽게 usd 투입기로 변환 가능
         */
        Map<Integer, String> cash = KwrDummyData(ap);
//        Map<Integer, String> cash = UsdDummyData(ap);

        /**
         * 언어선택->메뉴선택->금액투입->주문결정
         * error
         * 금액투입 - 잘못된 화폐 투입
         * 주문결정 - 거스름돈 부족 , 재고 부족,
         */
        while(true) {
            System.out.println();
            int inputLocale = 0;
            //초기 언어를 선택하는 화면
            vmc.initVendingMachine();
            inputLocale = sc.nextInt();
            //cui 입력 오류
            while(inputLocale != 1 && inputLocale != 2) {
                System.out.println(MessageConst.REENTER_MESSAGE[LocaleConfig.getLocaleInstance().getLocaleNum()]);
                inputLocale = sc.nextInt();
            }
            //언어 선택
            vmc.setLanguage(inputLocale);
            //메뉴 메세지
            vmc.messageToMenuIntro();
            int inputMenu = 0;
            //메뉴 선택
            inputMenu=sc.nextInt();
            //cui 입력오류
            while(1>inputMenu || inputMenu>ap.menuService().findAllMenu().size()){
                System.out.println(MessageConst.REENTER_MESSAGE[LocaleConfig.getLocaleInstance().getLocaleNum()]);
                inputMenu = sc.nextInt();
            }
            //금액 투입
            vmc.messageToInsertMoney();
            String cashInfoMessage = "";
            for (Integer key : cash.keySet()) {
                cashInfoMessage += key +"."+cash.get(key)+" ";
            }
            System.out.println(cashInfoMessage);

            while(vmc.findMenuPrice(inputMenu)>=vmc.getCurrentCash()){
                int inputCash = sc.nextInt();
                if(inputCash>=1 && inputCash<=5){
                    vmc.insertBill(cash.get(inputCash));
                    vmc.messageToCurrentCash();
                }else if(inputCash>=6 && inputCash<=10){
                    vmc.insertCoin(cash.get(inputCash));
                    vmc.messageToCurrentCash();
                }else{
                    //cui 입력 오류
                    System.out.println(MessageConst.REENTER_MESSAGE[LocaleConfig.getLocaleInstance().getLocaleNum()]);
                    vmc.messageToCurrentCash();
                    continue;
                }
            }
            //주문 결정
            vmc.messageToSelectedOrder();
            int inputOrder = 0;
            while(inputOrder !=1 && inputOrder!=2) {
                inputOrder = sc.nextInt();
                if (inputOrder == 1) {
                    vmc.orderMenu(inputMenu);
                } else if (inputOrder == 2) {
                    vmc.cancel();
                } else {
                    //cui 입력 오류
                    System.out.println(MessageConst.REENTER_MESSAGE[LocaleConfig.getLocaleInstance().getLocaleNum()]);
                }
            }
        }
    }
//원화 테스트용 더미데이터를 초기화 시키는 메서드
    private static Map<Integer, String> KwrDummyData(AppConfig ap) {
        //메뉴 초기화
        ap.menuService().addMenu(new Menu("아메리카노","Americano",3500,100));
        ap.menuService().addMenu(new Menu("라떼","latte",3800,100));
        ap.menuService().addMenu(new Menu("카라멜 마끼아또","Caramel Macchiato",3800,100));
        ap.menuService().addMenu(new Menu("물","water",700,100));
        ap.menuService().addMenu(new Menu("오렌지 쥬스","orange juice",2000,100));
        ap.menuService().addMenu(new Menu("에스프레소","Espresso",3330,10));
        // 재고 오류
        ap.menuService().addMenu(new Menu("녹차","Green tea",1400,0));
        //거스름돈 초기화
        ap.cashContainer().inBill(50000,1);
        ap.cashContainer().inBill(10000,10);
        ap.cashContainer().inBill(5000,20);
        ap.cashContainer().inBill(1000,30);
        ap.cashContainer().inCoin(500,20);
        ap.cashContainer().inCoin(100,20);
        ap.cashContainer().inCoin(50,10);
        //거스름돈 오류를 위해 수량 0
        ap.cashContainer().inCoin(10,0);
        //현실에는 화폐가 정해져 있기 때문에 돈에 대한 정보를 map 을 통해 초기화
        //
        Map<Integer,String> cash = new TreeMap<>();
        cash.put(1,"50000원");
        cash.put(2,"10000원");
        cash.put(3,"5000원");
        cash.put(4,"1000원");
        //지폐투입기 오류검출
        cash.put(5,"100$");
        cash.put(6,"500원");
        cash.put(7,"100원");
        cash.put(8,"50원");
        cash.put(9,"10원");
        //동전투입기 오류
        cash.put(10,"25cent");
        return cash;
    }
    //달러 테스트용
    private static Map<Integer, String> UsdDummyData(AppConfig ap) {
        //메뉴 초기화
        ap.menuService().addMenu(new Menu("아메리카노","Americano",350,100));
        ap.menuService().addMenu(new Menu("라떼","latte",380,100));
        ap.menuService().addMenu(new Menu("카라멜 마끼아또","Caramel Macchiato",380,100));
        ap.menuService().addMenu(new Menu("물","water",70,100));
        ap.menuService().addMenu(new Menu("오렌지 쥬스","orange juice",200,100));
        ap.menuService().addMenu(new Menu("에스프레소","Espresso",333,10));
        // 재고 오류
        ap.menuService().addMenu(new Menu("녹차","Green tea",140,0));
        //거스름돈 초기화
        ap.cashContainer().inBill(10000,10);
        ap.cashContainer().inBill(5000,10);
        ap.cashContainer().inBill(2000,10);
        ap.cashContainer().inBill(1000,20);
        ap.cashContainer().inBill(100,20);
        ap.cashContainer().inCoin(50,30);
        ap.cashContainer().inCoin(25,20);
        ap.cashContainer().inCoin(10,20);
        ap.cashContainer().inCoin(5,10);
        ap.cashContainer().inCoin(1,10);
        //현실에는 화폐가 정해져 있기 때문에 돈에 대한 정보를 map 을 통해 초기화
        //
        Map<Integer,String> cash = new TreeMap<>();
        cash.put(1,"100$");
        cash.put(2,"50$");
        cash.put(3,"20$");
        cash.put(4,"10$");
        cash.put(5,"1$");
        cash.put(6,"50cent");
        cash.put(7,"25cent");
        cash.put(8,"10cent");
        cash.put(9,"5cent");
        cash.put(10,"1cent");

        return cash;
    }
}
