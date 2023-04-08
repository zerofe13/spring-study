package main.java.display;

import main.java.menu.Menu;

import java.util.List;

public interface Display {

    String initMessage();

    String menuIntro();

    String menuList(List<Menu> menuList);

    String currentCash(int cash);

    String refundCash(int cash);

    String cancel();

    String success();

    String soldOut();

    String insertMoney();

    String selectOrderAndCancel();
}
