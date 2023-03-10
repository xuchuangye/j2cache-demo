package com.mashibing.j2cache_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @date 2023/3/9 - 21:14
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
