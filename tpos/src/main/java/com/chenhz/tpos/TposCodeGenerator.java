package com.chenhz.tpos;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.chenhz.common.CodeGenerator;
import com.chenhz.common.enums.Application;

public class TposCodeGenerator extends CodeGenerator {


    private TposCodeGenerator(Application app) {
        super(app);
    }

    @Override
    public Application getApp() {
        return Application.TPOS;
    }


    public static void main(String[] args) {
        TposCodeGenerator tposCodeGenerator = new TposCodeGenerator(Application.TPOS);
        tposCodeGenerator.generateByTables("sys_log");
    }

    @Override
    public GlobalConfig getGlobalConfig() {
        return super.getGlobalConfig()
                .setMapperName("%sDao");
    }

}
