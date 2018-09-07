package com.chenhz.test.service.impl;

import com.chenhz.test.entity.UserEntity;
import com.chenhz.test.dao.UserDao;
import com.chenhz.test.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chz
 * @since 2018-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IUserService {

}
