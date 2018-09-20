package com.chenhz.tpos.test.cloneTest;

import java.io.IOException;

public class CloneTest {

    public static void main(String[] args) {
        Customer customerBef = new Customer();
        Address address = new Address();
        address.setPrivince("广东省");
        address.setCity("广州市");
        customerBef.setName("小明");
        customerBef.setAddress(address);

        Customer customerAft = customerBef.clone();
        System.out.println(customerBef == customerAft);
        System.out.println("result :"+(customerBef.getAddress() == customerAft.getAddress()) +"\t\nBef : "+customerBef.getAddress() +"\t\nAft :"+customerAft.getAddress());

        try {
            Customer customerAftTwo = customerBef.cloneDeep();
            System.out.println(customerBef == customerAftTwo);
            System.out.println("result :"+(customerBef.getAddress() == customerAftTwo.getAddress()) +"\t\nBef : "+customerBef.getAddress() +"\t\nAft :"+customerAftTwo.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
