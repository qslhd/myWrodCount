package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;

public interface Dao {
	
	//所有词频!
	public Map<String, Integer> AllWordCount (BufferedReader bufferedReader/*, BufferedWriter bufferedWriter*/) throws IOException;

	//统计文本行!
	public int selectRowNum (BufferedReader bufferedReader) throws IOException;
	
	//统计字符数!
	public int selectCharNum (BufferedReader bufferedReader) throws IOException;
	
	//所有词频字典输出!
	public  List<Map.Entry<String, Integer>> selectAllWordCountByDic (BufferedReader bufferedReader) throws IOException;
	
	//柱状图
	public JFreeChart ShowCountHistogram(BufferedReader bufferedReader) throws IOException;
	
	//指定单词!
	public int selectCountByWord (BufferedReader bufferedReader, String word) throws IOException  ;
	
	//前k个高频词!
	public Map<String, Integer> selectWordCountByK (BufferedReader bufferedReader, Integer k) throws IOException;
	
	//降序词频显示!
	public Map<String, Integer> selectWordCountByDOrder (BufferedReader bufferedReader) throws IOException;
	
	//除冠词、代词、介词以外的高频词
	public Map<String, Integer> selectExceptFunctionWord (BufferedReader bufferedReader) throws IOException;
	
	//计时
	
}
