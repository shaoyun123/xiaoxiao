package com.web.controller.web.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.util.GetPic;

@Controller
public class GetPicController {

	@RequestMapping(value = "getPic")
	@ResponseBody
	public void getPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = request.getParameter("id");
		String touxiang = request.getParameter("touxiang");
		// 设置header
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		OutputStream os = response.getOutputStream();
		byte[] btImg = null;
		// String s = request.getSession().getServletContext().getRealPath("/")
		// +"upload/" + filename;
		if (filename != null && !"".equals(filename)) {
			btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/") + "upload/" + filename);
			if (btImg != null) {
				os.write(btImg);
				os.flush();
			} else {
				if("1".equals(touxiang)){
					btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/")
							+ "static/img/6666.jpg");
					os.write(btImg);
					os.flush();
				}else{
					btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/")
							+ "static/img/836343800200187442.jpg");
					os.write(btImg);
					os.flush();
				}
			}
		}else{
			if("1".equals(touxiang)){
				btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/")
						+ "static/img/6666.jpg");
				os.write(btImg);
				os.flush();
			}else{
				btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/")
						+ "static/img/836343800200187442.jpg");
				os.write(btImg);
				os.flush();
			}
		}
	}

	@RequestMapping(value = "getcqPic")
	@ResponseBody
	public void getChaQinPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = request.getParameter("id");
		String anpaiid = request.getParameter("anpaiid");
		// 设置header
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		OutputStream os = response.getOutputStream();
		byte[] btImg = null;
		btImg = GetPic.getBytes(request.getSession().getServletContext().getRealPath("/") +"upload" + File.separator + "cqap" + File.separator + anpaiid + File.separator +  filename);
		if (btImg != null) {
			os.write(btImg);
			os.flush();
		}
	}

	@RequestMapping(value = "app_getPic")
	@ResponseBody
	public void app_getPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = request.getParameter("id");
		String l = request.getParameter("l");
		String aid = request.getParameter("aid");
		// 设置header
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		OutputStream os = response.getOutputStream();
		byte[] btImg = null;
		if(!"1".equals(l)){
			btImg = GetPic.getBytes(
					request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + filename);
		}else{
			btImg = GetPic.getBytes(
					request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + "cqap" + File.separator + aid + File.separator + filename);
		}
		if (btImg != null) {
			os.write(btImg);
			os.flush();
		}
	}
}
