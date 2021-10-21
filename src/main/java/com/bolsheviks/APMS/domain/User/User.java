package com.bolsheviks.APMS.domain.User;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Project.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String phoneNumber;
    private String status;
    private String workPlace;
    private String email;
    private String password;
    private UserRole role;
    @ManyToMany
    private List<Project> projectList;
}
