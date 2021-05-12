package io.github.himcs.mot.web.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.himcs.mot.generator.entity.User;
import io.github.himcs.mot.generator.entity.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
}