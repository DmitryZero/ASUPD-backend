package com.bolsheviks.APMS.domain;

import com.bolsheviks.APMS.domain.ProjectProposal.ProjectProposal;
import com.bolsheviks.APMS.domain.ProjectProposal.ProjectProposalRepository;
import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.Stage.StageRepository;
import com.bolsheviks.APMS.domain.User.Role;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MockService {

    private final ProjectProposalRepository projectProposalRepository;
    private final StageRepository stageRepository;
    private final UserRepository userRepository;

    public MockService(ProjectProposalRepository projectProposalRepository,
                       StageRepository stageRepository,
                       UserRepository userRepository) {
        this.projectProposalRepository = projectProposalRepository;
        this.stageRepository = stageRepository;
        this.userRepository = userRepository;

        createMocks();
    }

    private void createMocks() {
        createProjectProposalMocks();
    }

    private void createAdmin() {
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("oralcumshot");
        admin.setRole(Role.BUSINESS_ADMINISTRATOR);
        userRepository.save(admin);
    }

    private void createProjectProposalMocks() {
        ProjectProposal firstProjectProposal = new ProjectProposal();
        firstProjectProposal.setName("АСУ проектной деятельностью");
        firstProjectProposal.setInformation("Разработка АСУПД для ИжГТУ");
        List<User> users = new ArrayList<>();
        users.add(createUser("Шишлина", "Наталья", "Васильевна", Role.CURATOR));
        firstProjectProposal.setProjectManagersList(users);
        ArrayList<User> consultants = new ArrayList<>();
        consultants.add(createUser("Пигалев", "Сергей", "Александрович", Role.CURATOR));
        consultants.add(createUser("Сенилов", "Михал", "Андреич", Role.CURATOR));
        firstProjectProposal.setConsultantList(consultants);
        firstProjectProposal.setStageList(Stream.of(createStage("Разработка и анализ требований")).toList());
        projectProposalRepository.save(firstProjectProposal);

        ProjectProposal secondProjectProposal = new ProjectProposal();
        secondProjectProposal.setName("Виртуальные лаборатории");
        secondProjectProposal.setInformation("Разработка виртуальных лабораторий на движке Unity");
        List<User> secondUsers = new ArrayList<>();
        secondUsers.add(createUser("Архипов", "Игорь", "Олегович", Role.CURATOR));
        secondProjectProposal.setProjectManagersList(secondUsers);
        ArrayList<User> secondConsultants = new ArrayList<>();
        secondConsultants.add(createUser("Линус", "Торвальдс", "Иванович", Role.CURATOR));
        secondConsultants.add(createUser("Хайсэ", "Сасаки", "Андреич", Role.CURATOR));
        secondProjectProposal.setConsultantList(secondConsultants);
        secondProjectProposal.setStageList(Stream.of(
                createStage("Разработка и анализ требований"),
                createStage("Презентация прототипа"),
                createStage("Заработка деняк")
        ).toList());
        projectProposalRepository.save(secondProjectProposal);
    }

    private User createUser(String firstName, String lastName, String patronymic, Role role) {
        User user = new User();
        user.setRole(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);

        userRepository.save(user);
        return user;
    }

    private Stage createStage(String name) {
        Stage stage = new Stage();
        stage.setName(name);
        stageRepository.save(stage);
        return stage;
    }
}
