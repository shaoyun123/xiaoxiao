package com.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetPic {
	
	
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream(file);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
		    byte[] b = new byte[1000];
		    int n;
		    while ((n = fis.read(b)) != -1) {
		    	bos.write(b, 0, n);
		    	}
		    fis.close();
		    bos.close();
		    buffer = bos.toByteArray();
		    }
		catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } 
		catch (IOException e) {
		        e.printStackTrace();
		    }
		return buffer;
	}
}
