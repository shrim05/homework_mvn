package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerDeleteController {
	IBuyerService service = BuyerServiceImpl.getInstance();
	
	@URIMapping(value="/BuyerDeleteController", method=HttpMethod.POST)
	public String deleteBuyer(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("plain/text; charset=UTF-8");
		Map formData = req.getParameterMap();
		BuyerVO bv = new BuyerVO(); 
		try {
			BeanUtils.populate(bv, formData);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		try(PrintWriter out = resp.getWriter();){
			ServiceResult result = service.removeBuyer(bv);
			switch (result) {
			case OK:
				out.println("삭제성공");
				break;
			case FAILED:
				out.println("삭제실패");
				break;
			}
		}	
		return null;
	}
}
