package main.java;
import main.java.controller.CuiVendingMachineController;
import main.java.controller.VendingMachineController;
import main.java.display.CuiDisplay;
import main.java.display.Display;
import main.java.menu.MenuRepository;
import main.java.menu.MenuRepositoryImpl;
import main.java.menu.MenuService;
import main.java.menu.MenuServiceImpl;
import main.java.money.Inserter.Inserter;
import main.java.money.Inserter.KrwBillInserter;
import main.java.money.Inserter.KrwCoinInserter;
import main.java.money.Inserter.UsdBillInserter;
import main.java.money.cashcontainer.CashContainer;
import main.java.money.cashcontainer.KrwCashContainer;

import main.java.money.cashcontainer.UsdCashContainer;
import main.java.service.MoneyServiceImpl;

/**
 * 의존성역전을 위한 config
 * 부품수정시 config 만 수정하면 손쉽게 수정가능
 * AppConfig 를 이용함으로써 확장에는 열려있고 변경에는 닫혀있는 ocp 원칙
 * 의존성을 역전시킴으로 dip 원칙을 지킬수있다.
 */

public class AppConfig {
    public VendingMachineController vendingMachineController(){
        return new CuiVendingMachineController(moneyService(),menuService(),display());
    }

    public MenuService menuService(){
        return new MenuServiceImpl(menuRepository());
    }

    public MenuRepository menuRepository() {return new MenuRepositoryImpl();}


    public Display display(){
        return new CuiDisplay();
    }

    public MoneyServiceImpl moneyService(){return new MoneyServiceImpl(cashContainer(),billInserter(),coinInserter());}

    /**
     * usd 로 변화시 수정
     *
     */
    public CashContainer cashContainer(){
        return new KrwCashContainer();
//        return new UsdCashContainer();
    }

    public Inserter billInserter(){
        return new KrwBillInserter(cashContainer());
//        return new UsdBillInserter(cashContainer());
    }
    public Inserter coinInserter(){
        return new KrwCoinInserter(cashContainer());
//        return new UsdBillInserter(cashContainer());
    }


}
