
package com.chenhz.faker.main;

import com.chenhz.faker.FakerApplication;
import com.chenhz.faker.entity.User;
import com.chenhz.faker.service.IUserService;
import com.chenhz.faker.utils.Fakers;
import com.github.javafaker.Faker;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Main {

    private IUserService userService;

    static Logger log = LoggerFactory.getLogger(Fakers.class);

    static Faker faker ;


    @BeforeClass
    public static void init(){
        log.info("加载实例......");
        faker = new Faker(new Locale("zh_CN"));

    }

    @Test
    public void user(){
        List<User> users = new ArrayList<>();
        for (int i=0;i<10;i++){
            User user = new User();
            user.setUsername(faker.name().name());
            user.setMobile(faker.phoneNumber().cellPhone());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(""+faker.internet().password());
            user.setCreateTime(faker.date().birthday());
            user.setStatus(faker.random().nextInt(2));
            users.add(user);
        }
        userService.insertBatch(users);

    }


    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}

