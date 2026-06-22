package com.mkdev.user_service.dto.converter;

import com.mkdev.user_service.dto.UserDto;
import com.mkdev.user_service.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto source) {
        return User.builder()
                .email(source.getEmail())
                .name(source.getName())
                .surname(source.getSurname())
                .address(source.getAddress())
                .alerting(source.isAlerting())
                .energyAlertingThreshold(source.getEnergyAlertingThreshold())
                .build();
    }
}
