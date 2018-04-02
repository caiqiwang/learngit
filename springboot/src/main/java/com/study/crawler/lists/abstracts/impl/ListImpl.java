package com.study.crawler.lists.abstracts.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.small.crawler.util.document.CrawlParam;
import com.small.crawler.util.document.HttpURLConnectionFactory;
import com.study.crawler.lists.abstracts.WebsiteListAbstracts;
import com.study.crawler.tool.DocumentUtil;

public class ListImpl extends WebsiteListAbstracts {
	public static void main(String[] args) {
		ListImpl listImpl = new ListImpl();
		String url = "https://www.lagou.com/jobs/companyAjax.json?city=%E6%9D%AD%E5%B7%9E&needAddtionalResult=false";
		// int a =
		// listImpl.getAllPage("https://www.lagou.com/zhaopin/shichangyingxiao1/?labelWords=label");
		listImpl.getWebsiteList(url);
		// System.out.println(a);
	}

	public ListImpl() {

	}

	public ListImpl(BlockingQueue<String> categoryQueue, BlockingQueue<String> listQueue, ExecutorService service,
			AtomicInteger atomic) {
		super(categoryQueue, listQueue, service, atomic);
		// TODO Auto-generated constructor stub
		service.execute(this);
	}

	public void getWebsiteList(String url) {
		// TODO Auto-generated method stub
		String cookie = "user_trace_token=20180320083537-6aa38784-41e8-4cbe-a4a8-61e155a3d4e2; _ga=GA1.2.1130562059.1521506024; LGUID=20180320083538-9c7d1c4f-2bd6-11e8-8ee5-525400f775ce; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; index_location_city=%E6%9D%AD%E5%B7%9E; hasDeliver=108; _gid=GA1.2.1275686249.1522457023; JSESSIONID=ABAAABAAADEAAFI19F434F0AE895AE5B9F3E4D764364E13; X_HTTP_TOKEN=5867e1730fda854a90bb6247149dc104; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1521939463,1522330574,1522457023,1522547847; LG_LOGIN_USER_ID=fbdf6cba8595c69155b7cb829578171cebc07afb7fb9fa4d; _putrc=6DDA6DD78B0B5EA4; login=true; unick=%E8%94%A1%E5%85%B6%E6%9C%9B; TG-TRACK-CODE=index_navigation; LGSID=20180401183658-9b29ce91-3598-11e8-b6d1-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Fwww.lagou.com%2F; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fzhaopin%2FHTML5%2F%3FlabelWords%3Dlabel; gate_login_token=bdf9652d66649b8b5099da427edff104ccbb997326b6a95e; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1522578893; LGRID=20180401183700-9bfa3ba4-3598-11e8-abef-525400f775ce; SEARCH_ID=f40bb2bfd2a8457cb488ab44db40d47d";
		CrawlParam crawlParam = new CrawlParam();
		crawlParam.setUrlStr(url);
		crawlParam.setRequestMethod("POST");
		crawlParam.setCookie(cookie);
		crawlParam.setPostParam("no-cache");
		String info = HttpURLConnectionFactory.getDocumentStr(crawlParam);
		System.out.println(info);
		/*Document document = DocumentUtil.getDocument(url);
		if (document == null) {
			System.out.println("获取list页面 失败");
		}
		Elements elements = document.select("div[class=s_position_list ]");
		if (elements == null) {
			System.out.println("list  进入职位列表 div失败");
		}
		elements = elements.select("ul[class=item_con_list]");
		elements = elements.select("li");
		String detailUrl = null;
		// int maxPage=getAllPage(url);
		// for
		for (int i = 0; i < elements.size(); i++) {
			Elements wordUrl = elements.get(i).select("div[class=position]");
			wordUrl = wordUrl.select("div[class=p_top]");
			wordUrl = wordUrl.select("a");
			detailUrl = wordUrl.attr("href");
			System.out.println(detailUrl);
		}*/
	}

	@Override
	public int getAllPage(String categoryUrl) { // 获取最大的翻页数
		// TODO Auto-generated method stub
		Document document = DocumentUtil.getDocument(categoryUrl);
		int page = 1;
		if (document == null) {
			System.out.println("获取list页面 失败");
		}
		Elements elements = document.select("div[class=pager_container]");
		if (elements == null) {
			System.out.println("获取list页面 失败");
		}

		Elements allPage = elements.select("a");
		if (allPage.size() > 2) {
			String maxPage = allPage.get(allPage.size() - 2).text();// 倒数第2个数为最大页数
			page = Integer.parseInt(maxPage);
		}
		return page;
	}
}
