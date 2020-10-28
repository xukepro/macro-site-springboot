package com.xuke.macrosite.mapper;

import com.xuke.macrosite.entity.UserFriendAsk;
import com.xuke.macrosite.entity.UserFriendAskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFriendAskMapper {
    long countByExample(UserFriendAskExample example);

    int deleteByExample(UserFriendAskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFriendAsk record);

    int insertSelective(UserFriendAsk record);

    List<UserFriendAsk> selectByExample(UserFriendAskExample example);

    UserFriendAsk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserFriendAsk record, @Param("example") UserFriendAskExample example);

    int updateByExample(@Param("record") UserFriendAsk record, @Param("example") UserFriendAskExample example);

    int updateByPrimaryKeySelective(UserFriendAsk record);

    int updateByPrimaryKey(UserFriendAsk record);
}