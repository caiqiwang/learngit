package com.study.crawler.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.study.crawler.category.WebsiteCategory;
import com.study.crawler.category.abstracts.impl.LagouCategoryImpl;
import com.study.crawler.details.abstracts.impl.DetailImpl;
import com.study.crawler.lists.abstracts.impl.ListImpl;
import com.study.crawler.tool.ConstantUtil;

@Service
public class CrawlerFactory {
	public void startCrawler() {
		BlockingQueue<String> categoryQueue = new LinkedBlockingQueue<String>();// 存储分类信息
		BlockingQueue<String> listQueue = new LinkedBlockingQueue<String>();// 存储每个分类下的所有详情url
		ExecutorService service = Executors.newFixedThreadPool(5);
		AtomicInteger atomic = new AtomicInteger(0);
		//
		WebsiteCategory websiteCategory = new LagouCategoryImpl();
		categoryQueue = websiteCategory.getWebsiteCategory();
		System.out.println("分类信息获取完毕");
		for (int i = 0; i <= ConstantUtil.number; i++) {
			new ListImpl(categoryQueue, listQueue, service, atomic);
		}
		System.out.println("list信息获取完毕");
		for (int i = 0; i <= ConstantUtil.number; i++) {
			new DetailImpl(listQueue, service, atomic);
		}

		service.shutdown();
		while (!service.isTerminated()) {
			try {
				TimeUnit.SECONDS.sleep(10);
				service.shutdown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
