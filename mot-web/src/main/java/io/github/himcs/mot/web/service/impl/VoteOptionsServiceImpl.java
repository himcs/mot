package io.github.himcs.mot.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.himcs.mot.generator.entity.VoteOptions;
import io.github.himcs.mot.generator.mapper.VoteOptionsMapper;
import io.github.himcs.mot.web.service.IVoteOptionsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@Service
public class VoteOptionsServiceImpl extends ServiceImpl<VoteOptionsMapper, VoteOptions> implements IVoteOptionsService {

}
