package com.bolsheviks.APMS.domain.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDto {
    public String firstName;
    public String lastName;
    public String patronymic;
    public Date birthDate;
    public String phoneNumber;
    public String status;
    public String workPlace;
    public List<UUID> projectUuidList;
    public Role role;
}
