package com.chenhz.tpos;

import com.chenhz.common.GeneratorCode;
import com.chenhz.common.enums.Application;
import org.junit.Test;

public class TposGenerator extends GeneratorCode {
    @Override
    public Application getApp() {
        return Application.TPOS;
    }

    @Test
    public void tposGenerator(){
        this.generateByTables("userinfo");
    }
}
