package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Profile;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:55:34
 * @see Class Description
 */
public interface ProfileMapper {
	/**
	 * 添加账户
	 * 
	 * @param profile
	 * @return
	 */
	public int addProfile(Profile profile);

	/**
	 * 列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Profile> getProfileListByLevel(@Param(value = "level") int level, @Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 获取账户
	 * 
	 * @param UserId
	 * @return
	 */
	public Profile getProfile(long UserId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userName
	 * @return
	 */
	public Profile getProfileByUserName(@Param(value = "username") String userName);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public List<Profile> getProfileByClassId(@Param(value = "classId") long classId, @Param(value = "level") int level);

	/**
	 * 得到最大的
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public Profile getMaxProfileByNumber(@Param(value = "classId") long classId);

	/**
	 * 改密码
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @param password
	 * @return
	 */
	public int updateProfilePassword(@Param(value = "userId") long userId, @Param(value = "password") String password);
}
