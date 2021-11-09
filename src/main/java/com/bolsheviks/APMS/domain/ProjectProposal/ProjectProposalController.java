package com.bolsheviks.APMS.domain.ProjectProposal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project_proposal")
public class ProjectProposalController {

    private final ProjectProposalRepository projectProposalRepository;
    private final ProjectProposalConverter projectProposalConverter;

    public ProjectProposalController(ProjectProposalRepository projectProposalRepository,
                                     ProjectProposalConverter projectProposalConverter) {
        this.projectProposalRepository = projectProposalRepository;
        this.projectProposalConverter = projectProposalConverter;
    }

    @GetMapping("/get_all")
    public List<ProjectProposalDto> getAll() {
        return projectProposalRepository.findAll().stream()
                .map(projectProposalConverter::convertProjectProposalToDto).toList();
    }
}
