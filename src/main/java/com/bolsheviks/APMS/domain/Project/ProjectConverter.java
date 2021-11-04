package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.StageConverter;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectConverter {

    private final StageConverter stageConverter;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectDto convertProjectToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.usersMembersUuidList = project.getUsersMembersList().stream().map(BaseEntity::getId).toList();
        projectDto.usersConsultantsUuidList = project.getUsersConsultantsList().stream().map(BaseEntity::getId).toList();
        projectDto.projectStatus = project.getProjectStatus();
        projectDto.information = project.getInformation();
        projectDto.stageDtoList = project.getStageList().stream().map(stageConverter::convertStageToDto).toList();
        return projectDto;
    }

    public void fillProjectByDtoAndSave(Project project, ProjectDto projectDto) {
        if (projectDto.usersMembersUuidList != null) {
            project.setUsersMembersList(getUserListFromUuidList(projectDto.usersMembersUuidList));
        }
        if (projectDto.usersConsultantsUuidList != null) {
            project.setUsersConsultantsList(getUserListFromUuidList(projectDto.usersConsultantsUuidList));
        }
        if (projectDto.projectStatus != null) {
            project.setProjectStatus(projectDto.projectStatus);
        }
        if (projectDto.information != null) {
            project.setInformation(projectDto.information);
        }
        if (projectDto.stageDtoList != null) {
            project.setStageList(stageConverter.createStageListFromDtoList(projectDto.stageDtoList));
        }
        projectRepository.save(project);
    }

    private List<User> getUserListFromUuidList(List<UUID> uuids) {
        return uuids.stream().map(uuid ->
                userRepository.findById(uuid).orElse(null)).filter(Objects::nonNull).toList();
    }
}
