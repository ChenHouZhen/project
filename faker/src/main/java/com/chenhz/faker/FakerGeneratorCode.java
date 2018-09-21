package com.chenhz.faker;

import com.chenhz.common.GeneratorCode;
import com.chenhz.common.enums.Application;
import org.junit.Test;

public class FakerGeneratorCode extends GeneratorCode {

    @Override
    public Application getApp() {
        return Application.FAKER;
    }

    @Test
    public void fakerGenerator(){
        this.generateByTables("tb_area");
    }

}
