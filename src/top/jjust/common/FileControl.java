package top.jjust.common;

import java.io.File;
import java.io.IOException;

public class FileControl {
	/**
	 * 新建文件夹
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
	 * 显示是否存在此文件夹
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
