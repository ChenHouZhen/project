package com.chenhz.http.entity.unmarshaller;

import com.chenhz.http.entity.UserEntity;
import com.chenhz.http.utils.UnmarshallerContext;

/**
 * @author CHZ
 * @create 2018/9/10
 */
public class UserUnmarshaller {
    public UserUnmarshaller(){ }

    public static UserEntity unmarshall(UserEntity userInfo, UnmarshallerContext context) {
        //userInfo.setName(context.stringValue("UserInfo.name"));
        //userInfo.setAge(context.integerValue("UserInfo.age"));
        return userInfo;
    }
}
