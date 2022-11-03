package com.heekwoncompany.mvcboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heekwoncompany.mvcboard.command.BCommand;
import com.heekwoncompany.mvcboard.command.BContentviewCommand;
import com.heekwoncompany.mvcboard.command.BDeleteCommand;
import com.heekwoncompany.mvcboard.command.BListCommand;
import com.heekwoncompany.mvcboard.command.BModifyCommand;
import com.heekwoncompany.mvcboard.command.BReplyCommand;
import com.heekwoncompany.mvcboard.command.BWriteCommand;

@Controller
public class BoardController {
	
	BCommand command = null;
	@RequestMapping(value="/")
	public String goList() {
		
		return "redirect:list";
	}
	
	@RequestMapping(value="list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.excute(model);
		
		return "list";
	}
	
	
	@RequestMapping(value="write_form")
	public String write_form(Model model) {
		
		
		return "write_form";
	}
	
	@RequestMapping(value="write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.excute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BContentviewCommand();
		command.excute(model);
		
		return "content_view";
	}
	
	
	@RequestMapping(value="modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BContentviewCommand();
		command.excute(model);
		
		
		return "modify_view";
	}
	
	@RequestMapping(value="modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.excute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.excute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="reply_write")
	public String reply_write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BContentviewCommand();
		command.excute(model);
		
		
		return "reply_write";
	}
	
	@RequestMapping(value="reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.excute(model);
		
		
		return "redirect:list";
	}
	
}
