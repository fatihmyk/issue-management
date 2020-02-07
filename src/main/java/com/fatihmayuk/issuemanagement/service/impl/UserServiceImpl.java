package com.fatihmayuk.issuemanagement.service.impl;

import com.fatihmayuk.issuemanagement.dto.UserDto;
import com.fatihmayuk.issuemanagement.entity.User;
import com.fatihmayuk.issuemanagement.repository.UserRepository;
import com.fatihmayuk.issuemanagement.service.UserService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save(UserDto userDto) {
        //Business Logic
        if (userDto.getEmail() == null) {
            throw new IllegalArgumentException("Username email cannot be null!");
        }

        User userdb = modelMapper.map(userDto,User.class);
        userdb = userRepository.save(userdb);
       return modelMapper.map(userdb, UserDto.class);
       // userDto.setId(userdb.getId());
       // return userDto;
    }

   @Override
    public UserDto getById(Long id) {
        User user = userRepository.getOne(id);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data= userRepository.findAll(pageable);
        TPage<UserDto> tPage = new TPage<UserDto>();
        UserDto[] userDtos = modelMapper.map(data.getContent(),UserDto[].class);
        tPage.setStat(data, Arrays.asList(userDtos));
        return tPage;
    }

    public List<UserDto> getAll() {
        List<User> data= userRepository.findAll();
        return Arrays.asList( modelMapper.map(data, UserDto[].class));
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User userDb = userRepository.getOne(id);
        if (userDb == null) {
            throw new IllegalArgumentException("User Does Not Exist. ID: "+id); }

        userDb.setUsername(userDto.getUsername());
        userDb.setEmail(userDto.getEmail());
        userDb.setNameSurname(userDto.getNameSurname());

        userRepository.save(userDb);
        return modelMapper.map(userDb,UserDto.class);

    }

    @Override
    public Boolean delete(Long id){
        userRepository.deleteById(id);
        return true;
    }
}
