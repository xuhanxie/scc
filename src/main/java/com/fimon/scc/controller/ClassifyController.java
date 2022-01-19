package com.fimon.scc.controller;

import com.fimon.scc.entity.Response;
import com.fimon.scc.entity.params.AssignParam;
import com.fimon.scc.service.ClassifyService;
import com.fimon.scc.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    @PostMapping("/assign")
    public Response assignToGroup(@RequestBody AssignParam params) {
        if (params.getSku() == null || params.getGroupId() == null)
            return Response.error("invalid params");

        log.info("assign sku " + params.getSku() + " to " + params.getGroupId());
        return classifyService.assign(params);
    }

    @GetMapping("/remove/{sku}")
    public Response removeFromGroup(@PathVariable("sku") String str) {
        long sku = IdGenerator.parseId(str);
        log.info("remove sku " + sku + " from a group");
        return classifyService.remove(sku);
    }

}
