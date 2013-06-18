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
	public Profile getProfileByUserName(@Param(value = "userName") String userName);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public List<Profile> getProfileByClassId(@Param(value = "classId") long classId, @Param(value = "level") int level,
			@Param(value = "limit") int limit, @Param(value = "offset") int offset);

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

	/**
	 * 更新学生状态
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param status
	 * @return
	 */
	public int updateProfileStatus(@Param(value = "userId") long userId, @Param(value = "status") int status);

	/**
	 * 获取profile列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userIds
	 * @return
	 */
	public List<Profile> getProfileListByIds(@Param(value = "userIds") List<Long> userIds);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Profile> getProfileListWithTeacher(@Param(value = "limit") int limit, @Param(value = "offset") int offset);

	/**
	 * 删除用户信息通过classid
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public int deleteProfileByClassId(@Param(value = "classId") long classId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public int deleteProfileByUserId(@Param(value = "userId") long userId);

}
