package main.java.menu;

public class Menu {
    private int id;
    private String menuName;
    private String menuName_En;
    private int price;
    private int quantity;

    public Menu(String menuName, String menuName_En, int price, int quantity) {
        this.menuName = menuName;
        this.menuName_En = menuName_En;
        this.price = price;
        this.quantity = quantity;
    }

    public Menu(String menuName, int price, int quantity) {
        this.menuName = menuName;
        this.menuName_En = "";
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName_En() {
        return menuName_En;
    }

    public void setMenuName_En(String menuName_En) {
        this.menuName_En = menuName_En;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMenuName(int localeNum){
        if(localeNum == 1){
            return menuName;
        }else{
            return menuName_En != "" ? menuName_En : menuName;
        }
    }

}
