package com.edu.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.bean.WXUserBean;
import com.edu.util.OAuth2API;

/**
 * LoginServlet�ص�������
 * 
 * @author Poppy(��Ӧ��)
 *
 */
@WebServlet(name = "callBackServlet", urlPatterns = "/callBackServlet")
public class CallBackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		try {
			WXUserBean weiXinUserInfo;
			weiXinUserInfo = OAuth2API.getUserInfo(req, resp);
			if (weiXinUserInfo != null) {
				HttpSession session = req.getSession();
				String nick = weiXinUserInfo.getNickname();

				if (nick == null || "".equals(nick)) {
					nick = "����";
				}
				System.out.println("------nickName-" + nick);
				session.setAttribute("unionid", weiXinUserInfo.getUnionid());
				session.setAttribute("nickname", nick);
				nick = URLEncoder.encode(nick, "UTF-8");
				resp.sendRedirect(
						"index.jsp?uid=" + weiXinUserInfo.getUnionid() + "&tid=" + nick + "&r=" + Math.random());
			} else {
				resp.sendRedirect("LoginServlet");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * String code = req.getParameter("code"); String state =
		 * req.getParameter("state"); // ����̨���code��state System.out.println(
		 * "redirct code-->" + code + ",redirct state-->" + state); if (code !=
		 * null && state != null) { // ��һ��URL��ַ String url =
		 * "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
		 * Global.APP_ID + "&secret=" + Global.SECRET + "&code=" + code +
		 * "&grant_type=authorization_code"; String jsonString =
		 * doGetByHttpClient(url); // ���ص���json�ַ���
		 * System.out.println("access_token-->" + jsonString); // �洢΢��������Ϣ��ʵ����
		 * AccessTokenBean weiChatInfo = JsonUtil.getWeiChat(jsonString);
		 * 
		 * // ��ȡ���û��Ļ�����Ϣ String getUserUrl =
		 * "https://api.weixin.qq.com/sns/userinfo?access_token=" +
		 * weiChatInfo.getAccess_token() + "&openid=" + weiChatInfo.getOpenid();
		 * String weiUseString = doGetByHttpClient(getUserUrl);
		 * System.out.println("---->CallServlet two token" + weiUseString);
		 * WXUserBean weiXinUserInfo = JsonUtil.getWeiXinUserInfo(weiUseString);
		 * HttpSession session = req.getSession(); String nick =
		 * weiXinUserInfo.getNickname(); if (null == nick || "".equals(nick)) {
		 * nick = "����"; } System.out.println("nick----->" + nick);
		 * session.setAttribute("unionid", weiChatInfo.getUnionid());
		 * session.setAttribute("nickname", nick);
		 * 
		 * nick = URLEncoder.encode(nick, "UTF-8");
		 * System.out.println("nick--CallBack---->" + nick);
		 * resp.sendRedirect("index.jsp?uid=" + weiXinUserInfo.getUnionid() +
		 * "&tid=" + nick); } else {
		 * System.out.println("CallBackServlet----->else����");
		 * resp.sendRedirect("tankyou.html"); }
		 **/
	}

	/**
	 * ������������
	 * 
	 * @param url
	 * @return
	 */
	public String doGetByHttpClient(String url) {
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setReadTimeout(5000);
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();// ��������
			InputStream inStream = connection.getInputStream();
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			result = buffer.toString();
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		} // ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
