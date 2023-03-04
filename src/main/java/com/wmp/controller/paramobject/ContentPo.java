package com.wmp.controller.paramobject;

import com.wmp.enums.ParseType;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ContentPo {

    @URL
    @NotBlank(message = "URL은 필수입니다.")
    private String url;

    private ParseType type;

    @Min(value = 1,message = "출력 단위 묶음은 최소 1이상의 값이여야 합니다.")
    private int operand;

}
