package top.jjust.container;

import java.awt.List;
import java.io.InputStream;
import java.util.ArrayList;

import top.jjust.common.FileControl;
import top.jjust.common.StaticVariable;

/**
 * �����࣬������̨��������ʾ���з����Ա�ȱ���
 * @author TF
 *
 */
public class Room {

/*	private int RoomState = StaticVariable.ROOM_CLOSE;//����״̬
	private String admin;//�������Ա���ϴ��ļ�����ʼ/�ر���ʾ���رշ���
	private ShowStage stage;//������ʾ
	private String recDir = "";*/
	
	public Room(String roomID){
		/*RoomState = StaticVariable.ROOM_OPEN;
		admin = adminID;	*/
		FileControl.newFileDir(StaticVariable.PPTPicRootPath+roomID);
		
	}
	/**
	 * ��admin���뷿�����Ҫ��ʾʱ�����ϴ��ļ�
	 * 
	 */
	public void upLoadFile(InputStream is,String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){
			//��ֵrecdir
		}
	};
	/**
	 * �ϴ��ļ�������������̨��չʾ�ļ�(ppt�ļ�����ͼƬ)
	 * @return
	 */
	public Object startShowFile(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){
			//recdir����������ShowStage
		}
		return null;
	};
	/**
	 * ������̨�����չʾ
	 */
	public void endShowFile(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){

		}

	}
/*	*//**
	 * ���ط���״̬
	 * @return
	 *//*
	public int getRoomState(){
		return RoomState;
	}*/
	/**
	 * �رշ���
	 */
	public void destoryRoom(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){

		}

	}

}
