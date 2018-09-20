package com.chenhz.tpos.test.cloneTest;


import java.io.*;

public class Customer implements Serializable,Cloneable {

    private String name;

    private Address address;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Customer clone(){
        try {
            Customer customer =  (Customer) super.clone();
            Address address = (Address) this.address.clone();
            customer.setAddress(address);
            return customer;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected Customer cloneDeep() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Customer customer = (Customer) ois.readObject();
        return customer;
    }

}
