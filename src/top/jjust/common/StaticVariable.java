package top.jjust.common;

import top.jiang.utils.FileUtils;

public class StaticVariable {
	
	//房间未开启状态，不接受新听众
	public static final int ROOM_CLOSE = 0;
	//房间开启状态，可以接受新听众；
	public static final int ROOM_OPEN = 1;
	//ppt文件存放位置(实验用)
	public static final String dirPath = "k:\\showpppt\\";
	//缓存存储时间(1h)
	public static final long time = 30*60*1000;
	//上传文件的保存目录(废弃)
	public static final String storeFilePath ="l:\\";
	//web应用的根目录Tomcat
	public static final String ROOTPATH = System.getProperty("user.dir").replace("bin", "webapps/ShowPPT");/*"G:\\Tomcat\\tomcat6\\webapps\\ShowPPT";*/
	//上传文件转换ppt保存文件根目录Tomcat
	public static final String PPTPicRootPath =ROOTPATH+"/Rooms/";
	//网站域名
	public static final String YUMING ="www.jjust.top";
	//当前没有文件演示中
	public static final int SHOWNONE = 0;
	//当前PPT文件演示中
	public static final int SHOWPPT = 1;
	//当前没有文件演示中
	public static final int SHOWWORD = 2;
	//当前没有文件演示中
	public static final int SHOWEXCEL = 3;
	//服务器长轮询等待时间
	public static final int SLEEP = 1000;
}
