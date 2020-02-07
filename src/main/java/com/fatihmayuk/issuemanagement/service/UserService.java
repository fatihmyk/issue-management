package com.fatihmayuk.issuemanagement.service;

import com.fatihmayuk.issuemanagement.dto.UserDto;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);

    UserDto update(Long id, UserDto userDto);

    Boolean delete(Long id);



}
