package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.BaseEntity;
import com.bolsheviks.APMS.domain.User.User;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.UUID;
import java.util.List;

@Entity
public class Project extends BaseEntity {

    private String name;
    private UUID userCaptainId;
    @ManyToMany
    private List<User> usersMembersList;

}
