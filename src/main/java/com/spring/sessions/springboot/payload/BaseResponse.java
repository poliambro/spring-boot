package com.spring.sessions.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("payload")
    private Object payload;

    public static BaseResponse generateResponse(HttpStatus status, Object payload){
        return BaseResponse.builder()
                .code(status.value())
                .message(status.toString())
                .payload(payload)
                .build();
    }
}
