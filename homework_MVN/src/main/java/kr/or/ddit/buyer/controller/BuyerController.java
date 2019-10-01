package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import utils.MarshallingUtils;

/**
 * Servlet implementation class BuyerController
 */
@CommandHandler
public class BuyerController {
	IBuyerService service;
	
	@URIMapping(value="/BuyerController", method=HttpMethod.GET)
	public String buyerListGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BuyerServiceImpl.getInstance();
		request.setCharacterEncoding("UTF-8");
		List<BuyerVO> lbv = null;
		String accept = request.getHeader("Accept");
		if(accept.contains("json")) {
			String command = request.getParameter("command");
			String json = "";
			if(StringUtils.isNotBlank(command)) {
				switch(command) {
					case "readList":
						lbv = service.retrieveBuyerList();
						json = new MarshallingUtils().marshalling(lbv);
						break;
					case "readOne":
						String buyer_id = request.getParameter("buyer_id");
						if(StringUtils.isNotBlank(buyer_id)) {
							BuyerVO bv = new BuyerVO();
							bv.setBuyer_id(buyer_id);
							BuyerVO buyer = service.retrieveBuyer(bv);
							json = new MarshallingUtils().marshalling(buyer);
						}else {
							response.sendError(HttpServletResponse.SC_BAD_REQUEST);
							return null;
						}
						break;
				}
				response.setContentType("application/json;charset=UTF-8");
				try(
						PrintWriter out = response.getWriter();
				) {
					out.print(json);
					return null;
				}
			}else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		}else {
			return "/buyerMain";
		}
			
	}
	
	@URIMapping(value="/BuyerController", method=HttpMethod.POST)
	public String buyerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		BuyerVO bv = new BuyerVO();
		ServiceResult result = null;
		try {
			BeanUtils.populate(bv, request.getParameterMap());
			result = service.createBuyer(bv);
			response.setContentType("application/json;charset=UTF-8");
			try(
					PrintWriter out = response.getWriter();
			) {
				out.print("{\"result\":\""+result.toString()+"\"}");
				return null;
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException();
		}
	}

}
