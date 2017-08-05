package com.david.dal.dao;

import com.david.dal.model.MemberInfo;
import com.david.dal.model.MemberInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberInfoMapper extends Mapper<MemberInfo> {
    int countByExample(MemberInfoExample example);

    int deleteByExample(MemberInfoExample example);

    List<MemberInfo> selectByExample(MemberInfoExample example);

    int updateByExampleSelective(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByExample(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);
}