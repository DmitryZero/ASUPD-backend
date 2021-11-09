package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.Stage.StageDto;

import java.util.List;
import java.util.UUID;

public class ProjectDto {

    public UUID userCaptain;
    public UUID projectManager;
    public List<UUID> usersMembersUuidList;
    public List<UUID> usersConsultantsUuidList;
    public ProjectStatus projectStatus;
    public String information;
    public List<StageDto> stageDtoList;
}
