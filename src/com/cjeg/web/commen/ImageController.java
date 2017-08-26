package com.cjeg.web.commen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.font.FontDesignMetrics;

import com.cjeg.web.consts.Consts;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年3月11日 上午10:25:19
 *
 */
@Controller
@RequestMapping("/user/image")
public class ImageController {
	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/code")
	public void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		OutputStream output=response.getOutputStream();
		String code=writeImage(output);
		//图片验证码
		//session.setAttribute(Consts.IMAGE_SESSION, code);
		output.close();
	}
	private String writeImage(OutputStream output) throws IOException{
		Font font=new Font("",Font.BOLD, 60);
		FontMetrics fm=FontDesignMetrics.getMetrics(font);
		BufferedImage bufferedImage=new BufferedImage(200, 100, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2=(Graphics2D)bufferedImage.getGraphics();
		g2.setFont(font);
		String str=RandomStringUtils.random(4, ""/*Consts.RANDOM_CODE*/);
		g2.setColor(Color.BLACK);
		g2.drawString(str, 30,fm.getHeight());
		ImageIO.write(bufferedImage, "png", output);
		return str;
	}
}
