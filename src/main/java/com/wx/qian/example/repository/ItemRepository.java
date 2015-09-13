package com.wx.qian.example.repository;

import com.wx.qian.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qwx89 on 2015/9/13.
 */
public interface ItemRepository extends JpaRepository<Item,Integer> {

}
