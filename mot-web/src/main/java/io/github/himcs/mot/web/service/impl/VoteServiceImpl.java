package io.github.himcs.mot.web.service.impl;

import io.github.himcs.mot.generator.entity.Vote;
import io.github.himcs.mot.generator.mapper.VoteMapper;
import io.github.himcs.mot.web.service.IVoteService;
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
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements IVoteService {

}
