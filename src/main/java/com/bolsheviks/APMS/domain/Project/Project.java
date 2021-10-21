package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project extends BaseEntity {

    private String name;
    @ManyToOne
    private User userCaptain;
    @ManyToMany
    private List<User> usersMembersList;
    @ManyToOne
    private User userProjectManager;
    @ManyToMany
    private List<User> usersConsultantsList;
    private ProjectStatus projectStatus;
    private String information;
    @OneToMany
    private List<Stage> stageList;
}
