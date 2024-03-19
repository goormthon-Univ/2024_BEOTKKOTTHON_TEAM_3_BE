package com.univ.haksamo.domain.keyword.service.res;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SelectedKeywordDTO {
    private boolean isSelected;
    private Long id;
    private String name;
    private String description;
    public void decideSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
