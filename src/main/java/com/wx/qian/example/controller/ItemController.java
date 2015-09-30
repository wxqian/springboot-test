package com.wx.qian.example.controller;

import com.wx.qian.example.model.Item;
import com.wx.qian.example.repository.ItemRepository;
import com.wx.qian.rabbitmq.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by qwx89 on 2015/9/13.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MqService mqService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Item addItem(@RequestBody Item item) {
        item.setId(null);
        mqService.sendMessage("addItem:" + item.getDescription());
        return itemRepository.saveAndFlush(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Item updateItem(@RequestBody Item updateItem, @PathVariable Integer id) {
        updateItem.setId(id);
        return itemRepository.saveAndFlush(updateItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer id) {
        itemRepository.delete(id);
    }
}
