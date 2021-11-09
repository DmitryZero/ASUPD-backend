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
    }
}
