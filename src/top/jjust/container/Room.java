package top.jjust.container;

import java.awt.List;
import java.io.InputStream;
import java.util.ArrayList;

import top.jjust.common.FileControl;
import top.jjust.common.StaticVariable;

/**
 * 房间类，含有舞台类用来演示，有房间成员等变量
 * @author TF
 *
 */
public class Room {

/*	private int RoomState = StaticVariable.ROOM_CLOSE;//房间状态
	private String admin;//房间管理员，上传文件，开始/关闭演示，关闭房间
	private ShowStage stage;//房间演示
	private String recDir = "";*/
	
	public Room(String roomID){
		/*RoomState = StaticVariable.ROOM_OPEN;
		admin = adminID;	*/
		FileControl.newFileDir(StaticVariable.PPTPicRootPath+roomID);
		
	}
	/**
	 * 当admin进入房间后，需要演示时当先上传文件
	 * 
	 */
	public void upLoadFile(InputStream is,String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){
			//赋值recdir
		}
	};
	/**
	 * 上传文件结束，调用舞台类展示文件(ppt文件返回图片)
	 * @return
	 */
	public Object startShowFile(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){
			//recdir做参数调用ShowStage
		}
		return null;
	};
	/**
	 * 调用舞台类结束展示
	 */
	public void endShowFile(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){

		}

	}
/*	*//**
	 * 返回房间状态
	 * @return
	 *//*
	public int getRoomState(){
		return RoomState;
	}*/
	/**
	 * 关闭房间
	 */
	public void destoryRoom(String ID){
		if(FileControl.hasFileDir(StaticVariable.dirPath+ID)){

		}

	}

}
