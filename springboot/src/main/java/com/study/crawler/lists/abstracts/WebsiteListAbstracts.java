package com.study.crawler.lists.abstracts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import com.study.crawler.lists.WebsiteList;

public abstract class WebsiteListAbstracts implements WebsiteList, Runnable {
	private BlockingQueue<String> categoryQueue;// 分类信息
	private BlockingQueue<String> listQueue;// 存储每个分类下的所有详情url
	private ExecutorService service;
	private AtomicInteger atomic;

	public WebsiteListAbstracts(BlockingQueue<String> categoryQueue, BlockingQueue<String> listQueue,
			ExecutorService service, AtomicInteger atomic) {
		this.categoryQueue = categoryQueue;
		this.listQueue = listQueue;
		this.service = service;
		this.atomic = atomic;
	}

	public WebsiteListAbstracts() {

	}

	public void run() {
		// TODO Auto-generated method stub
		String url = null;
		while (categoryQueue.size() > 0) {
			url = getUrl();
			if (url != null) {
				getWebsiteList(url);
			}
		}
		// 条件
		atomic.getAndIncrement();
	}

	public String getUrl() {
		String url = null;
		try {
			synchronized (categoryQueue) { // 保证线程原子性 判断跟取url同步进行
				if (categoryQueue.size() > 0) {
					url = categoryQueue.take();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;

	}

	public void getWebsiteList(String str) {
		// TODO Auto-generated method stub
	}

	public abstract int getAllPage(String categoryUrl);
}
