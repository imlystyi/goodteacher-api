package com.goodteacher.api.resource;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.service.AssignmentService;
import com.goodteacher.api.service.impl.AssignmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {
    private AssignmentService assignmentService;

    @GetMapping("/{id}")
    public AssignmentDTO findById(final UUID id) {
        return assignmentService.findDTOById(id);
    }

    @PostMapping
    public AssignmentDTO save(final AssignmentDTO assignmentDTO) {
        return assignmentService.save(assignmentDTO);
    }

    @PutMapping
    public AssignmentDTO update(final AssignmentDTO assignmentDTO) {
        return assignmentService.update(assignmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(final UUID id) {
        assignmentService.deleteById(id);
    }
}
