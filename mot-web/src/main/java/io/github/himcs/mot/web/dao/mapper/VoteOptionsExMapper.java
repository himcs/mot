package io.github.himcs.mot.web.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteOptionsExMapper {

    int updateSum(@Param("ids") List<Integer> ids);
}
