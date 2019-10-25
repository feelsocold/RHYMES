package com.rhymes.app.store.dao;

import java.util.List;
import java.util.Map;

import com.rhymes.app.store.model.DetailParam;
import com.rhymes.app.store.model.PreviewDto;

public interface PreviewDao {

	//Preview list가져오기
	public List<PreviewDto> getPreviewList(DetailParam param);
	
	//Preview count
	public int getPreviewCount(DetailParam param);
	
	//like 여부 가져오기
	boolean getlikes(Map<String, Object> map);
	
	//like 추가
	boolean addlikes(Map<String,Object> map);
	
	//like 삭제
	boolean deletelikes(Map<String, Object> map);
	
	
}
