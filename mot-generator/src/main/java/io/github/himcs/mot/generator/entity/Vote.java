package io.github.himcs.mot.generator.entity;

import java.time.LocalDateTime;
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
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String title;

    private String context;

    private LocalDateTime createTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
