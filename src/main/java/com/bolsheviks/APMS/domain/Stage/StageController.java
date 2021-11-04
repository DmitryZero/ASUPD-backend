package com.bolsheviks.APMS.domain.Stage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/stage")
@RequiredArgsConstructor
public class StageController {

    private final StageRepository stageRepository;
    private final StageConverter stageConverter;

    @GetMapping("/{uuid}")
    public StageDto get(@PathVariable("uuid")UUID uuid) {
        Stage stage = stageRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return stageConverter.convertStageToDto(stage);
    }

    @PutMapping("/{uuid}")
    public void put(@PathVariable("uuid")UUID uuid, @RequestBody StageDto stageDto) {
        Stage stage = stageRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        stageConverter.
    }
}
