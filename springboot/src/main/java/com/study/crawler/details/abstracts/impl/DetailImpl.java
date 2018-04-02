package com.study.crawler.details.abstracts.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.small.crawler.util.DateTimeUtil;
import com.study.crawler.details.abstracts.WebsiteDetailAbstract;
import com.study.crawler.entity.CompanyInfo;
import com.study.crawler.entity.RecruitmentInfo;
import com.study.crawler.tool.DocumentUtil;

public class DetailImpl extends WebsiteDetailAbstract {
	// CompanyManagerImpl campanyImpl =
	// ApplicationContextSave.getBean(CompanyManagerImpl.class);
	// RecruitmentInfoManagerImpl recruitmentInfoImpl =
	// ApplicationContextSave.getBean(RecruitmentInfoManagerImpl.class);

	public DetailImpl(BlockingQueue<String> listQueue, ExecutorService service, AtomicInteger atomic) {
		super(listQueue, service, atomic);
		service.execute(this);
	}

	public DetailImpl() {

	}

	public static void main(String[] args) {
		DetailImpl impl = new DetailImpl();
		impl.getCompanyInfo("https://www.lagou.com/jobs/2907194.html");

	}

	@Override
	public void getWebsiteDetail(String detailUrl) {
		CompanyInfo companyInfo = getCompanyInfo(detailUrl);
		// campanyImpl.insertCompanyInfo(companyInfo);
		RecruitmentInfo recruitmentInfo = getRecruitmentInfo(detailUrl);
		// recruitmentInfoImpl.insertRecruitmetInfo(recruitmentInfo);
	}

	/* 传入详情页url
	 * 用来获取公司信息
	 */
	@Override
	public CompanyInfo getCompanyInfo(String detailUrl) {
		CompanyInfo companyInfo = new CompanyInfo();
		Document document = DocumentUtil.getDocument(detailUrl);
		if (document == null) {
			System.out.println(" getCompanyInfo 的detail 页面进入失败");
			return null;
		}
		Elements elements = document.select("div[class=content_r]");
		if (elements == null) {
			System.out.println("detail 获取公司页面div失败");
			return null;
		}
		elements = elements.select("dl[class=job_company]");
		// 获取公司名称
		Elements companyNames = elements.select("dt");
		companyNames = companyNames.select("a");
		String companyName = companyNames.select("img").attr("alt");// 公司名
		// 获取其他信息
		Elements detailInfo = elements.select("dd");
		detailInfo = detailInfo.select("ul[class=c_feature]");
		detailInfo = detailInfo.select("li");
		String companyDomain = detailInfo.get(0).text(); // 领域
		String developmentalPhase = detailInfo.get(1).text();// 发展阶段
		String companyScale = detailInfo.get(2).text(); // 公司规模
		String companyHomepage = detailInfo.get(3).text();// 主页
		companyInfo.setCompanyName(companyName);
		companyInfo.setCompanyDomain(companyDomain);
		companyInfo.setCompanyHomepage(companyHomepage);
		companyInfo.setCompanyScale(companyScale);
		companyInfo.setDevelopmentalPhase(developmentalPhase);
		return companyInfo;
	}

	/* 传入详情页url
	 * 用来获取招聘信息
	 */
	@Override
	public RecruitmentInfo getRecruitmentInfo(String detailUrl) {
		RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
		Document document = DocumentUtil.getDocument(detailUrl);
		if (document == null) {
			System.out.println(" getRecruitmentInfo 的detail 页面进入失败");
			return null;
		}
		// 进入 招聘信息div
		Elements elements = document.select("position-content ");
		if (elements == null) {
			System.out.println("detail 进入招聘页面顶部div失败");
			return null;
		}
		elements = elements.select("div[class=position-content-l]");

		Elements baseInfo = elements.select("div[job-name]");
		String departmentInfo = baseInfo.select("div[class=company]").text();// 招聘部门名称
		String positionInfo = elements.select("span").text(); // 招聘职位信息

		Elements baseInfo2 = elements.select("div[class=job_request]");
		Elements label = baseInfo2.select("ul[class=position-label clearfix]");
		label = label.select("li");
		String jobLableInfo = null;
		for (int i = 0; i < label.size(); i++) {
			if (i != 0) {
				jobLableInfo = jobLableInfo + ", ";// 用于拼接标签
			}
			jobLableInfo = label.get(i).text(); // 职位标签
		}
		baseInfo2 = baseInfo2.select("p"); // 有2个p标签
		String releaseTime = baseInfo2.get(1).text(); // 发布时间,及拼接
		String dayNum = null;
		if (releaseTime.indexOf("天") > 0) {
			dayNum = releaseTime.substring(0, 1);
			int offset = Integer.parseInt(dayNum);
			offset = offset - offset * 2;
			dayNum = DateTimeUtil.getTime("yyyy-MM-dd", offset);
			releaseTime = dayNum + releaseTime.split("前")[1];

		} else if (releaseTime.indexOf(":") > 0) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateFormat.format(now);
			releaseTime = time + " 发布于拉勾网";
			// System.out.println(releaseTime);
		}

		baseInfo2 = baseInfo2.get(0).select("span");
		String payInfo = baseInfo2.get(0).text(); // 薪资信息
		String placeInfo = baseInfo2.get(1).text(); // 工作地点
		String workExperienceInfo = baseInfo2.get(2).text(); // 工作经验
		String educationInfo = baseInfo2.get(3).text(); // 学历要求
		String jobCategoryInfo = baseInfo2.get(4).text(); // 工作性质

		Elements otherInfo = document.select("div[class=content_l fl]");
		// middleinfo 包含任职要求和职位诱惑 ，详细工作地点 发布者信息
		Elements middleinfo = otherInfo.select("div[id=job_detail]");
		middleinfo = middleinfo.select("dd");
		String positionTheTemptation = middleinfo.get(0).select("p").text(); // 职位诱惑
		Elements workInfo = middleinfo.get(1).select("div");
		workInfo = workInfo.select("p");
		String info = null;// 包含岗位职责和任职要求
		for (int i = 0; i < workInfo.size(); i++) {
			info = info + workInfo.get(i);
		}
		String[] infos = info.split("任职要求：");
		String jobDescription = infos[0]; // 岗位职责
		String jobRequirements = "任职要求：" + infos[1]; // 任职要求

		Elements DetailPlace = middleinfo.get(2).select("div[class=work_addr]");
		String workPlace = DetailPlace.text().split("查看地图")[0]; // 详细工作地点
		// 发布者信息
		Elements promulgator = middleinfo.get(3).select("class=[border clearfix]");
		Elements publisherName = promulgator.select("div[class=publisher_name]");
		publisherName = publisherName.select("a");
		String jobPromulgator = publisherName.select("span").text(); // 职位发布者姓名
		promulgator = promulgator.select("div[class=publisher_data]");
		promulgator = promulgator.select("div");
		// 聊天意愿
		String chatWith = "聊天意愿：";
		Elements chat = promulgator.get(0).select("span");
		chatWith = chatWith + promulgator.get(2).text();
		// 判断内容
		String timeInfo = chat.get(3).text();
		if (timeInfo.length() > 7) {
			timeInfo = timeInfo.substring(timeInfo.length() - 3);
		}
		chat = chat.get(3).select("i");
		// 拼接聊天意愿信息
		chatWith = chatWith + "回复率" + chat.get(0).text() + "用时" + chat.get(1).text() + timeInfo;
		// 简历处理div
		String resumeProcessing = "简历处理："; // 简历处理
		resumeProcessing = promulgator.get(2).select("span").text();
		Elements Processing = promulgator.get(3).select("i");
		resumeProcessing = resumeProcessing + "处理率" + Processing.get(0).text() + "用时" + Processing.get(1).text() + "天";
		// 活跃时段
		String activePeriodOfTime = "活跃时段：";
		activePeriodOfTime = promulgator.get(2).select("span").text();
		activePeriodOfTime = activePeriodOfTime + "早" + promulgator.get(3).select("i").text() + "点最活跃";
		// 面试评价----

		// 存放到实体类
		recruitmentInfo.setDepartmentInfo(departmentInfo);
		recruitmentInfo.setPositionInfo(positionInfo);
		recruitmentInfo.setPayInfo(payInfo);
		recruitmentInfo.setPlaceInfo(placeInfo);
		recruitmentInfo.setWorkExperienceInfo(workExperienceInfo);
		recruitmentInfo.setEducationInfo(educationInfo);
		recruitmentInfo.setJobCategoryInfo(jobCategoryInfo);
		recruitmentInfo.setJobLableInfo(jobLableInfo);
		recruitmentInfo.setReleaseTime(releaseTime);
		recruitmentInfo.setPositionTheTemptation(positionTheTemptation);
		recruitmentInfo.setJobDescription(jobDescription);
		recruitmentInfo.setJobRequirements(jobRequirements);
		recruitmentInfo.setWorkPlace(workPlace);
		recruitmentInfo.setJobPromulgator(jobPromulgator);
		recruitmentInfo.setChatWith(chatWith);
		recruitmentInfo.setResumeProcessing(resumeProcessing);
		recruitmentInfo.setActivePeriodOfTime(activePeriodOfTime);
		recruitmentInfo.setInterviewAppraisal("");// 未写
		return recruitmentInfo;
	}

}
