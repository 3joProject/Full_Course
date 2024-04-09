package com.fullcourse.triprecord.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.triprecord.TripRecordVO;

@Mapper
public interface TripRecordMapper {

	List<TripRecordVO> selectAll(Map<String, Integer> map);

	int getTotalRows();

	int insertOK(TripRecordVO vo);

}
