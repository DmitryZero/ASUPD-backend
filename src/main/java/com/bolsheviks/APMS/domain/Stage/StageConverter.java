package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.domain.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class StageConverter {

    public StageDto convertStageToDto(Stage stage) {
        StageDto stageDto = new StageDto();
        stageDto.id = stage.getId();
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
