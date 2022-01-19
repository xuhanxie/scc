package com.fimon.scc.service;

import com.fimon.scc.entity.Group;
import com.fimon.scc.entity.Response;
import com.fimon.scc.mapper.ClassifyMapper;
import com.fimon.scc.mapper.GroupMapper;
import com.fimon.scc.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GroupService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    public Response createGroup(Group group) {
        long id = idGenerator.nextId();
        group.setId(id);
        groupMapper.createGroup(group);
        log.info("create group " + group.getName() + " successfully");
        Map<String, Object> ret = new HashMap<>();
        ret.put("id", id);
        return Response.success("success", ret);
    }

    public Response updateGroup(Group group) {
        groupMapper.updateGroup(group);
        log.info("update group info of " + group.getId() + " successfully");
        return Response.success("success");
    }

    public Response getAllGroups() {
        Group[] res = groupMapper.getAllGroups();
        log.info("get details of all groups successfully");
        return Response.success("success", res);
    }

    public Response getDetails(long id) {
        Group detail = groupMapper.getDetails(id);
        if (detail == null) {
            log.info("no such group, id: " + id);
            return Response.error("no such group");
        }
        log.info("get details of group " + id + " successfully");
        return Response.success("success", detail);
    }

    @Transactional
    public Response deleteGroup(long id) {
        classifyMapper.removeAll(id);
        groupMapper.deleteGroup(id);
        log.info("delete group " + id + " successfully");
        return Response.success("success");
    }

}
