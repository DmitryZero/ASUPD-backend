package com.bolsheviks.APMS.domain.User;

import com.bolsheviks.APMS.domain.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.patronymic = user.getPatronymic();
        userDto.birthDate= user.getBirthDate();
        userDto.phoneNumber = user.getPhoneNumber();
        userDto.status = user.getStatus();
        userDto.workPlace = user.getWorkPlace();
        userDto.projectUuidList = user.getProjectList().stream().map(BaseEntity::getId).toList();
        return userDto;
    }

    public void fillUserByUserDto(User user, UserDto userDto) {
        if (userDto.firstName != null) {
            user.setFirstName(userDto.firstName);
        }
        if (userDto.lastName != null) {
            user.setLastName(userDto.lastName);
        }
        if (userDto.patronymic != null) {
            user.setPatronymic(userDto.patronymic);
        }
        if (userDto.birthDate != null) {
            user.setBirthDate(userDto.birthDate);
        }
        if (userDto.phoneNumber != null) {
            user.setPhoneNumber(userDto.phoneNumber);
        }
        if (userDto.status != null) {
            user.setStatus(userDto.status);
        }
        if (userDto.workPlace != null) {
            user.setWorkPlace(userDto.workPlace);
        }
    }
}
