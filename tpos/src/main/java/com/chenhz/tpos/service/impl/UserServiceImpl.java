package com.chenhz.tpos.service.impl;

import com.chenhz.tpos.entity.UserEntity;
import com.chenhz.tpos.dao.UserDao;
import com.chenhz.tpos.service.IUserService;
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
