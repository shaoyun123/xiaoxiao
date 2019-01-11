package com.web.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UpdataImgerUtil {
	
	/**
	 * 将图片转换为base64
	 * @param basePath 存储文件位置 传入需有结束符
	 * @param filePathName 存入文件名称
	 * @param type 类型 jpg 等
	 * @return 返回base64字符串
	 */
	public static String GetImageStr(String basePath,String filePathName,String type) {
		if(!"\\".equals((basePath.substring(0, basePath.length()-1)))){
			basePath = basePath+File.separator;
		}
		File file = new File(basePath+filePathName);
		//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		String imgFile = ys(basePath,filePathName,type);//待处理的图片  
		file = new File(imgFile);
		if(!file.exists()){
			return "";
		}
		InputStream in = null;  
		byte[] data = null;  
		//读取图片字节数组  
		try {  
			in = new FileInputStream(imgFile);          
			data = new byte[in.available()];  
			in.read(data);  
			in.close();  
			in = null;
		}   
		catch (IOException e){  
			e.printStackTrace();  
		} finally{
			if(in != null){
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	        //对字节数组Base64编码  
		BASE64Encoder encoder = new BASE64Encoder();  
		return encoder.encode(data);//返回Base64编码过的字节数组字符串  
	}
	 
	/**
	 * base64字符串转化成图片  
	 * @param imgStr base64字符串
	 * @param pathName 文件路径 全路径
	 * @return 返回是否成功
	 */
	public static boolean GenerateImage(String imgStr,String pathName )  {
    	//对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try{  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i){  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = pathName;//新生成的图片  
            System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close(); 
            return true;
        }   
        catch (Exception e){  
        	return false;
        }  
    } 
	/**
	 * 压缩图片
	 * @param basePath 存储路径
	 * @param filePathName 文件名称
	 * @param type 类型 jpg等
	 * @return 返回新压缩的文件路径（全路径）
	 */
	public static String ys(String basePath,String filePathName,String type){
    	InputStream is = null;
    	OutputStream os = null;
    	ImageOutputStream ios = null;
    	ImageWriter writer = null;
    	File compressedImageFile = null;
    	String newPath = "";
    	try{
    		if(!"\\".equals(basePath.substring(basePath.length() - 1, basePath.length()))){
        		basePath = basePath+File.separator;
        	}
        	File file = new File(basePath+filePathName);
        	// 如果图片不存在生成默认图片
        	if(!file.exists()){
        		newPath = basePath+"default.jpg";
        		type = "jpg";
        	}else{
        		newPath = basePath+filePathName;
        	}
        	//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
            String imgFile = newPath;//待处理的图片  
            file = new File(imgFile);
        	compressedImageFile = new File(basePath+"myimage_compressed."+type);
        	is = new FileInputStream(file);
        	os = new FileOutputStream(compressedImageFile);
        	float quality = 0.5f;
        	BufferedImage image = ImageIO.read(is);
        	Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(type);
        	if (!writers.hasNext())
        		throw new IllegalStateException("No writers found");
        	writer = writers.next();
        	ios = ImageIO.createImageOutputStream(os);
        	writer.setOutput(ios);
        	ImageWriteParam param = writer.getDefaultWriteParam();
        	param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        	param.setCompressionQuality(quality);
        	writer.write(null, new IIOImage(image, null, null), param);
        	// close all streams
    	}catch (Exception e) {
    		if(compressedImageFile != null){
    			compressedImageFile.delete();
    		}
    		//e.printStackTrace();
    		return newPath;
			
		}finally{
			try{
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();			
				}
				if(ios != null){
					ios.close();
				}
				if(writer != null){
					writer.dispose();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
    	
    	return basePath+"myimage_compressed."+type;
    }
}
