package com.chenhz.tpos.service.impl;

import com.chenhz.tpos.entity.Position;
import com.chenhz.tpos.dao.PositionDao;
import com.chenhz.tpos.service.IPositionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author chz
 * @since 2018-09-26
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position> implements IPositionService {

}
