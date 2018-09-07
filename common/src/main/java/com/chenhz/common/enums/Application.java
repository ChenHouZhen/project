package com.chenhz.common.enums;

public enum Application {
    COMMON(new String[]{"com.chenhz.common"}),
    FAKER(new String[]{"com.chenhz.faker"}),
    HTTP(new String[]{"com.chenhz.http"}),
    TEST(new String[]{"com.chenhz.test"}),
    TPOS(new String[]{"com.chenhz.tpos"});


    private String[] path;

    private Application(String[] path){
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

}
