package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigmentDTO {

    private UUID id;
    @NotNull
    private Task task;
    //@NotNull
    //private Studet studet;
    //@NotNull
    //private Teacher teacher;
    private Double grade;
    private LocalDate deadline;

}