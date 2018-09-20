package com.chenhz.tpos.test.cloneTest;

import java.io.Serializable;

public class Address implements Serializable,Cloneable{

    private String privince;

    private String city;

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
