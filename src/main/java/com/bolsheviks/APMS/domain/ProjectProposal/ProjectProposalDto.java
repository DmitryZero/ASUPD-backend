package com.bolsheviks.APMS.domain.ProjectProposal;

import java.util.List;
import java.util.UUID;

public class ProjectProposalDto {
    public String name;
    public String information;
    public List<UUID> projectManagersUuidList;
    public List<UUID> consultantUuidList;
}