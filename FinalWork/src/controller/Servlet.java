package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import result.Result;
import util.ApiIDKEY;
import util.TransApi;
import util.TransformZh;
@WebServlet("/translate.do")

public class Servlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("½øÈë¸Ãservlet");
		request.setCharacterEncoding("utf-8");
		String q=request.getParameter("query");
		URLEncoder.encode(q, "utf-8");
		System.out.println(q);
		String option=request.getParameter("option");
		System.out.println(option);
		String from="";
		String to="";
		String answer="";
		if(option.equals("auto")) {
			from="auto";
			to="en";
		}
		else {
			from=option.split("-")[0];
			to=option.split("-")[1];
		}
		System.out.println(from);
		System.out.println(to);
		TransApi api = new TransApi(ApiIDKEY.getAppId(), ApiIDKEY.getSecurityKey());
		String jsonResult=api.getTransResult(q, from, to);
		System.out.println(jsonResult);
		if(option.equals("en-zh")) {
			 answer=TransformZh.unicodeToCn(jsonResult.substring(jsonResult.lastIndexOf(":")+2,jsonResult.indexOf("\"}")));
		}else {
		 answer = jsonResult.substring(jsonResult.lastIndexOf(":")+2,jsonResult.indexOf("\"}"));
		}
		Map<String,String> res=new HashMap<String, String>();
		res.put("answer", answer);
		String jsonStr=new Gson().toJson(res);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.printf(jsonStr);
		pw.flush();
		pw.close();
	}
}
