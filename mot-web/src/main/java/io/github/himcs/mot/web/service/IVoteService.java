package io.github.himcs.mot.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.himcs.mot.dto.vote.req.VoteDTO;
import io.github.himcs.mot.dto.vote.req.VotingDTO;
import io.github.himcs.mot.dto.vote.res.VoteResDTO;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.entity.Vote;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
public interface IVoteService extends IService<Vote> {
    int createVote(VoteDTO voteDTO, User user);

    VoteResDTO getVote(Integer voteId);

    Page<Vote> page(Long pageNum, Long perPage);

    Page<Vote> pageStarred(Long pageNum, Long perPage, User user);

    void voting(VotingDTO votingDTO, User user);
}
