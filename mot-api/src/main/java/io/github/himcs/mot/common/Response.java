package io.github.himcs.mot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int code;
    private String message;
    private Object data;

    public static Response OK(Object data) {
        return Response.builder().code(200).message("OK").data(data).build();
    }

    public static Response OK() {
        return Response.builder().code(200).message("OK").build();
    }

    public static Response ERROR(String message) {
        return Response.builder().code(500).message(message).build();
    }
}
