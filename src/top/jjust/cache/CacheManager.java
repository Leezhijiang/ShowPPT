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
			//����û�������ɾ�����û���
			if(usr_Room.checkTime()){
				usr_Room.destory();
				delete.add(usr_Room);
			}
		}
		contain.removeAll(delete);
	}
	/**
	 * ����������������
	 * @return
	 */
	public static ArrayList<Bean> getData(){
		return contain;
	}
	public static boolean checkLogin(String roomID,String pd){
		for(Bean bean: contain){
			RoomBean ur = (RoomBean)bean;
			if(ur.getRoomID().equals(roomID)){
				//��������һ�£��ж������Ƿ�һ��
				return ur.checkPD(pd);
			}
		}
		return false;
	}
	/**
	 * ������Ҫ�ҵ�bean
	 * @return
	 */
	public static Bean getBean(String ID){
		for(Bean bean: contain){
			RoomBean ur = (RoomBean)bean;
			if(ur.getRoomID().equals(ID)){
				//��������һ�£����ط���
				return ur;
			}
		}
		return null;
	}
}
