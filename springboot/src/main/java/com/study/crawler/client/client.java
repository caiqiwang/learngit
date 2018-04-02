package com.study.crawler.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.study.crawler.factory.CrawlerFactory;

@SpringBootApplication
@ComponentScan(basePackages = { "com.study" })
@MapperScan(basePackages = { "com.study.crawler.dao" }) // 自动寻找该包下的类
public class client {
	public static void main(String[] args) {
		SpringApplication.run(client.class, args);
		CrawlerFactory factory = ApplicationContextSave.getBean(CrawlerFactory.class);
		// CrawlerFactory factory = new CrawlerFactory();
		factory.startCrawler();
		// String str = "3天前 发布于拉勾网";
		// String str2 = "12:33 发布于拉勾网";
		// String str3 = "2018-9-1 发布于拉勾网";
		// System.out.println(str);

	}
}
