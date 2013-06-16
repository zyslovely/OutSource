package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.ProfileProperty;

public interface ProfilePropertyMapper {
	public int addProfileProperty(ProfileProperty profileProperty);

	public List<ProfileProperty> getProfileProperty(@Param(value = "userId") long userId);
}