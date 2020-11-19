/**
 * 功能描述 
 * 文件名 UserMapper.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午7:49:36
 **/
package com.zy.blog.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.zy.blog.dbentity.User;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface UserMapper {
	/**
	 * 根据用户登录方式查询是否存在
	 * @param identity
	 * @return
	 */
	@Select("SELECT * FROM user WHERE u_email=#{identity} or u_username=#{identity}")
	@Results(id="userMap",value={
		@Result(property = "userUid",column = "u_uid",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "email",column = "u_email",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "userName",column = "u_username",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "pwd",column = "u_pwd",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "displayName",column = "display_name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "status",column = "u_status",javaType = byte.class,jdbcType = JdbcType.TINYINT),
		@Result(property = "avatarImgUrl",column = "u_avatar",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "desc",column = "u_desc",javaType = String.class,jdbcType = JdbcType.VARCHAR),
		@Result(property = "recentlyLandedDate",column = "recently_landed_time"),
		@Result(property = "isAdmin",column = "isAdmin")
	})
	public User findByIdentity(String identity);
	
	@Select("SELECT * FROM user WHERE u_uid=#{userUid}")
	@ResultMap(value = "userMap")
	public User findByUserUid(String userUid);
	
	@Select("SELECT * FROM user WHERE u_email=#{email}")
	@ResultMap(value = "userMap")
	public User findByEmail(String email);
	
	/**
	 * 注册用户
	 * @param username 用户名
	 * @param email 用户邮箱
	 * @param pwd 用户密码
	 */
	@Insert("INSERT INTO user (u_uid,u_username,u_email,display_name,u_pwd,u_status)VALUES(#{userUid},#{username},#{email},#{nickName},sha(#{pwd}),0)")
	public void addUser(@Param("userUid")String userUid,
			@Param("username")String username,
			@Param("nickName")String nickName,
			@Param("email")String email,
			@Param("pwd")String pwd);

	/**
	 * 更新用户激活状态
	 * @param userUid
	 */
	@Update("UPDATE user SET u_status=1 WHERE u_uid=#{userUid}")
	public void activateUser(String userUid);
	
	/**
	 * 更新用户登录时间
	 * @param userUid
	 * @param currentTime
	 */
	@Update("UPDATE user SET recently_landed_time=#{currentTime} WHERE u_uid=#{userUid}")
	public void updateLoginTime(@Param("userUid")String userUid,@Param("currentTime")Date currentTime);
}
