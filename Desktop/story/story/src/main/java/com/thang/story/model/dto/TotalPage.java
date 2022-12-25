package com.thang.story.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalPage {
    private int totalAlcohol;

    private int totalAccessory;

    private int totalAlcoholByOrigin;

    private int totalAlcoholByType;

    private int totalAccessoryById;
}
