package com.chenhz.faker.service.impl;

import com.chenhz.faker.entity.Area;
import com.chenhz.faker.dao.AreaDao;
import com.chenhz.faker.service.IAreaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市区域表 服务实现类
 * </p>
 *
 * @author chz
 * @since 2018-09-20
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements IAreaService {

}
