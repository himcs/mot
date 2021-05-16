package io.github.himcs.mot.dto.vote.res;

import io.github.himcs.mot.generator.entity.Vote;
import io.github.himcs.mot.generator.entity.VoteOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VoteResDTO extends Vote {
    private List<VoteOptions> voteOptionsDTOList;
}
