package com.tedu.sp04.order.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.OrderService;
import com.tedu.sp01.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	
	@GetMapping("/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		log.info("get order, id="+orderId);
		
		Order order = orderService.getOrder(orderId);
		return JsonResult.ok(order);
	}
	
	
	
	@GetMapping("/")
	public JsonResult addOrder() {
		//模拟post提交的数据
		Order order = new Order();
		order.setId("123abc");
		order.setUser(new User(7,null,null));
		order.setItems(Arrays.asList(new Item[] {
				new Item(1,"迪丽热巴",2),
				new Item(2,"古力娜扎",1),
				new Item(3,"马尔扎哈",3),
				new Item(4,"郑合惠子",1),
				new Item(5,"吉克隽逸",5),
		}));
		orderService.addOrder(order);
		return JsonResult.ok();
	}
}
