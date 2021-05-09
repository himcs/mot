package io.github.himcs.mot.web.dto.user.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class Login {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
