package com.example.carhelper.ListItems;

public class Item {
    /**
     * Заголовок
     */
    String header;

    /**
     * Подзаголовок
     */
    String subHeader;


    /**
     * Конструктор создает новый элемент в соответствии с передаваемыми
     * параметрами:
     * @param h - заголовок элемента
     * @param s - подзаголовок
     */
    public Item(String h, String s){
        this.header=h;
        this.subHeader=s;
    }


    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getSubHeader() {
        return subHeader;
    }
    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }

}