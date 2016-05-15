package top.jjust.bean;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import top.jiang.utils.FileUtils;
import top.jjust.common.StaticVariable;

public class RoomBean extends Bean{
	private String roomID = "";
	private String pd = "";
	private int nowPPT = 0;//�����ڵ�ǰչʾpptλ��
	private int PPTCount = 0;//������PPT������\
	private float nowPX = 0;
	private int OfficeType = StaticVariable.SHOWNONE;
	public int getOfficeType() {
		return OfficeType;
	}
	public void setOfficeType(int officeType) {
		OfficeType = officeType;
	}
	//�½�bean
	public RoomBean(String roomID,String pd){
		this.roomID = roomID;
		this.pd = pd;
		Date date = new Date();
		lastDate = date.getTime();
	}
	/**
	 * ����Usr_Room�Ƿ���� true-����
	 */
	public boolean checkTime(){
		Date date = new Date();
		if((date.getTime()-lastDate)>StaticVariable.time){
			return true;
		}
		return false;
	}
	//��destroyʱ����
	public void destory(){
		//ɾ����Ӧ�ļ�
		FileUtils.deleteDirectory(StaticVariable.PPTPicRootPath+roomID);
		this.lastDate = 0;
		this.roomID = null;
		this.pd = null;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "����ID"+roomID+",����"+pd+",ʱ��"+lastDate;
	}
	/**
	 * ��֤�����Ƿ�һ��
	 * @param pd
	 * @return
	 */
	public boolean checkPD(String pd){
		if(this.pd.endsWith(pd)){
			return true;
		}
		return false;
	}
	/**
	 * ��������
	 */
	public void setPPTCount(int count){
		this.PPTCount = count;
	}
	/**
	 * ���õ�ǰλ��
	 * @param position
	 */
	public void setNowPPT(int position){
		this.nowPPT = position;
	}
	public int getNowPPT() {
		return nowPPT;
	}
	public int getPPTCount() {
		return PPTCount;
	}
	public String getPD(){
		return pd;
	}
	public String getRoomID(){
		return roomID;
	}
	public float getNowPX() {
		return nowPX;
	}
	public void setNowPX(float nowPX) {
		this.nowPX = nowPX;
	}
}
