package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerUpdateController {
	
	IBuyerService service = BuyerServiceImpl.getInstance();
	@URIMapping(value="/BuyerUpdate", method=HttpMethod.POST)
	public String updateBuyer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("plain/text;charset=UTF-8"); 
		Map formData = req.getParameterMap();
		BuyerVO bv = new BuyerVO(); 
		try {
			BeanUtils.populate(bv, formData);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		Part part = req.getPart("buyer_img");
		long size = part.getSize();
		if(size>0) {
			//1.저장위치
			String saveFolderUrl = "/buyerImages";
			String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
			File saveFolder = new File(saveFolderPath);
			if(!saveFolder.exists()) saveFolder.mkdirs();
//				2.저장명
			String savename = UUID.randomUUID().toString();
			try(
					InputStream is = part.getInputStream();
					){
				FileUtils.copyInputStreamToFile(is, new File(saveFolder,savename));
			}
			bv.setBuyer_img(savename);
		}
		try(PrintWriter out = resp.getWriter();){
			ServiceResult result = service.modifyBuyer(bv);
			switch (result) {
			case OK:
				out.println("수정성공");
				break;
			case FAILED:
				out.println("수정실패");
				break;
			}
		}	
		return null;
	}
}

