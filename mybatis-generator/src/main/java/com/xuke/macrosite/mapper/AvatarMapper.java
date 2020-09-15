package com.xuke.macrosite.mapper;

import com.xuke.macrosite.entity.Avatar;
import com.xuke.macrosite.entity.AvatarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AvatarMapper {
    long countByExample(AvatarExample example);

    int deleteByExample(AvatarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Avatar record);

    int insertSelective(Avatar record);

    List<Avatar> selectByExample(AvatarExample example);

    Avatar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Avatar record, @Param("example") AvatarExample example);

    int updateByExample(@Param("record") Avatar record, @Param("example") AvatarExample example);

    int updateByPrimaryKeySelective(Avatar record);

    int updateByPrimaryKey(Avatar record);
}