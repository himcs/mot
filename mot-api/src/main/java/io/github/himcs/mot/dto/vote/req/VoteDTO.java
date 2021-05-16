package io.github.himcs.mot.dto.vote.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String context;
    // 0 = 单选 1=多选
    private Integer type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<VoteOptionsDTO> voteOptionsDTOList;
}
