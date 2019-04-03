package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;

public interface Dao {
	
	//���д�Ƶ!
	public Map<String, Integer> AllWordCount (BufferedReader bufferedReader/*, BufferedWriter bufferedWriter*/) throws IOException;

	//ͳ���ı���!
	public int selectRowNum (BufferedReader bufferedReader) throws IOException;
	
	//ͳ���ַ���!
	public int selectCharNum (BufferedReader bufferedReader) throws IOException;
	
	//���д�Ƶ�ֵ����!
	public  List<Map.Entry<String, Integer>> selectAllWordCountByDic (BufferedReader bufferedReader) throws IOException;
	
	//��״ͼ
	public JFreeChart ShowCountHistogram(BufferedReader bufferedReader) throws IOException;
	
	//ָ������!
	public int selectCountByWord (BufferedReader bufferedReader, String word) throws IOException  ;
	
	//ǰk����Ƶ��!
	public Map<String, Integer> selectWordCountByK (BufferedReader bufferedReader, Integer k) throws IOException;
	
	//�����Ƶ��ʾ!
	public Map<String, Integer> selectWordCountByDOrder (BufferedReader bufferedReader) throws IOException;
	
	//���ڴʡ����ʡ��������ĸ�Ƶ��
	public Map<String, Integer> selectExceptFunctionWord (BufferedReader bufferedReader) throws IOException;
	
	//��ʱ
	
}
