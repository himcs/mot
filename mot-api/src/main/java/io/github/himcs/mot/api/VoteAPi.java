package io.github.himcs.mot.api;

import io.github.himcs.mot.common.DefaultPage;
import io.github.himcs.mot.common.Response;
import io.github.himcs.mot.dto.vote.req.VoteDTO;
import io.github.himcs.mot.generator.entity.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/vote")
public interface VoteAPi {
    @PostMapping("/")
    Response create(@RequestBody VoteDTO voteDTO, @Parameter(hidden = true) User user);

    @GetMapping("/public")
    Response page(@RequestParam(value = DefaultPage.DEFAULT_PAGE_PARAM, defaultValue = DefaultPage.DEFAULT_PAGE) Long page, @RequestParam(value = DefaultPage.DEFAULT_PER_PAGE_PARAM, defaultValue = DefaultPage.DEFAULT_PER_PAGE) Long perPage);

    @GetMapping("/starred")
    Response starred(@RequestParam(value = DefaultPage.DEFAULT_PAGE_PARAM, defaultValue = DefaultPage.DEFAULT_PAGE) Long page, @RequestParam(value = DefaultPage.DEFAULT_PER_PAGE_PARAM, defaultValue = DefaultPage.DEFAULT_PER_PAGE) Long perPage, @Parameter(hidden = true) User user);

    @GetMapping("/{voteId}")
    Response detail(@PathVariable Integer voteId);

}
