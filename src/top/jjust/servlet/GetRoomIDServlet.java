package top.jjust.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.jiang.utils.NumberUtils;
import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.container.Room;

public class GetRoomIDServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用data生成房间号8位，一天多重复一遍
		Date date = new Date();
		int roomID = (int)(date.getTime()%100000000);
		Room room = new Room(roomID + "");

		//����������
		String pd = NumberUtils.getFixLenthString(4);
		//�ѷ���źͶ�Ӧ������뻺��
		RoomBean ur = new RoomBean(roomID + "", pd);
		CacheManager.insert(ur);
		//���÷�������coockie���
		Cookie c = new Cookie("roomID", roomID + "");
		response.addCookie(c);
		//����
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("{\"roomID\":\""+roomID+"\",\"pd\":\""+pd+"\"}");
		out.flush();
		out.close();
	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
