package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static com.bolsheviks.APMS.security.SecurityFilter.USER_UUID;

@RestController
@RequestMapping("/project_proposal")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectProposalController {

    private final ProjectProposalRepository projectProposalRepository;
    private final ProjectProposalConverter projectProposalConverter;
    private final UserRepository userRepository;
    private final ProjectProposalService projectProposalService;

    @GetMapping("/get_all")
    public List<UUID> getAll() {
        return projectProposalRepository.findAll().stream()
                .map(BaseEntity::getId).toList();
    }

    @GetMapping("/{uuid}")
    public ProjectProposalDto get(@PathVariable("uuid") UUID id) {
        ProjectProposal projectProposal = projectProposalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return projectProposalConverter.convertProjectProposalToDto(projectProposal);
    }

    @PostMapping("/{uuid}")
    public UUID createProject(@RequestAttribute(USER_UUID) UUID userId,
                              @PathVariable("uuid") UUID id,
                              @RequestHeader("managerId") UUID managerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProjectProposal projectProposal = projectProposalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!projectProposal.getProjectManagersList().contains(manager)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return projectProposalService.createProject(user, manager, projectProposal).getId();
    }
}
