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
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ� 
	 * @param   sPath ��ɾ��Ŀ¼���ļ�·�� 
	 * @return  Ŀ¼ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
		boolean flag = true;
	    //���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    //ɾ���ļ����µ������ļ�(������Ŀ¼)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //ɾ�����ļ�  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //ɾ����Ŀ¼  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //ɾ����ǰĿ¼  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	/** 
	 * ɾ�������ļ� 
	 * @param   sPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    File file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
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
