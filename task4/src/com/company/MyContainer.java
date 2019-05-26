package com.company;

import java.util.ArrayList;

public class MyContainer {

    //milliseconds timeout
    private static final Integer TIMEOUT = 3000;
    //number that indicate queue or stack
    private static final Integer NUMBER = 3;
    private ArrayList<Item> list;

    public MyContainer() {
        this.list = new ArrayList<Item>();
    }

    public void addItem() {
        Item item = new Item();
        list.add(item);
    }

    public void removeTimeout() {
        for (Item i:list
             ) {
            if(System.currentTimeMillis()-i.getCreateTime()>TIMEOUT){
                list.remove(i);
            }else {
                break;
            }
        }
    }

    public String getItem() {
        removeTimeout();
        if (list.isEmpty()) {
            return "nothing";
        }
        if (list.size() <= NUMBER) {
            Item tmp = list.get(0);
            list.remove(0);
            return "Item number : " + tmp.getContent();
        } else {
            Item tmp = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return "Item number : " + tmp.getContent();
        }

    }


}
