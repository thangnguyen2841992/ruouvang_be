package com.thang.story.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private double newPrice;

    private String description;

    private int capacity;

    private String grape;

    private String producer;

    private double concentration;

    private String region;

    @Lob
    @Column
    private String content;

    private int quantity;

    private String image;

    private Long originId;

    private Long accessoryId;

    private Long typeId;
}
