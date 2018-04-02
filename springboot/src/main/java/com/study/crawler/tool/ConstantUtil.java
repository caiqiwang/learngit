package com.study.crawler.tool;

import org.springframework.beans.factory.annotation.Value;

public class ConstantUtil {
	public static final String URL = "https://www.lagou.com/";
	@Value("${com.study.ThreadNumber}")
	public static int number;
}
