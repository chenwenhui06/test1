package com.tedu.sp02.item.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;
import com.tedu.sp01.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	// 为了后面集群测试，查看端口
	@Value("${server.port}")     // 将yml文件中的port注入
	private int port;
	
	
	// 指定Get请求
	@GetMapping("/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws Exception{
		log.info("server.port = "+port+", orderId = "+orderId);
		
		///--设置随机延迟
		long t = new Random().nextInt(5000);
			if(Math.random()<0.6) {    // 60%的概率暂停
				log.info("item-service-"+port+" - 暂停 "+t);
				Thread.sleep(t);
			}
		
		List<Item> items = itemService.getItems(orderId);
		return JsonResult.ok(items).msg("port = "+port);
	}
	
	
	
	/**
	 * 指定Post请求时，是将json数据存放在http请求中的协议体中，则需添加@RequestBody
	 */
	@PostMapping("/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		itemService.decreaseNumbers(items);
		return JsonResult.ok();
	}
}
