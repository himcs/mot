package io.github.himcs.mot.generator.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mcs
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VoteOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer voteId;

    private String context;

    private Integer voteSum;


}
