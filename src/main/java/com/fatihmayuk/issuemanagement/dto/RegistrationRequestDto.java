package com.fatihmayuk.issuemanagement.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {

    private String nameSurname;
    private String username;
    private String password;
    private String email;
}
