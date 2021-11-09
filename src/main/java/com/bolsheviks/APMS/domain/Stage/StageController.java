package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.domain.Project.Project;
import com.bolsheviks.APMS.domain.Project.ProjectRepository;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.bolsheviks.APMS.security.SecurityFilter.USER_UUID;

@RestController
@RequestMapping("/stage")
@RequiredArgsConstructor
public class StageController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final StageRepository stageRepository;
    private final StageConverter stageConverter;

    @GetMapping("/{uuid}")
    public StageDto get(@RequestAttribute(USER_UUID) UUID userId,
                        @RequestParam("projectUuid") UUID projectUuid,
                        @PathVariable("uuid") UUID uuid) {
        if (!canUserGetStage(userId, projectUuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Stage stage = stageRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return stageConverter.convertStageToDto(stage);
    }

    @PostMapping
    public void post(@RequestAttribute(USER_UUID) UUID userId,
                     @RequestParam("projectUuid") UUID projectUuid,
                     @RequestBody StageDto stageDto) {
        if (!canUserModifyStage(userId, projectUuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Stage stage = new Stage();
        stageConverter.fillStageFromDto(stage, stageDto);
        stageRepository.save(stage);
    }

    @PutMapping("/{uuid}")
    public void put(@RequestAttribute(USER_UUID) UUID userId,
                    @RequestParam("projectUuid") UUID projectUuid,
                    @PathVariable("uuid") UUID uuid,
                    @RequestBody StageDto stageDto) {
        if (!canUserModifyStage(userId, projectUuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Stage stage = stageRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        stageConverter.fillStageFromDto(stage, stageDto);
        stageRepository.save(stage);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@RequestAttribute(USER_UUID) UUID userId,
                       @RequestParam("projectUuid") UUID projectUuid,
                       @PathVariable("uuid") UUID uuid) {
        if (!canUserModifyStage(userId, projectUuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        stageRepository.deleteById(uuid);
    }

    private boolean canUserModifyStage(UUID userId, UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return project.containsUserWithModifyRights(user);
    }

    private boolean canUserGetStage(UUID userId, UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return project.containsUser(user);
    }
}
