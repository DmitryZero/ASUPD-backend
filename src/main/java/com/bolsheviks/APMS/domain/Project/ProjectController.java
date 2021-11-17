package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.User.Role;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static com.bolsheviks.APMS.security.SecurityFilter.USER_UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectConverter projectConverter;

    @GetMapping("/{uuid}")
    public ProjectDto get(@RequestAttribute(USER_UUID) UUID userId,
                          @PathVariable("uuid") UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (project.containsUser(user)) {
            return projectConverter.convertProjectToDto(project);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    public void put(@RequestAttribute(USER_UUID) UUID userId,
                    @PathVariable("uuid") UUID projectId,
                    @RequestBody ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!project.containsUserWithModifyRights(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        projectConverter.fillProjectByDto(project, projectDto);
        projectRepository.save(project);
    }

    @PutMapping("/{uuid}/add_consultants")
    public void addConsultants(@RequestAttribute(USER_UUID) UUID userId,
                               @PathVariable("uuid") UUID projectId,
                               @RequestBody ProjectDto projectDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!project.getUserProjectManager().equals(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<User> usersConsultantsList = project.getUsersConsultantsList();

        for (User newConsultant : userRepository.findAllById(projectDto.usersConsultantsUuidList)) {
            if (newConsultant.getRole() != Role.CURATOR || project.containsUser(newConsultant)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            usersConsultantsList.add(newConsultant);
        }
        projectRepository.save(project);
    }

    @PutMapping("/{uuid}/add_members")
    public void addMembers(@RequestAttribute(USER_UUID) UUID userId,
                               @PathVariable("uuid") UUID projectId,
                               @RequestBody ProjectDto projectDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!project.getUserCaptain().equals(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<User> usersMembersList = project.getUsersMembersList();

        for (User newMember : userRepository.findAllById(projectDto.usersMembersUuidList)) {
            if (project.containsUser(newMember)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
            usersMembersList.add(newMember);
        }
        projectRepository.save(project);
    }
}
