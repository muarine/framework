/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muarine.common.constants.Code;
import com.muarine.common.entity.Menu;
import com.muarine.common.entity.User;
import com.muarine.common.exception.ResultException;
import com.muarine.common.mapper.MenuMapper;
import com.muarine.common.mapper.UserMapper;
import com.muarine.common.vo.Channel;
import com.muarine.common.vo.GlobalUser;

/**
 * PermissionService.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月6日
 * @since 2.0
 */
@Service
public class PermissionService {
	
	@Autowired
	private MemCacheService memCacheService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;
	
	/**
	 * 自学习机制-自动添加新提供api接口
	 * 
	 * @param channel
	 */
	private void autoStudy(Channel channel) throws ResultException, Exception {
		//TODO 查询action	parent_id = 0 action = xxx
		//TODO 查询action/method
		String actionName = channel.getAction();
		Menu menu = menuMapper.selectByHref(channel.getHref());
		// 当前api不在库存内
		if(null == menu){
			// 检测模块menu是否存在
			Menu action = menuMapper.selectByAction(actionName);
			if(null == action){
				action = new Menu();
				action.setCreate_time(new Date());
				action.setCreator("system");
				action.setName(channel.getModuleName());
				action.setParent_id(0L);
				action.setAction(actionName);
				action.setMemo("主菜单");
				action.setType(0);
				menuMapper.insert(action);
			}
			
			menu = new Menu();
			menu.setCreate_time(new Date());
			menu.setParent_id(action.getId());
			menu.setCreator("system");
			menu.setIs_show(channel.getShow());
			menu.setName(channel.getMenuName());
			menu.setMemo(channel.getMemo());
			menu.setSort(channel.getSort());
			menu.setAction(channel.getAction());
			menu.setHref(channel.getHref());
			menu.setMethod(channel.getMethod());
			menuMapper.insert(menu);
		}
			
		
	}

	/**
	 * 权限校验
	 * 
	 * @param channel
	 */
	public void checkPermission(Channel channel) throws ResultException, Exception {
		
		// TODO 自学习机制	name / desc	/ controller	/ method
		this.autoStudy(channel);
		
		// TODO 检测access_token是否有效
		String access_token = channel.getAccessToken();
		
		String json = memCacheService.getUserInfoAndPermission(access_token);
		// 缓存无数据,则先查库校验是否存在access_token
		ObjectMapper mapper = new ObjectMapper();
		if(null == json){
			// 校验access_token
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("access_token", access_token);
			User user = userMapper.selectByMap(map);
			if(null == user) throw new ResultException(Code.PM_NONE);
			// TODO 查询用户对应的权限列表
			List<String> menus = menuMapper.selectMenusByRoleId(user.getRole_id());
			if(menus.size() < 1) throw new ResultException(Code.PM_NONE);
			this.flushPermissions(access_token, user, menus);
			if(!menus.contains(channel.getHref())) throw new ResultException(Code.PM_NONE);
		}else{
			JsonNode node = mapper.readValue(json, JsonNode.class);
			String menus = node.get("menus").toString();
			if(menus.indexOf(channel.getHref()) < 1) throw new ResultException(Code.PM_NONE);
		}
	}

	/**
	 * 刷新权限集合
	 * 
	 * @param access_token
	 * @param user
	 * @param menus
	 * @throws JsonProcessingException
	 */
	public void flushPermissions(String access_token,User user,List<String> menus)
			throws JsonProcessingException {
		GlobalUser cacheUser = new GlobalUser();
		cacheUser.setMenus(menus);
		cacheUser.setNickname(user.getNickname());
		cacheUser.setRole_id(user.getRole_id());
		cacheUser.setUsername(user.getUsername());
		ObjectMapper mapper = new ObjectMapper();
		String memcachJson = mapper.writeValueAsString(cacheUser);
		memCacheService.setUserInfoAndPermission(access_token, memcachJson);
	}
	
	public GlobalUser getCacheUser(String access_token) throws Exception{
		
		String session = memCacheService.getUserInfoAndPermission(access_token);
		ObjectMapper mapper = new ObjectMapper();
		GlobalUser user = mapper.readValue(session, GlobalUser.class);
		return user;
		
	}
	
	
	
}
