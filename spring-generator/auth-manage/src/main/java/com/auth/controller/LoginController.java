package com.auth.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.utils.image.ImageUtils;

/**
 * 
 * 登录 Controller
 * @author lifangyu
 * @version V1.0
 */
@Controller
public class LoginController {
	
	/**
	 * 生成验证码图片
	 * @throws IOException 
	 */
	@RequestMapping("/image/createImage")
	public void createImage(HttpServletResponse response,HttpSession session) throws IOException {
		//生成验证码及图片
		Map<String, BufferedImage> map = ImageUtils.createImage();
		//取得验证码，存入session
		String code = map.keySet().iterator().next();
        session.setAttribute("imageCode", code);
		//取得图片，发送到页面
		BufferedImage image = map.get(code);
		response.setContentType("image/jpeg");
		OutputStream os = null;
		try{
		    os = response.getOutputStream();
		    ImageIO.write(image, "jpeg", os);
		} catch(IOException ie){
		    throw ie;
		}finally {
		    os.close();
        }
	}

}
