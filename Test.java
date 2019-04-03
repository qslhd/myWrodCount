package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;
 
public class Test {
    public static void main(String[] args) throws IOException {
        
    	
    	BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\essay.txt"));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\result.txt"));
        Dao dao = new DaoImpl();
        //Map<String, Integer> map = dao.AllWordCount(bufferedReader);
        
        System.out.print("��������ʹ�õĹ��ܣ�");
        System.out.println("1.��ѯһ�����ʳ��ִ�����");
        System.out.println("2.��ѯǰn���������ĵ��ʣ�");
        System.out.println("3.���ȫ����Ƶ���ֵ�˳������");
        System.out.println("4.��������ȫ����Ƶ��");
        Scanner scan = new Scanner(System.in);
        int read = scan.nextInt();
        
        if(read==1){
        	System.out.print("���������ѯ�ĵ��ʣ�");
            Scanner scan1 = new Scanner(System.in);
            String word = scan1.nextLine();
            int v = dao.selectCountByWord(bufferedReader, word);
            System.out.println(v);
            /*boolean b = map.containsKey(word);
            int v = 0;
            if(b){
            	//����key���ʲ��Ҵ���value
            	for (Map.Entry<String, Integer> m :map.entrySet())  {
            		if (m.getKey().equals(word)) {
            		v = m.getValue();
            		System.out.println(v);
            		}}
            }else {
            	System.out.println("�˵��ʲ����ڣ�");
    		}*/
        }else if(read==2){
        	 System.out.print("����������n��ѯǰn���������ĵ���:");
             Scanner scan2 = new Scanner(System.in);
             int n = scan2.nextInt();
             Map<String, Integer> map = dao.selectWordCountByK(bufferedReader, n);
             System.out.println(map);
             /*
             Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
             List<Map.Entry<String, Integer>> nlist = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
             Collections.sort(nlist, new Comparator<Map.Entry<String, Integer>>()
             {
                 @Override
                 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
                 {
                     int compare = (o1.getValue()).compareTo(o2.getValue());
                     return -compare;
                 }
             });


             for (Map.Entry<String, Integer>set:nlist.subList(0, n)){
            	 sortedMap.put(set.getKey(), set.getValue());
             }
             System.out.println(sortedMap);
             	}
             */
             
        }else if(read==3){
        	List<Map.Entry<String, Integer>> list = dao.selectAllWordCountByDic(bufferedReader);
        	/*
        	 //����TreeMapʵ��Comparator�ӿ�
            Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            	public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) { 
            		return o1.getKey().compareTo(o2.getKey());//��������
           		}
            }; 
            //mapת����list��������Entry��Map�е�һ����̬�ڲ��࣬������ʾMap�е�ÿ����ֵ��
            //map.EntrySet(),ʵ����Set�ӿڣ������ŵ��Ǽ�ֵ��.
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet()); 
            // ����
            Collections.sort(list,valueComparator); 
            System.out.println("������result.txt�ļ�");
            System.out.println("-----------------���е��ʰ��ֵ�˳����������---------------");
          	for (Map.Entry<String, Integer> entry : list) { 
          		System.out.println(entry.getKey() + "----" + entry.getValue());
           		bufferedWriter.write(entry.getKey()+"----"+entry.getValue()+"\r\n");
           		}
              */
        	System.out.println("-----------------���е��ʰ��ֵ�˳����������---------------");
          	for (Map.Entry<String, Integer> entry : list) { 
          		System.out.println(entry.getKey() + "----" + entry.getValue());
           		}
        }else if(read==4){
        	Map<String, Integer> map = dao.selectWordCountByDOrder(bufferedReader);
            System.out.println(map);
        	/*
	         List<Map.Entry<String, Integer>> nlist = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
	         Collections.sort(nlist, new Comparator<Map.Entry<String, Integer>>()
	         {
	             @Override
	             public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
	             {
	                 int compare = (o1.getValue()).compareTo(o2.getValue());
	                 return -compare;
	             }
	         });
	
	         Map<String, Integer> result = new LinkedHashMap<String, Integer>();
	         for (Map.Entry<String, Integer> entry : nlist) {
	             result.put(entry.getKey(), entry.getValue());
	         }
	
	         //����
	         //Map<String, Integer> result = new LinkedHashMap<>();
	         //Stream<Entry<String, Integer>> st = map.entrySet().stream();
	         //st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
	         for (String a : result.keySet()) {
	         	System.out.println( a + "-----" + result.get(a));
	         	}
	       */  
	         
	    }else if(read==5){
	    	//�����ַ���
	    	//int row = dao.selectRowNum(bufferedReader);
	    	//System.out.println(row);
	    	int charNum = dao.selectCharNum(bufferedReader);
	    	System.out.println(charNum);
	    	//LineNumberReader lnr = new LineNumberReader(bufferedReader);
	    	/*����
	    	int line = 0;
	    	while (bufferedReader.readLine() != null){
	    		line++;
	    	}
	    	System.out.println(line);*/
	    	//lnr.close();
	    	
	    	/*�ַ���
	    	int x = 0;
	    	char[] ch = new char[300000];
	    	int len = 0 ;
	    	while ((len = bufferedReader.read(ch)) != -1) {
	    		x = len;
	    	}
	    	System.out.println(x);*/
	    	
	    }else if(read==6){
	    	//������ʴ�Ƶ
	    	Map<String, Integer> map = dao.selectExceptFunctionWord(bufferedReader);
            System.out.println(map);
	    	
	    }else if(read==7){
	    	//��״ͼ
	    	dao.ShowCountHistogram(bufferedReader);
	    	
	    }else{
        	System.out.println("�������");
		}
        
        //bufferedWriter.newLine();
        // �ر����������
        bufferedReader.close();
        //bufferedWriter.close();
    }
}
