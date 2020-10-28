package com.xuke.macrosite.mapper;

import com.xuke.macrosite.entity.GroupAsk;
import com.xuke.macrosite.entity.GroupAskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupAskMapper {
    long countByExample(GroupAskExample example);

    int deleteByExample(GroupAskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupAsk record);

    int insertSelective(GroupAsk record);

    List<GroupAsk> selectByExample(GroupAskExample example);

    GroupAsk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupAsk record, @Param("example") GroupAskExample example);

    int updateByExample(@Param("record") GroupAsk record, @Param("example") GroupAskExample example);

    int updateByPrimaryKeySelective(GroupAsk record);

    int updateByPrimaryKey(GroupAsk record);
}