package com.mkdev.user_service.service;

import com.mkdev.user_service.dto.UserDto;
import com.mkdev.user_service.dto.converter.UserDtoToUserConverter;
import com.mkdev.user_service.dto.converter.UserToUserDtoConverter;
import com.mkdev.user_service.entity.User;
import com.mkdev.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
