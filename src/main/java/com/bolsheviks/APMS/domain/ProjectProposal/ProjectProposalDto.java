package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.Stage.StageDto;

import java.util.List;
import java.util.UUID;

public class ProjectProposalDto {
    public UUID id;
    public String name;
    public String information;
    public List<UUID> projectManagersUuidList;
    public List<UUID> consultantUuidList;
    public List<StageDto> stageDtoList;
}
