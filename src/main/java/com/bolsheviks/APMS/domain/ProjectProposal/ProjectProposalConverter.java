package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectProposalConverter {

    public ProjectProposalDto convertProjectProposalToDto(ProjectProposal projectProposal) {
        ProjectProposalDto projectProposalDto = new ProjectProposalDto();
        projectProposalDto.name = projectProposal.getName();
        projectProposalDto.information = projectProposal.getInformation();
        projectProposalDto.projectManagersUuidList = projectProposal.getProjectManagersList()
                .stream().map(BaseEntity::getId).toList();
        projectProposalDto.consultantUuidList = projectProposal.getConsultantList()
                .stream().map(BaseEntity::getId).toList();
        return projectProposalDto;
    }
}
