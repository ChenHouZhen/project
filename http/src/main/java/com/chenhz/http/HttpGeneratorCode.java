package com.chenhz.http;

import com.chenhz.common.GeneratorCode;
import com.chenhz.common.enums.Application;
import org.junit.Test;

public class HttpGeneratorCode extends GeneratorCode {
    @Override
    public Application getApp() {
        return Application.HTTP;
    }

    @Test
    public void httpGenerator(){
        this.generateByTables("tb_user");
    }

}
