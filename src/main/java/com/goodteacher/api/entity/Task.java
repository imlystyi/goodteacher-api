package com.goodteacher.api.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private UUID id;
    private String name;
    private String text;
    private String quiz;

}
