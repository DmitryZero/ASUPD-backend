package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.User.User;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String name;
    private UUID userCaptainId;
    @ManyToMany
    private List<User> usersMembersList;
    private UUID userProjectManagerId;
    @ManyToMany
    private List<User> usersConsultantsList;
    private ProjectStatus projectStatus;
    private String information;
    @OneToMany
    private List<Stage> stageList;
}
