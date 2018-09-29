package com.chenhz.faker;

import com.chenhz.common.CodeGenerator;
import com.chenhz.common.enums.Application;
import org.junit.Test;

public class FakerCodeGenerator extends CodeGenerator {


    @Override
    public Application getApp() {
        return Application.FAKER;
    }


    @Test
    public void fakerGenerator(){
        this.generateByTables("sys_log");
    }

}
