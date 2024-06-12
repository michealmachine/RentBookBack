package org.zhangziqi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BooksDto {
    Integer id;
    String bookName;
    String disc;
    Integer account;
}
