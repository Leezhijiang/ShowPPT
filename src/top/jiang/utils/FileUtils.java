package top.jiang.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;


public class FileUtils {
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
		boolean flag = true;
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        return true;
	    }  
	    return false;  
	}  
	public static String getPath() {
		/*ClassLoader lo=  Number.class.getClass().getClassLoader();
		URL path = lo.getResource("../../a.txt");
		return path.getPath();*/
		return System.getProperty("user.dir").replace("bin", "webapps\\ShowPPT");
	}  
	public static void writeFile(String content, String path) {  
		FileOutputStream fos = null;  
		BufferedWriter bw = null;  
		try {  
			File file = new File(path);  
			fos = new FileOutputStream(file);  
			bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));  
			bw.write(content);  
		} catch (FileNotFoundException fnfe) {  
			fnfe.printStackTrace();  
		} catch (IOException ioe) {  
			ioe.printStackTrace();  
		} finally {  
			try {  
				if (bw != null)  
					bw.close();  
				if (fos != null)  
					fos.close();  
			} catch (IOException ie) {  
			}  
		}  
	}  
}
