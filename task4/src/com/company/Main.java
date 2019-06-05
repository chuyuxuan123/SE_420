package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here

        try {
            //test1 : test get items FIFO(queue)
            MyContainer test1 = new MyContainer();

            //producer
            test1.addItem();
            test1.addItem();
            test1.addItem();

            //consumer
            System.out.println(test1.getItem());
            System.out.println(test1.getItem());
            System.out.println(test1.getItem());

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //test2 : test get items LIFO(stack)
            MyContainer test2 = new MyContainer();

            //producer
            for (int i = 0; i < 5; i++) {
                test2.addItem();
            }

            //consumer
            for (int i = 0; i < 5; i++) {
                test2.getItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            //test3 : test timeout
            MyContainer test3 = new MyContainer();

            //producer
            for (int i = 0; i < 5; i++) {
                test3.addItem();
            }

            //consumer
            System.out.println(test3.getItem());
            System.out.println(test3.getItem());

            //sleep 3 second to make the items stay in container longer than TIMEOUT
            Thread.sleep(3000);
            System.out.println(test3.getItem());
            System.out.println(test3.getItem());
            System.out.println(test3.getItem());

            System.out.println(test3.getItem());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
