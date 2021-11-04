package com.bolsheviks.APMS.domain.User;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Project.Project;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@NonNull
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String phoneNumber;
    private String status;
    private String workPlace;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private Role role;
    @ManyToMany
    private List<Project> projectList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }
}
