package com.goodteacher.api.dto;


import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;

    private String name;

    private String about;

    private TeacherDto teacher;

    private Set<StudentDto> students;


    public static GroupDto toDTO(final Group group) {
        GroupDto groupDTO = new GroupDto();

        //groupDTO.setId(group.getId());
        //groupDTO.setStudentsId(group.getStudents().stream().map(Student::getId).collect(Collectors.toSet()));
        //groupDTO.setTeacherId(group.getTeacher().getId());
        groupDTO.setName(group.getName());
        groupDTO.setAbout(group.getAbout());

        return groupDTO;
    }
}
