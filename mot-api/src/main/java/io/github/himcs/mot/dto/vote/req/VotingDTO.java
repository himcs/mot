package io.github.himcs.mot.dto.vote.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingDTO {
    private Integer voteId;
    private List<Integer> voteOptions;
}
