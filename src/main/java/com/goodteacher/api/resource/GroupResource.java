package com.goodteacher.api.resource;


import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.service.impl.GroupServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/group")
public class GroupResource {
    private GroupServiceImpl groupService;


    @GetMapping("/find/{id}")
    public GroupDto findById(@PathVariable final UUID id){
        return groupService.findDTOById(id);
    }

    @PostMapping("/save")
    public GroupDto save(final @RequestBody GroupDto groupDto) {
        return groupService.createGroup(groupDto);
    }
}
