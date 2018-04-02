package com.study.crawler.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.crawler.entity.CompanyInfo;

@Mapper
public interface CompanyDao {
	void insertCompanyInfo(CompanyInfo companyInfo);
}
