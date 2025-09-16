package com.example.commonlibrary.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ServiceDTO {
    private UUID id;
    private String code;
    private String name;
}
