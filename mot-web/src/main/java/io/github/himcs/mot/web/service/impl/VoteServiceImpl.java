package io.github.himcs.mot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.himcs.mot.dto.vote.req.VoteDTO;
import io.github.himcs.mot.dto.vote.req.VoteOptionsDTO;
import io.github.himcs.mot.dto.vote.req.VotingDTO;
import io.github.himcs.mot.dto.vote.res.VoteResDTO;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.entity.Vote;
import io.github.himcs.mot.generator.entity.VoteOptions;
import io.github.himcs.mot.generator.entity.VoteOptionsHistory;
import io.github.himcs.mot.generator.mapper.VoteMapper;
import io.github.himcs.mot.web.dao.mapper.VoteOptionsExMapper;
import io.github.himcs.mot.web.service.IVoteOptionsHistoryService;
import io.github.himcs.mot.web.service.IVoteOptionsService;
import io.github.himcs.mot.web.service.IVoteService;
import io.github.himcs.mot.web.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements IVoteService {

    private VoteMapper voteMapper;

    private IVoteOptionsService voteOptionsService;

    private IVoteOptionsHistoryService voteOptionsHistoryService;

    @Resource
    private VoteOptionsExMapper voteOptionsExMapper;

    @Autowired
    public VoteServiceImpl(VoteMapper voteMapper, IVoteOptionsService voteOptionsService, IVoteOptionsHistoryService voteOptionsHistoryService) {
        this.voteMapper = voteMapper;
        this.voteOptionsService = voteOptionsService;
        this.voteOptionsHistoryService = voteOptionsHistoryService;
    }

    @Override
    @Transactional
    public int createVote(VoteDTO voteDTO, User user) {
        Integer voteId = saveVote(voteDTO, user.getId());
        List<VoteOptions> voteOptionsList = genVoteOptionsList(voteDTO.getVoteOptionsDTOList(), voteId);
        voteOptionsService.saveBatch(voteOptionsList);
        return voteId;
    }

    @Override
    public VoteResDTO getVote(Integer voteId) {
        Vote vote = this.getById(voteId);
        BaseMapper<VoteOptions> voteOptionsMapper = voteOptionsService.getBaseMapper();
        List<VoteOptions> voteOptionsList = voteOptionsMapper.selectList(new QueryWrapper<VoteOptions>().lambda()
                .eq(VoteOptions::getVoteId, voteId));
        VoteResDTO voteResDTO = new VoteResDTO();
        BeanUtils.copyProperties(vote, voteResDTO);
        voteResDTO.setVoteOptionsDTOList(voteOptionsList);
        return voteResDTO;
    }

    @Override
    public Page<Vote> page(Long pageNum, Long perPage) {
        Page<Vote> page = PageUtil.instance(pageNum, perPage);
        page = voteMapper.selectPage(page, null);
        return page;
    }

    @Override
    public Page<Vote> pageStarred(Long pageNum, Long perPage, User user) {
        Page<Vote> page = PageUtil.instance(pageNum, perPage);
        page = voteMapper.selectPage(page, new QueryWrapper<Vote>().lambda().eq(Vote::getUserId, user.getId()));
        return page;
    }

    @Override
    @Transactional
    public void voting(VotingDTO votingDTO, User user) {
        Integer voteId = votingDTO.getVoteId();
        Integer userId = user.getId();
        List<Integer> voteOptions = votingDTO.getVoteOptions();
        List<VoteOptionsHistory> voteOptionsHistoryList = genVoteOptionsHistories(voteId, userId, voteOptions);
        LambdaUpdateWrapper<VoteOptions> voteOptionsLambdaUpdateWrapper = new UpdateWrapper<VoteOptions>().lambda().setSql("vote_sum = vote_sum + 1");
        voteOptionsHistoryService.saveBatch(voteOptionsHistoryList);
        voteOptionsExMapper.updateSum(voteOptions);
    }

    private List<VoteOptionsHistory> genVoteOptionsHistories(Integer voteId, Integer userId, List<Integer> voteOptions) {
        List<VoteOptionsHistory> voteOptionsHistoryList = new ArrayList<>(voteOptions.size());
        LocalDateTime now = LocalDateTime.now();
        for (Integer voteOption : voteOptions) {
            VoteOptionsHistory voteOptionsHistory = new VoteOptionsHistory();
            voteOptionsHistory.setVoteId(voteId);
            voteOptionsHistory.setVoteOptionsId(voteOption);
            voteOptionsHistory.setUserId(userId);
            voteOptionsHistory.setCreateTime(now);
            voteOptionsHistoryList.add(voteOptionsHistory);
        }
        return voteOptionsHistoryList;
    }

    private Integer saveVote(VoteDTO voteDTO, Integer userID) {
        Vote vote = new Vote();
        BeanUtils.copyProperties(voteDTO, vote);
        vote.setCreateTime(LocalDateTime.now());
        vote.setUserId(userID);
        voteMapper.insert(vote);
        return vote.getId();
    }

    private List<VoteOptions> genVoteOptionsList(List<VoteOptionsDTO> voteOptionsDTOList, Integer voteId) {
        List<VoteOptions> voteOptionsList = new ArrayList<>(voteOptionsDTOList.size());
        for (VoteOptionsDTO voteOptionsDTO : voteOptionsDTOList) {
            VoteOptions voteOptions = new VoteOptions();
            BeanUtils.copyProperties(voteOptionsDTO, voteOptions);
            voteOptions.setVoteId(voteId);
            voteOptions.setVoteSum(0);
            voteOptionsList.add(voteOptions);
        }
        return voteOptionsList;
    }
}
