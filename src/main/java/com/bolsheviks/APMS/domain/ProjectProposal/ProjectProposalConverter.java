package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.Stage.StageConverter;
import com.bolsheviks.APMS.domain.Stage.StageDto;
import com.bolsheviks.APMS.domain.Stage.StageRepository;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectProposalConverter {

    private final UserRepository userRepository;
    private final StageConverter stageConverter;
    private final StageRepository stageRepository;

    public void fillProjectProposalByDto(ProjectProposal projectProposal, ProjectProposalDto projectProposalDto) {
        if (projectProposalDto.name != null) {
            projectProposal.setName(projectProposalDto.name);
        }
        if (projectProposalDto.information != null) {
            projectProposal.setInformation(projectProposalDto.information);
        }
        if (projectProposalDto.stageDtoList != null) {
            projectProposal.setStageList(getStageListFromDtoList(projectProposalDto.stageDtoList));
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
        projectProposalDto.stageDtoList
                = projectProposal.getStageList().stream().map(stageConverter::convertStageToDto).toList();
        return projectProposalDto;
    }

    private List<User> getUserListFromUuidList(List<UUID> uuids) {
        return userRepository.findAllById(uuids);
    }

    private List<Stage> getStageListFromDtoList(List<StageDto> stageDtoList) {
        ArrayList<Stage> stages = new ArrayList<>();

        for (StageDto stageDto : stageDtoList) {
            Stage stage = new Stage();
            stageConverter.fillStageFromDto(stage, stageDto);
            stages.add(stage);
        }
        stageRepository.saveAll(stages);

        return stages;
    }
}
