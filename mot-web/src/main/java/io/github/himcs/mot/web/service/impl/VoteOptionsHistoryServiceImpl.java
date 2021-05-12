package io.github.himcs.mot.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.himcs.mot.generator.entity.VoteOptionsHistory;
import io.github.himcs.mot.generator.mapper.VoteOptionsHistoryMapper;
import io.github.himcs.mot.web.service.IVoteOptionsHistoryService;
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
public class VoteOptionsHistoryServiceImpl extends ServiceImpl<VoteOptionsHistoryMapper, VoteOptionsHistory> implements IVoteOptionsHistoryService {

}
