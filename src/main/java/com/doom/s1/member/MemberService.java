package com.doom.s1.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doom.s1.util.Pager;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int memberJoin(MemberVO memberVO, HttpSession session) throws Exception{
		return memberDAO.memberJoin(memberVO);
	}
	
	public MemberVO memberLogin_HOME(MemberVO memberVO) throws Exception{
		return memberDAO.memberLogin_HOME(memberVO);
	}
	
	public int memberDelete(MemberVO memberVO) throws Exception{
		return memberDAO.memberDelete(memberVO);
	}
	
	public int memberUpdate(MemberVO memberVO) throws Exception{
		return memberDAO.memberUpdate(memberVO);
	}
	
	public List<MemberVO> memberList(Pager memberPager) throws Exception{
		memberPager.makeRow();
		long totalCount = memberDAO.memberCount(memberPager);
		memberPager.makePage(totalCount);
		return memberDAO.memberList(memberPager);
	}
	
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return memberDAO.memberIdCheck(memberVO);
	}
	
	public MemberVO memberNaverCheck(MemberVO memberVO) throws Exception {
		return memberDAO.memberNaverCheck(memberVO);
	}
	
	public MemberVO memberIdFind(MemberVO memberVO) throws Exception{
		return memberDAO.memberIdFind(memberVO);
	}

}