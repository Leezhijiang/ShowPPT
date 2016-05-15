package top.jjust.changer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.common.StaticVariable;

public class Office2Rec {
	public static String convertOffice(HttpServletRequest request){
			String url = "";
			String type = "";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> fileItems = null;
			fileItems = upload.parseRequest(request);
			String roomID="";
			String roomPD="";
			RoomBean room = null;
			for(FileItem item:fileItems){
				if(item.isFormField()){
					if("room_id".equals(item.getFieldName())){
						roomID = item.getString();
						if(roomID.contains("Room ID = ")){
							roomID = roomID.replace("Room ID = ",""); 
						}
					}
					if("room_pd".equals(item.getFieldName())){
						roomPD = item.getString();
						if(roomPD.contains("Password = ")){
							roomPD = roomPD.replace("Password = ","");
						}
					}
				}
				else{
					//当前是一个文件
					//防止入侵
					if(roomID==""&&roomID==null&&CacheManager.checkLogin(roomID,roomPD)){

					}else{
						//处理文件以及生成相应bean
						room = (RoomBean)CacheManager.getBean(roomID);
						if(room!=null){
							String name = item.getName();
							if(name.endsWith("ppt")){
								url="Rooms/"+roomID+"/"+(room).getNowPPT()+".png";
								type = "ppt";
								room.setNowPPT(0);
								room.setOfficeType(StaticVariable.SHOWPPT);
								PPTShower2003older.doPPTtoImage(item.getInputStream(),roomID);
							}
							if(name.endsWith("pptx")){
								url="Rooms/"+roomID+"/"+(room).getNowPPT()+".png";
								type = "pptx";
								room.setNowPPT(0);
								room.setOfficeType(StaticVariable.SHOWPPT);
								PPTShower2007.doPPTtoImage2007(item.getInputStream(),roomID);

							}
							if(name.endsWith("doc")){
								type = "doc";
								url = "/Rooms/"+roomID+"/my.html";
								room.setOfficeType(StaticVariable.SHOWWORD);
								DocShower.convert2Html(item.getInputStream(),roomID);
							}
							if(name.endsWith("docx")){
								type = "docx";
								url = "/Rooms/"+roomID+"/my.html";
								room.setOfficeType(StaticVariable.SHOWWORD);
								DocxShower.convert2Html(item.getInputStream(),roomID);
							}
							if(name.endsWith("xls")||name.endsWith("xlsx")){
								type = "xls";
								url = "/Rooms/"+roomID+"/my.html";
								room.setOfficeType(StaticVariable.SHOWEXCEL);
								XlsShower.convert2Html(item.getInputStream(),roomID);
							}
						} 

					}

				}

			}
			return "{\"url\":\""+url+"\",\"type\":\""+type+"\"}";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}
}
