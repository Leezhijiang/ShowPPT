package top.jjust.common;

import java.io.File;
import java.io.IOException;

public class FileControl {
	/**
	 * �½��ļ���
	 * @param path
	 */
	public static void newFileDir(String path){
		File file=new File(path/*"C:\\Users\\QPING\\Desktop\\JavaScript\\2.htm"*/);    
		if(!file.exists())    
		{    
			 
				file.mkdir();  
			
		}  
	}
	/**
	 * ��ʾ�Ƿ���ڴ��ļ���
	 * @param path
	 * @return
	 */
	public static boolean hasFileDir(String path){
		File file=new File(path/*"C:\\Users\\QPING\\Desktop\\JavaScript\\2.htm"*/);    
		if(!file.exists() && !file .isDirectory())    
		{    
		return false;    
			 
		}  else
		return true;
	}
}
