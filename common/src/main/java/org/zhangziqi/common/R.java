package org.zhangziqi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R <T>{
    private StatusCode status;
    private String message;
    private T data;
    public static <T> R<T> ok(T data) {
        return new R<>(StatusCode.SUCCESS, "Operation successful", data);
    }

    public static <T> R<T> error(StatusCode status, String message) {
        return new R<>(status, message, null);
    }
}
