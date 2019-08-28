package com.tedu.sp02.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

	@Override
	public List<Item> getItems(String orderId) {
		// TODO Auto-generated method stub
		ArrayList<Item> itemList = new ArrayList<>();
		itemList.add(new Item(1,"迪丽热巴",18));
		itemList.add(new Item(2,"古力娜扎",19));
		itemList.add(new Item(3,"马尔扎哈",20));
		itemList.add(new Item(4,"郑合惠子",21));
		itemList.add(new Item(5,"吉克隽逸",22));
		return itemList;
	}

	
	
	@Override
	public void decreaseNumbers(List<Item> items) {
		// TODO Auto-generated method stub
		if (log.isInfoEnabled()) {
			for (Item item : items) {
				log.info("减少库存--"+item);
			}
		}
	}

}
