package com.heekwoncompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.heekwoncompany.mvcboard.dao.BoardDao;

public class BReplyCommand implements BCommand {

	@Override
	public void excute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		
		BoardDao dao = new BoardDao();
		dao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);

	}

}
