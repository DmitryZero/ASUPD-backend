package com.bolsheviks.APMS.domain.ProjectProposal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StageNamesConverter {

    public List<String> convertStageNamesToList(String stageNames) {

        if (stageNames == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(stageNames.split("/")).toList();
    }

    public String convertListToStageNames(List<String> names) {

        if (names.isEmpty()) {
            return null;
        }

        return String.join("/", names);
    }
}
