package com.fimon.scc.service;

import com.fimon.scc.entity.Item;
import com.fimon.scc.entity.params.ListParam;
import com.fimon.scc.entity.Response;
import com.fimon.scc.mapper.ItemMapper;
import com.fimon.scc.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ItemMapper itemMapper;

    public Response createItem(Item item) {
        long id = idGenerator.nextId();
        item.setSku(id);

        itemMapper.createItem(item);

        log.info("create new item success, sku: " + id);

        Map<String, Object> res = new HashMap<>();
        res.put("sku", id);
        return Response.success("success", res);
    }

    public Response updateItem(Item item) {
        itemMapper.updateItem(item);
        log.info("update item " + item.getSku() + " successfully");
        return Response.success("success");
    }

    public Response itemDetails(long sku) {
        Item item = itemMapper.itemDetails(sku);
        if (item == null) {
            log.warn("no such item " + sku);
            return Response.error("no such item");
        }
        log.info("get details of " + sku + " successfully");
        return Response.success("success", item);
    }

    public Response deleteItem(long sku) {
        itemMapper.deleteItem(sku);
        log.info("delete item " + sku + " successfully");
        return Response.success("success");
    }

    public Response fetchItems(ListParam params) {
        Item[] res = itemMapper.fetchItems(params);
        log.info("fetch items successfully");
        return Response.success("success", res);
    }

    public Response getCount() {
        int count = itemMapper.count();
        log.info("get count of items successfully, count: " + count);
        Map<String, Integer> ret = new HashMap<>();
        ret.put("count", count);
        return Response.success("success", ret);
    }

}
