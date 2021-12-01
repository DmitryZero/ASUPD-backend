package com.bolsheviks.APMS.domain.Project;

import java.util.List;
import java.util.UUID;

public class ProjectDto {

    public String name;
    public UUID userCaptain;
    public UUID projectManager;
    public List<UUID> usersMembersUuidList;
    public List<UUID> usersConsultantsUuidList;
    public ProjectStatus projectStatus;
    public String information;
    public List<UUID> stageUuidList;
}
