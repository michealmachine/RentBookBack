package org.zhangziqi.dto;

import lombok.Data;

@Data
public class UserDto {
    Integer id;
    String username;
    String email;
    String phone;
    Boolean idAdmin;
}
