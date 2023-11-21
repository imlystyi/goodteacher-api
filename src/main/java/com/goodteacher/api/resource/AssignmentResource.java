package com.goodteacher.api.resource;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.service.AssignmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {
    private AssignmentService assignmentService;

    @GetMapping("/find/{id}")
    public AssignmentDTO findById(@PathVariable final UUID id) {
        return assignmentService.findDTOById(id);
    }

    @PostMapping("/save")
    public AssignmentDTO save(final @RequestBody AssignmentDTO assignmentDTO) {
        return assignmentService.save(assignmentDTO);
    }

    @PutMapping("/update")
    public AssignmentDTO update(final @RequestBody AssignmentDTO assignmentDTO) {
        return assignmentService.update(assignmentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable final UUID id) {
        assignmentService.deleteById(id);
    }
}