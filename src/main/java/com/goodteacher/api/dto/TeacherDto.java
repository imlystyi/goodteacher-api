package com.goodteacher.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String about;
    private String status;

    // TODO-1, Vladyslav: Uncomment this when GroupDto is implemented
    //private Set<GroupDto> groups;
}
