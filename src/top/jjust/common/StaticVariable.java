package top.jjust.common;

import top.jiang.utils.FileUtils;

public class StaticVariable {
	
	//����δ����״̬��������������
	public static final int ROOM_CLOSE = 0;
	//���俪��״̬�����Խ��������ڣ�
	public static final int ROOM_OPEN = 1;
	//ppt�ļ����λ��(ʵ����)
	public static final String dirPath = "k:\\showpppt\\";
	//����洢ʱ��(1h)
	public static final long time = 30*60*1000;
	//�ϴ��ļ��ı���Ŀ¼(����)
	public static final String storeFilePath ="l:\\";
	//webӦ�õĸ�Ŀ¼Tomcat
	public static final String ROOTPATH = System.getProperty("user.dir").replace("bin", "webapps/ShowPPT");/*"G:\\Tomcat\\tomcat6\\webapps\\ShowPPT";*/
	//�ϴ��ļ�ת��ppt�����ļ���Ŀ¼Tomcat
	public static final String PPTPicRootPath =ROOTPATH+"/Rooms/";
	//��վ����
	public static final String YUMING ="www.jjust.top";
	//��ǰû���ļ���ʾ��
	public static final int SHOWNONE = 0;
	//��ǰPPT�ļ���ʾ��
	public static final int SHOWPPT = 1;
	//��ǰû���ļ���ʾ��
	public static final int SHOWWORD = 2;
	//��ǰû���ļ���ʾ��
	public static final int SHOWEXCEL = 3;
	//����������ѯ�ȴ�ʱ��
	public static final int SLEEP = 1000;
}
