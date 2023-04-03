package com.goit.dto;

import com.goit.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Status status;
}
