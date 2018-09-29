package com.chenhz.common.enums;

public enum Application {
    COMMON(new String[]{"com.chenhz.common","common"}),
    FAKER(new String[]{"com.chenhz.faker","faker"}),
    HTTP(new String[]{"com.chenhz.http","http"}),
    TEST(new String[]{"com.chenhz.test","test"}),
    TPOS(new String[]{"com.chenhz.tpos","tpos"});


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
