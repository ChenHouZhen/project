package com.chenhz.test;

import com.chenhz.common.GeneratorCode;
import com.chenhz.common.enums.Application;
import org.junit.Test;

public class TestGeneratorCode extends GeneratorCode {
    @Override
    public Application getApp() {
        return Application.TEST;
    }

    @Test
    public void testGenerator(){
        this.generateByTables("tb_user");
    }
}
