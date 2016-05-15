package top.jjust.bean;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import top.jiang.utils.FileUtils;
import top.jjust.common.StaticVariable;

public class RoomBean extends Bean{
	private String roomID = "";
	private String pd = "";
	private int nowPPT = 0;//房间内当前展示ppt位置
	private int PPTCount = 0;//房间内PPT总数量\
	private float nowPX = 0;
	private int OfficeType = StaticVariable.SHOWNONE;
	public int getOfficeType() {
		return OfficeType;
	}
	public void setOfficeType(int officeType) {
		OfficeType = officeType;
	}
	//新建bean
	public RoomBean(String roomID,String pd){
		this.roomID = roomID;
		this.pd = pd;
		Date date = new Date();
		lastDate = date.getTime();
	}
	/**
	 * 检验Usr_Room是否过期 true-过期
	 */
	public boolean checkTime(){
		Date date = new Date();
		if((date.getTime()-lastDate)>StaticVariable.time){
			return true;
		}
		return false;
	}
	//当destroy时调用
	public void destory(){
		//删除对应文件
		FileUtils.deleteDirectory(StaticVariable.PPTPicRootPath+roomID);
		this.lastDate = 0;
		this.roomID = null;
		this.pd = null;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "房间ID"+roomID+",密码"+pd+",时间"+lastDate;
	}
	/**
	 * 验证密码是否一致
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
	 * 设置总量
	 */
	public void setPPTCount(int count){
		this.PPTCount = count;
	}
	/**
	 * 设置当前位置
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
