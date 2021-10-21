package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "project_proposals")
@Getter
@Setter
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
