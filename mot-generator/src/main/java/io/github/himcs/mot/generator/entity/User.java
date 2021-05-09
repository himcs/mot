package io.github.himcs.mot.generator.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String loginName;

    private String password;

    private String mobile;

    private String avatar;

    private Integer userState;


}
