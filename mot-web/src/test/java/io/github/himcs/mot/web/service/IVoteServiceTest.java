package io.github.himcs.mot.web.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.himcs.mot.dto.vote.req.VoteDTO;
import io.github.himcs.mot.dto.vote.req.VoteOptionsDTO;
import io.github.himcs.mot.dto.vote.req.VotingDTO;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.entity.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class VoteServiceTests {

    @Resource
    IVoteService voteService;

    @Test
    void testSave() {
        Vote vote = new Vote();
        vote.setTitle("测试title");
        vote.setContext("test");
        vote.setCreateTime(LocalDateTime.now());
        vote.setUserId(1);
        vote.setStartTime(LocalDateTime.now());
        vote.setEndTime(LocalDateTime.now());
        voteService.save(vote);
    }

    @Test
    void testPage() {
        BaseMapper<Vote> baseMapper = voteService.getBaseMapper();
        Page<Vote> page = new Page<>(1, 10);
        baseMapper.selectPage(page, null);
    }

    @Test
    void createVote() {
        VoteOptionsDTO voteOptionsDTO = new VoteOptionsDTO();
        voteOptionsDTO.setContext("test A");
        VoteOptionsDTO voteOptionsDTOB = new VoteOptionsDTO();
        voteOptionsDTOB.setContext("test B");
        List<VoteOptionsDTO> voteOptionsDTOS = Arrays.asList(voteOptionsDTO, voteOptionsDTOB);
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setTitle("voteTitle");
        voteDTO.setContext("test_CONtex");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusDays(2);
        voteDTO.setStartTime(now);
        voteDTO.setEndTime(end);
        voteDTO.setVoteOptionsDTOList(voteOptionsDTOS);

        User user = new User();
        user.setId(1);
        int vote = voteService.createVote(voteDTO, user);
        System.out.println("vote id is : " + vote);


    }

    @Test
    void testGetVote() {
        Page<Vote> page = voteService.page(1L, 10L);
        System.out.println(page);
    }

    @Test
    void testGetVoteStarred() {
        User user = new User();
        user.setId(1);
        Page<Vote> page = voteService.pageStarred(1L, 10L, user);
        System.out.println(page);
    }

    @Test
    void testVoting() {
        User user = new User();
        user.setId(1);
        Page<Vote> page = voteService.pageStarred(1L, 10L, user);
        VotingDTO votingDTO = new VotingDTO();
        votingDTO.setVoteId(1);
        votingDTO.setVoteOptions(Arrays.asList(1, 2, 3));
        voteService.voting(votingDTO, user);
    }
}