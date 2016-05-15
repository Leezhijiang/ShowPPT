package top.jjust.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.poifs.crypt.dsig.facets.Office2010SignatureFacet;

import top.jiang.utils.CookieUtils;
import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.common.StaticVariable;

public class ChangePPTServlet extends HttpServlet {

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
		String mdo = request.getParameter("mdo");
		//得到访问目标的cookie里的roomid
		String roomID = CookieUtils.getRoomID(request.getCookies());
		if(roomID!=null&&!roomID.equals("")){//房间管理员登陆
			RoomBean room = (RoomBean)CacheManager.getBean(roomID);
			if(room!=null){
				//得到room里的ppt总量和当前值
				int count = room.getPPTCount();
				int now = room.getNowPPT();
				if(mdo.equals("pre")){//上一张
					if(now==0){//下限
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print("0");
						out.flush();
						out.close();
					}else {
						now--;
						room.setNowPPT(now);
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}
				}

				if(mdo.equals("next")){//下一张
					if(now==(count-1)){//上限
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}else{
						now++;
						room.setNowPPT(now);
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}

				}
				if(mdo.equals("setNowPX")){//设置当前据top的px
					String a = request.getParameter("nowPX");
					a = a.replace("px", "");
					System.out.println();
					room.setNowPX(Float.parseFloat(a));
					System.out.println(room.getNowPX());
				}
			}
		}
		//访客登陆
		String visitorID = CookieUtils.getVisitorID(request.getCookies());
		if(visitorID!=null&&!visitorID.equals("")){
			RoomBean room = (RoomBean)CacheManager.getBean(visitorID);
			if(room!=null){
				if(mdo.equals("now")){//获取当前张
					//得到room里的ppt总量和当前值
					String temp = request.getParameter("nowpage");
					int nowpage = Integer.parseInt(temp);
					int count = room.getPPTCount();
					
//					response.setContentType("text/html");
//					PrintWriter out = response.getWriter();
//					out.print(room.getNowPPT());
//					out.flush();
//					out.close();
					while(true){
						int now = room.getNowPPT();
						if(nowpage!=now){
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.print(room.getNowPPT());
							out.flush();
							out.close();
							break;
						}
						try {
							Thread.sleep(StaticVariable.SLEEP);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				if(mdo.equals("nowPX")){//获取翻页
					String temp = request.getParameter("nowPX");
					temp = temp.replace("px", "");
					float nowPX = Float.parseFloat(temp);
					
					
//					response.setContentType("text/html");
//					PrintWriter out = response.getWriter();
//					out.print(room.getNowPPT());
//					out.flush();
//					out.close();
					while(true){
						float now = room.getNowPX();
						if(nowPX!=now){
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							System.out.println(room.getNowPX()+"..."+nowPX);
							out.print(room.getNowPX());
							out.flush();
							out.close();
							break;
						}
						try {
							Thread.sleep(StaticVariable.SLEEP);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}
	}
	/*		String mdo = request.getParameter("mdo");
		//得到访问目标的cookie里的roomid
		String roomID = CookieUtils.getRoomID(request.getCookies());
		if(roomID!=null&&!roomID.equals("")){//房间管理员登陆
			RoomBean room = (RoomBean)CacheManager.getBean(roomID);
			if(room!=null){
				//得到room里的ppt总量和当前值
				int count = room.getPPTCount();
				int now = room.getNowPPT();

				if(mdo.equals("pre")){//上一张
					if(now==0){//下限
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print("0");
						out.flush();
						out.close();
					}else {
						now--;
						room.setNowPPT(now);
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}
				}

				if(mdo.equals("next")){//下一张
					if(now==(count-1)){//上限
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}else{
						now++;
						room.setNowPPT(now);
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(now);
						out.flush();
						out.close();
					}

				}
			}

		}
		//访客登陆
		String visitorID = CookieUtils.getVisitorID(request.getCookies());
		if(visitorID!=null&&!visitorID.equals("")){
			RoomBean room = (RoomBean)CacheManager.getBean(visitorID);
			if(room!=null){
				//得到room里的ppt总量和当前值
				int count = room.getPPTCount();
				int now = room.getNowPPT();
				if(mdo.equals("now")){//获取当前张
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.print(room.getNowPPT());
					out.flush();
					out.close();
				}
			}
		}*/

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
