package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> list(int page){
		// pager 알고리즘
		PageVo vo = new PageVo();
		
		vo.setTotalCount(boardDao.getTotalCount());
		int totalPage = vo.getTotalPage(vo.getTotalCount());
		vo.setStartPage(vo.getPage());
		vo.setEndPage(10);
		vo.setPage(page);
		System.out.println(vo.getStartPage() + " " + vo.getEndPage());
		
		List<BoardVo> list = boardDao.getPageList(vo.getPage());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("startPage", vo.getStartPage());
		map.put("endPage", vo.getEndPage());
		map.put("page", vo.getPage());
		map.put("totalCount", vo.getTotalCount());
	
		return map;
	}
	
	public BoardVo getView(long no) {
		BoardVo vo = boardDao.get(no);
		
		return vo;
	}
	
	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo.getTitle(), boardVo.getContent(), boardVo.getUserNo());
	}
}
