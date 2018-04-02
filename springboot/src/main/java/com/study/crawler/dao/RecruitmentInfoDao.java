package com.study.crawler.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.crawler.entity.RecruitmentInfo;

@Mapper
public interface RecruitmentInfoDao {
	void insertRecruitmetInfo(RecruitmentInfo recruitmentInfo);
}
