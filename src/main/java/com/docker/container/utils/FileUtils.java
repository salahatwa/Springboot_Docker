package com.docker.container.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

/**
 * @author atwa
 * Jun 27, 2018
 */
public class FileUtils {
	

	public static void saveKey(String fileName,BigInteger mod,BigInteger exp)
	{
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		try {
			fos=new FileOutputStream(fileName);
			oos=new ObjectOutputStream(new BufferedOutputStream(fos));
			oos.writeObject(mod);
			oos.writeObject(exp);
			
			oos.close();
			fos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
