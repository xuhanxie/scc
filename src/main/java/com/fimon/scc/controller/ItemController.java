package com.fimon.scc.controller;

import com.fimon.scc.entity.Item;
import com.fimon.scc.entity.params.ListParam;
import com.fimon.scc.entity.Response;
import com.fimon.scc.service.ItemService;
import com.fimon.scc.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public Response createItemController(@RequestBody Item item) {
        if (item.getName() == null || item.getName().length() == 0)
            return Response.error("name can't be empty");

        log.info("create new item, name: " + item.getName());
        return itemService.createItem(item);
    }

    @PostMapping("/update")
    public Response updateItemController(@RequestBody Item item) {
        if (item.getSku() == null)
            return Response.error("sku can't be empty");
        if (item.getName() != null && item.getName().length() == 0)
            return Response.error("can't set item name to empty");
        log.info("update details of item " + item.getSku());
        return itemService.updateItem(item);
    }

    @GetMapping("/details/{id}")
    public Response viewItemController(@PathVariable("id") String id) {
        long sku = IdGenerator.parseId(id);
        log.info("query details of (sku)" + sku);
        return itemService.itemDetails(sku);
    }

    @GetMapping("/delete/{id}")
    public Response deleteItemController(@PathVariable("id") String id) {
        long sku = IdGenerator.parseId(id);
        log.info("delete (sku)" + sku);
        return itemService.deleteItem(sku);
    }

    @PostMapping("/list")
    public Response fetchItemsController(@RequestBody ListParam params) {
        if (params.getPage() <= 0 || params.getPageSize() <= 0)
            return Response.error("invalid params");
        log.info("fetch item list, params: " + params.toString());
        return itemService.fetchItems(params);
    }

    @GetMapping("/count")
    public Response getCountController() {
        log.info("get count of all items");
        return itemService.getCount();
    }

}
