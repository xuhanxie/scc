package com.fimon.scc.service;

import com.fimon.scc.entity.Response;
import com.fimon.scc.entity.params.AssignParam;
import com.fimon.scc.mapper.ClassifyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    public Response assign(AssignParam params) {
        classifyMapper.assign(params);
        log.info("assign sku " + params.getSku() + " to " + params.getGroupId() + " successfully");
        return Response.success("success");
    }

    public Response remove(long sku) {
        classifyMapper.remove(sku);
        log.info("remove group info from sku " + sku + " successfully");
        return Response.success("success");
    }

}
