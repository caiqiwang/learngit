package com.study.crawler.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.crawler.entity.CategoryInfo;

@Mapper
public interface CategoryDao {
	void insertCategoryInfo(CategoryInfo categroyInfo);
}
