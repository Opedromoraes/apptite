package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.enums.CriteriaTypeEnum;
import lombok.*;

@Getter
@Builder
public class CriteriaOperatorsDTO {

    String field;
    CriteriaTypeEnum operator;
    String value;
}
