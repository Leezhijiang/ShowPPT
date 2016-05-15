package top.jjust.cache;

import java.util.ArrayList;

import top.jjust.bean.Bean;
import top.jjust.bean.RoomBean;

public class CacheManager {
	private static ArrayList<Bean> contain = new ArrayList<Bean>();
	private static ArrayList<Bean> delete = new ArrayList<Bean>();
	
	public static void insert(RoomBean ur){
		contain.add(ur);
		for(Bean usr_Room : contain){
			//如果用户过期则删除该用户。
			if(usr_Room.checkTime()){
				usr_Room.destory();
				delete.add(usr_Room);
			}
		}
		contain.removeAll(delete);
	}
	/**
	 * 返回整个缓存内容
	 * @return
	 */
	public static ArrayList<Bean> getData(){
		return contain;
	}
	public static boolean checkLogin(String roomID,String pd){
		for(Bean bean: contain){
			RoomBean ur = (RoomBean)bean;
			if(ur.getRoomID().equals(roomID)){
				//如果房间号一致，判断密码是否一致
				return ur.checkPD(pd);
			}
		}
		return false;
	}
	/**
	 * 返回需要找的bean
	 * @return
	 */
	public static Bean getBean(String ID){
		for(Bean bean: contain){
			RoomBean ur = (RoomBean)bean;
			if(ur.getRoomID().equals(ID)){
				//如果房间号一致，返回房间
				return ur;
			}
		}
		return null;
	}
}
