package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.User.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ProjectProposal extends BaseEntity {

    private String name;
    private String information;
    @OneToMany
    private List<Stage> stageList;
    @OneToMany
    private List<User> projectManagersList;
    @OneToMany
    private List<User> consultantList;
}
