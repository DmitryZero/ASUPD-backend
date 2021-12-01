package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectProposalConverter {

    private final UserRepository userRepository;
    private final StageNamesConverter stageNamesConverter;

    public void fillProjectProposalByDto(ProjectProposal projectProposal, ProjectProposalDto projectProposalDto) {
        if (projectProposalDto.name != null) {
            projectProposal.setName(projectProposalDto.name);
        }
        if (projectProposalDto.information != null) {
            projectProposal.setInformation(projectProposalDto.information);
        }
        if (projectProposalDto.stageNamesList != null) {
            projectProposal.setStageNames((stageNamesConverter.convertListToStageNames(projectProposalDto.stageNamesList)));
        }
        if (projectProposalDto.projectManagersUuidList != null) {
            projectProposal.setProjectManagersList(getUserListFromUuidList(projectProposalDto.projectManagersUuidList));
        }
        if (projectProposalDto.consultantUuidList != null) {
            projectProposal.setConsultantList(getUserListFromUuidList(projectProposalDto.consultantUuidList));
        }
    }

    public ProjectProposalDto convertProjectProposalToDto(ProjectProposal projectProposal) {
        ProjectProposalDto projectProposalDto = new ProjectProposalDto();
        projectProposalDto.id = projectProposal.getId();
        projectProposalDto.name = projectProposal.getName();
        projectProposalDto.information = projectProposal.getInformation();
        projectProposalDto.projectManagersUuidList = projectProposal.getProjectManagersList()
                .stream().map(BaseEntity::getId).toList();
        projectProposalDto.consultantUuidList = projectProposal.getConsultantList()
                .stream().map(BaseEntity::getId).toList();
        projectProposalDto.stageNamesList
                = stageNamesConverter.convertStageNamesToList(projectProposal.getStageNames());
        return projectProposalDto;
    }

    private List<User> getUserListFromUuidList(List<UUID> uuids) {
        return userRepository.findAllById(uuids);
    }

}
