package com.muarine.common.mapper;

import java.util.List;

import com.muarine.common.entity.MpNews;

public interface MpNewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MpNews record);

    int insertSelective(MpNews record);

    MpNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MpNews record);

    int updateByPrimaryKeyWithBLOBs(MpNews record);

    int updateByPrimaryKey(MpNews record);

	/** 
	* @Description: TODO	根据图文类型ID获取 列表
	* @param id
	* @return
	* @return List<MpNews>    
	* @throws 
	*/
	List<MpNews> selectByParentId(Long parent_id);
}