package com.mashibing.j2cache_demo.controller;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @date 2023/3/9 - 21:42
 */
@RestController
@RequestMapping("/j2cache")
public class J2cacheController {

	//key根据业务决定
	private String key = "myKey";
	//测试区域
	private String testRegion = "testRegion";

	/**
	 * 取缓存，设置缓存
	 */
	@Autowired
	private CacheChannel cacheChannel;


	@GetMapping("/getValue")
	public String getValue() {
		CacheObject result = cacheChannel.get(testRegion, key);
		if (result.getValue() == null) {
			//从数据库获取的value
			String valueDB = "数据库的值";
			cacheChannel.set(testRegion, key, valueDB);
			return "数据库的值";
		}
		return "";
	}

	/**
	 * 删除J2Cache中的缓存数据
	 *
	 * @return
	 */
	@GetMapping("/evict")
	public String evict() {
		CacheObject result = cacheChannel.get(testRegion, key);
		if (result.getValue() == null) {
			return "evict fail";
		}
		cacheChannel.evict(testRegion, key);
		return "evict success";
	}

	/**
	 * 清除缓存
	 *
	 * @return
	 */
	@GetMapping("/clear")
	public String clear() {
		CacheObject result = cacheChannel.get(testRegion, key);
		if (result.getValue() == null) {
			return "clear fail";
		}
		cacheChannel.clear(testRegion);
		return "clear success";
	}

	/**
	 * 判断某个key存在于哪级的缓存中
	 *
	 * @return 0(不存在), 1(一级), 2(二级)
	 */
	@GetMapping("/check")
	public String check() {
		CacheObject result = cacheChannel.get(testRegion, key);
		if (result.getValue() == null) {
			return "cache is null";
		}

		int check = cacheChannel.check(testRegion, key);
		return "cache in:" + check;
	}
}
