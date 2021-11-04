package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.domain.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageConverter {

    private final StageRepository stageRepository;

    public StageDto convertStageToDto(Stage stage) {
        StageDto stageDto = new StageDto();
        stageDto.name = stage.getName();
        stageDto.cardUuidList = stage.getCardList().stream().map(BaseEntity::getId).toList();
        return stageDto;
    }

    public void fillStageFromDto(Stage stage, StageDto stageDto) {
        if (stageDto.name != null) {
            stage.setName(stageDto.name);
        }
        if (stageDto.cardUuidList != null) {
            // TODO: 04.11.2021 Разобраться с сильной связанностью (меня связали на даче)
            stage.setCardList(stageDto.cardUuidList.stream().map().ToList());
        }
    }

    public Stage saveNewStageFromDto(StageDto stageDto) {
        Stage stage = new Stage();
        if (stageDto.name != null) {
            stage.setName(stageDto.name);
            stageRepository.save(stage);
        }
        return stage;
    }

    public List<Stage> createStageListFromDtoList(List<StageDto> stageDtoList) {
        return stageDtoList.stream().map(this::saveNewStageFromDto).toList();
    }
}
