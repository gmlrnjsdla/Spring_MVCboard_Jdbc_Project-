package com.heekwoncompany.mvcboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.heekwoncompany.mvcboard.dto.BoardDto;
import com.heekwoncompany.mvcboard.util.Constant;

public class BoardDao {

	DataSource dataSource;
	JdbcTemplate template;

	public BoardDao() {
		super();
		
		this.template = Constant.template;
		

	}

	public ArrayList<BoardDto> list() { // 게시판 전체 글 목록을 반환하는 메소드
		
		//JDBCtemplate 이용
		String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC, bstep ASC";
		ArrayList<BoardDto> dtos = (ArrayList<BoardDto>) template.query(sql, new BeanPropertyRowMapper(BoardDto.class));
		
		return dtos;
		
		
	}

	
	public void write(final String bname,final String btitle,final String bcontent) {
		
		//JDBCtemplate 이용
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,"
						+ "bgroup,bstep,bindent) "
						+ "VALUES (mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				
				return pstmt;
			}
		});
		

	}
	
	public BoardDto content_view(String cid) {
		upHit(cid);
		
		String sql = "SELECT * FROM mvc_board WHERE bid=" + cid;
		
		BoardDto dto = template.queryForObject(sql, new BeanPropertyRowMapper(BoardDto.class));
		
		return dto;
		
	}
	
	public void modify(final String bname,final String btitle, final String bcontent, final String bid) {
		
		String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				pstmt.setString(4, bid);
				
			}
		});
			
	}
	
	public void delete(final String bid) {
		
		String sql = "DELETE FROM mvc_board WHERE bid=?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, bid);
			}
		});
		

	}
	
	public void upHit(final String bid) {
		
		String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, bid);
			}
		});
		
		

	}
	
	public int board_count() {
		
		String sql = "SELECT * FROM mvc_board";
		ArrayList<BoardDto> dtos = (ArrayList<BoardDto>) template.query(sql, new BeanPropertyRowMapper(BoardDto.class));
		int count = dtos.size();
		
		return count;
		

	}
	
	public void reply(final String bid, final String bname, final String btitle, final String bcontent, final String bgroup, final String bstep, final String bindent) {
		reply_sort(bgroup, bstep);
		
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,"
						+ "bgroup,bstep,bindent) "
						+ "VALUES (mvc_board_seq.nextval,?,?,?,0,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				pstmt.setString(4, bgroup);
				pstmt.setInt(5, Integer.parseInt(bstep)+1);
				pstmt.setInt(6, Integer.parseInt(bindent)+1);
				
				return pstmt;
			}
		});
		
		

	}
	
	public void reply_sort(final String bgroup, final String bstep) {
		
		String sql = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup=? and bstep>?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, bgroup);
				pstmt.setString(2, bstep);
			}
		});
		
		
	}
	
	
}
