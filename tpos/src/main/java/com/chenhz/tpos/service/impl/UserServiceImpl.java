package com.chenhz.tpos.service.impl;

import com.chenhz.tpos.entity.User;
import com.chenhz.tpos.dao.UserDao;
import com.chenhz.tpos.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author chz
 * @since 2018-09-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
