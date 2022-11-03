package com.heekwoncompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.heekwoncompany.mvcboard.dao.BoardDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void excute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//model 객체를 request 객체로 매핑
		
		String bid = request.getParameter("bid");
		
		BoardDao dao = new BoardDao();
		dao.delete(bid);

	}

}
