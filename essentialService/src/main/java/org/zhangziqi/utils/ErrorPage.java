package org.zhangziqi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
@Getter
public class ErrorPage extends PageImpl<String> {

    public ErrorPage(String errorMessage) {
        super(Collections.emptyList());
    }


}