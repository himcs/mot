package io.github.himcs.mot.web.service.impl;

import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.mapper.UserMapper;
import io.github.himcs.mot.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
