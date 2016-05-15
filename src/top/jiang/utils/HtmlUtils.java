package top.jiang.utils;

public class HtmlUtils {
	public static String getHtml(String body){
		String pre = "<!DOCTYPE   HTML   PUBLIC   \"-//W3C//DTD   HTML   4.01   Transitional//EN \"> <html><head><meta   http-equiv= \"Content-Type \"   content= \"text/html;   charset=utf-8 \">" +
				"</head> <body><div style=\"width:80%\" id=\"textCation\">";
		String after ="</div></body> </html> ";
		return pre+body+after;
	}
}
