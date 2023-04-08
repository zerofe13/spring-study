package main.java.controller;

import main.java.AppConfig;
import main.java.LocaleConfig;
import main.java.display.Display;
import main.java.menu.Menu;
import main.java.menu.MenuService;
import main.java.message.MessageConst;
import main.java.service.MoneyService;
import main.java.service.MoneyServiceImpl;

//controller cui 환경에서 사용하기 위해 결과를 콘솔창에 출력
public class CuiVendingMachineController implements VendingMachineController {

    private final MoneyService moneyService;
    private final MenuService menuService;
    private final Display display;
    private LocaleConfig lc = LocaleConfig.getLocaleInstance();

    public CuiVendingMachineController(MoneyService moneyService, MenuService menuService, Display display) {
        this.moneyService = moneyService;
        this.menuService = menuService;
        this.display = display;
    }

    @Override
    public void initVendingMachine(){
        System.out.println(display.initMessage());
    }

    @Override
    public void setLanguage(int num){
        lc.setLocaleNum(num);
    }

    @Override
    public void messageToMenuIntro(){
        System.out.println(display.menuIntro());
        System.out.println(display.menuList(menuService.findAllMenu()));
    }

    @Override
    public void messageToInsertMoney(){
        System.out.println(display.insertMoney());
    }
    @Override
    public void insertCoin(String coin){
        moneyService.inCoinService(coin);
    }
    @Override
    public void insertBill(String bill){
        moneyService.inBillService(bill);
    }
    @Override
    public int findMenuPrice(int menuId){
        Menu findMenu = menuService.findMenu(menuId);
        return findMenu.getPrice();
    }
    @Override
    public int getCurrentCash(){
        return moneyService.getCurrentCash();
    }
    @Override
    public void messageToCurrentCash(){
        System.out.println(display.currentCash(moneyService.getCurrentCash()));
    }
    @Override
    public void messageToSelectedOrder(){
        System.out.println(display.selectOrderAndCancel());
    }

    @Override
    public boolean orderMenu(int menuId){
        Menu findMenu = menuService.findMenu(menuId);
        if(findMenu.getQuantity()<=0){
            menuService.sell(findMenu.getId());
            System.out.println(display.soldOut());
            moneyService.cancelOrder();
            return false;
        }
        int tempCurrent = moneyService.getCurrentCash();
        int refund = moneyService.order(findMenu.getPrice());
        if (refund == tempCurrent){
            System.out.println(display.cancel());
            System.out.println(display.refundCash(refund)+" "+display.currentCash(moneyService.getCurrentCash()));
            return false;
        }
        menuService.sell(findMenu.getId());
        System.out.println(display.success());
        System.out.println(display.refundCash(refund) + " " + display.currentCash(moneyService.getCurrentCash()));
        return true;
        }

    @Override
    public void cancel() {
        int refund = moneyService.cancelOrder();
        System.out.println(display.cancel());
        System.out.println(display.refundCash(refund)+" "+display.currentCash(moneyService.getCurrentCash()));
    }


}
