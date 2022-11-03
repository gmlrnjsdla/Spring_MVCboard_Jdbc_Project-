package com.heekwoncompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.heekwoncompany.mvcboard.dao.BoardDao;
import com.heekwoncompany.mvcboard.dto.BoardDto;

public class BContentviewCommand implements BCommand {

	@Override
	public void excute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//model 객체를 request 객체로 매핑
		
		String bid = request.getParameter("bid");
		
		BoardDao dao = new BoardDao();
		BoardDto dto = dao.content_view(bid);
		
		
		model.addAttribute("content", dto);

	}

}
