package com.thang.story.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String price;

    private String description;

    private String content;

    private int quantity;

    private int capacity;

    private String grape;


    private String producer;

    private double concentration;

    private String region;

    private String image;

    private String accessory;

    private String origin;

    private String type;
}
