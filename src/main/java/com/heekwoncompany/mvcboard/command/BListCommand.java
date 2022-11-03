package com.heekwoncompany.mvcboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.heekwoncompany.mvcboard.dao.BoardDao;
import com.heekwoncompany.mvcboard.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void excute(Model model) {
		
		BoardDao dao = new BoardDao();
		
		ArrayList<BoardDto> dtos = dao.list();
		//DB 에서 가져온 글 목록을 모두가지고 있는 ArrayList 자료구조의 dtos를 저장
		
		int count = dao.board_count();
		
		model.addAttribute("boardCount", count);
		model.addAttribute("list", dtos); //글 목록 ArrayList 모델객체안에 저장
	}
}
