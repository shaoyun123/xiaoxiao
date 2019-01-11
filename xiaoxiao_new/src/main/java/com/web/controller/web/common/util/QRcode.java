   
 
package com.web.controller.web.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;



  

public class QRcode {
	//二维码颜色  
    private static final int BLACK = 0xFF000000;  
    //二维码颜色  
    private static final int WHITE = 0xFFFFFFFF;  
  
    /**
     * 
     * @discription ZXing 方式生成二维码 
     * @author xucs       
     * @created 2017-5-10 上午10:11:29  
     * @update 2017-5-10 上午10:11:29   
     * @param text 二维码内容
     * @param width 二维码宽 
     * @param height 二维码高 
     * @param outPutPath 二维码生成保存路径 
     * @param imageType 二维码生成格式 
     * @param bool 是否去除白边
     */
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static File zxingCodeCreate(String text, int width, int height, String outPutPath, String imageType,boolean bool){  
    	Hashtable hints=new Hashtable();
    	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    	hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    	hints.put(EncodeHintType.MARGIN, 2); 
        BufferedImage image = null;
        try {  
            //1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints); 
            if(bool){
            	encode = deleteWhite(encode);//删除白边
            }
            //2、获取二维码宽高  
            int codeWidth = encode.getWidth();  
            int codeHeight = encode.getHeight();  
              
            //3、将二维码放入缓冲流  
            image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);  
            for (int i = 0; i < codeWidth; i++) {  
                for (int j = 0; j < codeHeight; j++) {  
                    //4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }  
            }  
            File outPutImage = new File(outPutPath);  
            //如果图片不存在创建图片  
            if(!outPutImage.exists())  
                outPutImage.createNewFile();  
            //5、将二维码写入图片  
            ImageIO.write(image, imageType, outPutImage);  
            return outPutImage;
        } catch (WriterException e) {  
            e.printStackTrace();  
            System.out.println("二维码生成失败");  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("生成二维码图片失败");  
        }  
        return null;
    }  
    
    /**
     * 删除二维码的白边
     * @param matrix
     * @return
     */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
      
    /**
     * 
     * @discription 二维码解析
     * @author xucs       
     * @created 2017-5-10 上午10:14:34  
     * @update 2017-5-10 上午10:14:34   
     * @param analyzePath 二维码路径 
     * @return 返回二维码内容
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static Object zxingCodeAnalyze(String analyzePath) throws Exception{  
        MultiFormatReader formatReader = new MultiFormatReader();  
        Object result = null;  
        try {  
        	// 获取二维码位置
            File file = new File(analyzePath);  
            if (!file.exists())  
            {  
                return "二维码不存在";  
            }  
            // 读取文件
            BufferedImage image = ImageIO.read(file);  
            // 获取文件源
            LuminanceSource source =   new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);    
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map hints = new HashMap();  
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
            result = formatReader.decode(binaryBitmap, hints);  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }    
        return result;  
    }  
    public static void main(String[] args) throws IOException {
    	zxingCodeCreate("http://www.5ic.net.cn/xiaoyuan/static/appdownload.jsp", 1024, 1024, "D:\\1024.jpg", "jpg",false);
    }
}
