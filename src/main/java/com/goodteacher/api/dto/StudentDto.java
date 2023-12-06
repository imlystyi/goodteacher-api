package com.goodteacher.api.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private int entryYear;
    private Set<AssignmentDto> assignments;

    // TODO-1, Vladyslav: Uncomment this when GroupDto is implemented
    //private Set<GroupDto> groups;
}
