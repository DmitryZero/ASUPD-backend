package com.bolsheviks.APMS.domain.ProjectProposal;

import com.bolsheviks.APMS.domain.Project.Project;
import com.bolsheviks.APMS.domain.Project.ProjectRepository;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectProposalService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public void createProject(User user, User manager, ProjectProposal projectProposal) {
        Project project = new Project(projectProposal, user, manager);
        projectRepository.save(project);
        List<Project> userProjects = user.getProjectList();
        userProjects.add(project);
        userRepository.save(user);
    }
}
