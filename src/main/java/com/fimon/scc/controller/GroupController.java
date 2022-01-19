package com.fimon.scc.controller;

import com.fimon.scc.entity.Group;
import com.fimon.scc.entity.Response;
import com.fimon.scc.service.GroupService;
import com.fimon.scc.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public Response createGroup(@RequestBody Group group) {
        if (group.getName() == null || group.getName().length() == 0)
            return Response.error("group name can't be empty");
        log.info("create new group, name: " + group.getName());
        return groupService.createGroup(group);
    }

    @PostMapping("/update")
    public Response updateGroup(@RequestBody Group group) {
        if (group.getId() == null)
            return Response.error("group id can't be empty");
        if (group.getName() != null && group.getName().length() == 0)
            return Response.error("can't set group name to empty");
        log.info("update group info, id: " + group.getId());
        return groupService.updateGroup(group);
    }

    @GetMapping("/all")
    public Response getAllGroupDetails() {
        log.info("get details of all groups");
        return groupService.getAllGroups();
    }

    @GetMapping("/details/{id}")
    public Response getGroupDetails(@PathVariable("id") String str) {
        long id = IdGenerator.parseId(str);
        log.info("get details of group " + id);
        return groupService.getDetails(id);
    }

    @GetMapping("/delete/{id}")
    public Response deleteGroup(@PathVariable("id") String str) {
        long id = IdGenerator.parseId(str);
        log.info("delete group " + id);
        return groupService.deleteGroup(id);
    }
}
