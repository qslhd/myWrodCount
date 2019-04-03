package demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = new DaoImpl(); 
    public Controller() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		switch (op) {
		case "showRow":
			showRow(request, response);
			break;
		case "showChar":
			showChar(request, response);
			break;
		case "showAllByDic":
			showAllByDic(request, response);
			break;
		case "showAllByHistogram":
			showAllByHistogram(request, response);
			break;
		case "showByWord":
			showByWord(request, response);
			break;
		case "showByK":
			showByK(request, response);
			break;
		case "showByDOrder":
			showByDOrder(request, response);
			break;
		case "showExceptFunctionWord":
			showExceptFunctionWord(request, response);
			break;
			
		default:
			break;
		}
	}
	
	private void showRow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/** 获取当前系统时间*/
		long startTime =  System.currentTimeMillis();
		/** 程序运行 processRun();*/
 


		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		int row = dao.selectRowNum(bufferedReader);
		/** 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数，除以1000就是秒数*/
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);     ///1000;
		request.setAttribute("row", row);
		request.setAttribute("time", time);
		request.getRequestDispatcher("row.jsp").forward(request,
				response);
		bufferedReader.close();
	}

	private void showChar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		int charNum = dao.selectCharNum(bufferedReader);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("charNum", charNum);
		request.getRequestDispatcher("char.jsp").forward(request,
				response);
		bufferedReader.close();
	}
	
	//?????
	private void showAllByDic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		List<Map.Entry<String, Integer>> list= dao.selectAllWordCountByDic(bufferedReader);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("list", list);
		request.getRequestDispatcher("allDic.jsp").forward(request,
				response);
		bufferedReader.close();
	}
	
	private void showAllByHistogram(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		// 在业务层获取创建的柱状图（此时柱状图已经创建完成的）
	    JFreeChart chart = dao.ShowCountHistogram(bufferedReader);
	    // 将图形转换为图片传到
	    //String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400,
	        //    null, request.getSession());
	    
	    //String chartURL = "d:"+ request.getContextPath() + "/chart?filename="
	     //       + fileName;
	   // String chartURL = "d:"+ request.getContextPath() + "/"
	     //       + fileName;
	    ChartUtilities.writeChartAsPNG(response.getOutputStream(),chart,900,450,null); 
	    //request.setAttribute("chartURL", chartURL);
	   // request.setAttribute("fileName", fileName);
	    request.getRequestDispatcher("histogram.jsp").forward(request,
				response);
		bufferedReader.close();

	}
	
	private void showByWord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		request.setCharacterEncoding("UTF-8");
		String file = request.getParameter("file");
		String word = request.getParameter("word");
		//System.out.println(word);
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		int count = dao.selectCountByWord(bufferedReader, word);
		//System.out.println(count);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("count", count);
		request.getRequestDispatcher("showCountByWord.jsp").forward(request,
				response);
		bufferedReader.close();
	}
	
	private void showByK(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		String file = request.getParameter("file");
		String kStr = request.getParameter("k");
		int k = Integer.parseInt(kStr);
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Map<String, Integer> map = dao.selectWordCountByK(bufferedReader, k);
		System.out.println(map);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("map", map);
		request.getRequestDispatcher("showCountByK.jsp").forward(request,
				response);
		bufferedReader.close();
	}
	
	private void showByDOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Map<String, Integer> map = dao.selectWordCountByDOrder(bufferedReader);
		System.out.println(map);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("map", map);
		request.getRequestDispatcher("showByDOrder.jsp").forward(request,
				response);
		bufferedReader.close();
	}
	
	private void showExceptFunctionWord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime =  System.currentTimeMillis();
		String file = request.getParameter("file");
		//String path = this.getServletContext().getRealPath(file);
		String path = "d:\\" + file;
		//System.out.println(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Map<String, Integer> map = dao.selectExceptFunctionWord(bufferedReader);
		System.out.println(map);
		long endTime =  System.currentTimeMillis();
		long time = (endTime-startTime);
		request.setAttribute("time", time);
		request.setAttribute("map", map);
		request.getRequestDispatcher("showExceptFunctionWord.jsp").forward(request,
				response);
		bufferedReader.close();
	}
}
