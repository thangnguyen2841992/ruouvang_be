package com.thang.story.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
    private Long id;

    private int quantity;

    private String payment;

    private String productImg;

    private String productName;

    private String productPrice;

    private String content;

    private Long userId;
}
