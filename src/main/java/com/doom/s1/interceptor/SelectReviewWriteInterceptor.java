package com.doom.s1.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doom.s1.member.MemberVO;
import com.doom.s1.storeList.StoreListService;
import com.doom.s1.storeList.StoreListVO;

@Component
public class SelectReviewWriteInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private StoreListService storeListService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check = true;
		long st_key = Long.parseLong(request.getParameter("st_key"));
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");

		List<StoreListVO> ar = storeListService.select_id(st_key);
		System.out.println(memberVO.getId());
		System.out.println("id : "+ (String)ar.get(0).getId());
		
		
		if(memberVO != null && !memberVO.getId().equals("admin")) {
			if (ar.get(0).getId().equals(memberVO.getId())) {
				check = false;
				request.setAttribute("result", "식당등록 id는 리뷰를 쓰실수 없습니다.");
				request.setAttribute("path", "../");
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
				view.forward(request, response);
			}else {
				check = true;
			}
			
		}else {
			request.setAttribute("result", "로그인후 이용해 주세요");
			request.setAttribute("path","../");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
		}
		

		return check;
	}
}
