package com.chenhz.faker.service.impl;

import com.chenhz.faker.entity.UserEntity;
import com.chenhz.faker.dao.UserDao;
import com.chenhz.faker.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author chz
 * @since 2018-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IUserService {

}
