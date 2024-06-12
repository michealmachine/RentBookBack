package org.zhangziqi.error;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zhangziqi.common.R;
import org.zhangziqi.common.StatusCode;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<R<String>> handleDatabaseExceptions(DataAccessException ex) {
        return new ResponseEntity<>(R.error(StatusCode.INTERNAL_SERVER_ERROR, "你不能删除，有人还在借"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
