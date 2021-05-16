package io.github.himcs.mot.dto.user.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
