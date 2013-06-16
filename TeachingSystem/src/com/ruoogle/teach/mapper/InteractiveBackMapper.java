package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.InteractiveBack;

public interface InteractiveBackMapper {
	public int addInteractiveBack(InteractiveBack interactiveBack);

	public List<InteractiveBack> getInteractiveBack(@Param(value = "interactiveId") long interactiveId);
}