package com.bolsheviks.APMS.domain;

import com.bolsheviks.APMS.domain.ProjectProposal.ProjectProposal;
import com.bolsheviks.APMS.domain.ProjectProposal.ProjectProposalRepository;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockService {

    private final ProjectProposalRepository projectProposalRepository;
    private final UserRepository userRepository;

    public MockService(ProjectProposalRepository projectProposalRepository,
                       UserRepository userRepository) {
        this.projectProposalRepository = projectProposalRepository;
        this.userRepository = userRepository;

        createMocks();
    }

    private void createMocks() {
        createProjectProposalMocks();

    }

    private void createProjectProposalMocks() {
        ProjectProposal projectProposal = new ProjectProposal();
        projectProposal.setName("Тестовое проектное предложение");
        projectProposal.setInformation("Твоя бабушка курит трубку");
        List<User> users = new ArrayList<>();
        users.add(createUser());
        projectProposal.setProjectManagersList(users);
        projectProposalRepository.save(projectProposal);
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("Антон");
        user.setLastName("Фекалис");
        user.setPatronymic("Павлович");

        userRepository.save(user);
        return user;
    }
}
