package io.github.himcs.mot.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.himcs.mot.api.VoteAPi;
import io.github.himcs.mot.auth.annotation.CurrentUser;
import io.github.himcs.mot.common.Response;
import io.github.himcs.mot.dto.vote.req.VoteDTO;
import io.github.himcs.mot.dto.vote.req.VotingDTO;
import io.github.himcs.mot.dto.vote.res.VoteResDTO;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.entity.Vote;
import io.github.himcs.mot.web.service.IVoteService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@RestController
public class VoteController implements VoteAPi {

    private IVoteService voteService;

    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @Override
    public Response create(VoteDTO voteDTO, @CurrentUser User user) {
        int vote = voteService.createVote(voteDTO, user);
        return Response.OK(vote);
    }

    @Override
    public Response detail(Integer voteId) {
        VoteResDTO vote = voteService.getVote(voteId);
        return Response.OK(vote);
    }

    @Override
    public Response page(Long page, Long perPage) {
        Page<Vote> pageRes = voteService.page(page, perPage);
        return Response.OK(pageRes);
    }

    @Override
    public Response starred(Long page, Long perPage, @CurrentUser User user) {
        Page<Vote> pageRes = voteService.pageStarred(page, perPage, user);
        return Response.OK(pageRes);
    }

    @Override
    public Response vote(VotingDTO votingDTO, User user) {
        return null;
    }
}
