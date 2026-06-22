package com.mkdev.user_service.service;

import com.mkdev.user_service.dto.UserDto;
import com.mkdev.user_service.dto.converter.UserDtoToUserConverter;
import com.mkdev.user_service.dto.converter.UserToUserDtoConverter;
import com.mkdev.user_service.entity.User;
import com.mkdev.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoToUserConverter dtoToUserConverter;
    private final UserToUserDtoConverter userToUserDtoConverter;

    public UserDto createUser(UserDto userDto) {
        log.info("Creating user: {}",userDto);
        User savedUser = userRepository.save(dtoToUserConverter.convert(userDto));
        log.info("user Created Successfully: {}",savedUser);
        return userToUserDtoConverter.convert(savedUser);
    }

    public List<UserDto> getAllUsers() {
        log.info("Getting All Users .... \n");
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream()
                .map(userToUserDtoConverter::convert)
                .collect((Collectors.toList()));
        log.info("We found {}: {}",dtos.size(),dtos);
        return dtos;
    }

    public UserDto findUserByEmail(String email) {
        log.info("Searching For User {} .... \n",email);
        User user = userRepository.findUserByEmail(email);
        UserDto dto = userToUserDtoConverter.convert(user);
        log.info("We found user successfully: {}",dto.getEmail());
        return dto;
    }
}
