package demo;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;



public class DaoImpl implements Dao{

	@Override
	public Map<String, Integer> AllWordCount(BufferedReader bufferedReader) throws IOException {
        String s;
        Map<String, Integer> map = new TreeMap<String, Integer>();
        while ((s = bufferedReader.readLine()) != null) {
        	//ͨ����ֵ�Եķ�ʽȥ�ֱ�洢���ʺͳ��ֵĴ���
            //Map<String, Integer> map = new TreeMap<String, Integer>();
            //����һ��words���飬��split�ָ���ַ�����������
            String[] words = s.split("[������.��,\"!--;:?\'\\] ]");
            for (int i = 0; i < words.length; i++) {
            	String key = words[i].toLowerCase();//�����е���ת��ΪСд
                if (key.length() > 0) {
                	//��containsKey�ж�map���϶������Ƿ����ĳ���ַ���
                    if (!map.containsKey(key)) {//���������˵����һ�γ���,���Ƶ��ֵ��1;
                    	map.put(key, 1);
                    	} else {// ������ǵ�һ�γ��֣��Ͱ�valueֵ++����ôvalueֵ�Ƕ��پ��ǳ����˼���
                    		int value = map.get(key);//��get(key)��ȡ��Ӧ��valueֵ
                    		value++;
                    		map.put(key, value);
                    		}
                    }
                }
            
           	}
        //bufferedWriter.newLine();
        return map;
	}

	@Override
	public int selectRowNum (BufferedReader bufferedReader) throws IOException{
		int line = 0;
    	while (bufferedReader.readLine() != null){
    		line++;
    	}
    	
		return line;
	}
	
	public int selectCharNum (BufferedReader bufferedReader) throws IOException{
		int x = 0;
    	char[] ch = new char[300000];
    	int len = 0 ;
    	while ((len = bufferedReader.read(ch)) != -1) {
    		x = len;
    	}
		return x;
	}

	@Override
	public  List<Map.Entry<String, Integer>> selectAllWordCountByDic(BufferedReader bufferedReader) throws IOException {
		Map<String, Integer> map = this.AllWordCount(bufferedReader);
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
        //System.out.println("������result.txt�ļ�");
        //System.out.println("-----------------���е��ʰ��ֵ�˳����������---------------");
      	/*for (Map.Entry<String, Integer> entry : list) { 
      		System.out.println(entry.getKey() + "----" + entry.getValue());
       		bufferedWriter.write(entry.getKey()+"----"+entry.getValue()+"\r\n");
       		}*/
		return list;
	}

	@Override
	public int selectCountByWord(BufferedReader bufferedReader, String word) throws IOException {
		Map<String, Integer> map = this.AllWordCount(bufferedReader);
		//�ж��Ƿ������Ҫ��ѯ�ĵ���
        boolean b = map.containsKey(word);
        int v = 0;
        if(b){
        	//����key���ʲ��Ҵ���value
        	for (Map.Entry<String, Integer> m :map.entrySet())  {
        		if (m.getKey().equals(word)) {
        		v = m.getValue();
        		}}
        }else {
        	v = 0;
		}
        return v;
	}

	@Override
	public Map<String, Integer> selectWordCountByK(BufferedReader bufferedReader, Integer k) throws IOException{
		Map<String, Integer> map = this.AllWordCount(bufferedReader);
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


        for (Map.Entry<String, Integer>set:nlist.subList(0, k)){
       	 sortedMap.put(set.getKey(), set.getValue());
        }
		return sortedMap;
	}

	@Override
	public Map<String, Integer> selectExceptFunctionWord(BufferedReader bufferedReader) throws IOException {
		Map<String, Integer> map = this.selectWordCountByDOrder(bufferedReader);
		Map<String, Integer> exceptFunction = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> w :map.entrySet())  {
			if(!w.getKey().equals("the")&&!w.getKey().equals("is")&&!w.getKey().equals("you")&&!w.getKey().equals("yourself")&&!w.getKey().equals("your")&&!w.getKey().equals("them")&&!w.getKey().equals("their")&&!w.getKey().equals("to")&&!w.getKey().equals("by")&&!w.getKey().equals("is")&&!w.getKey().equals("a")&&!w.getKey().equals("and")&&!w.getKey().equals("was")&&!w.getKey().equals("has")&&!w.getKey().equals("had")&&!w.getKey().equals("I")&&!w.getKey().equals("for")&&!w.getKey().equals("my")&&!w.getKey().equals("me")&&!w.getKey().equals("with")&&!w.getKey().equals("of")&&!w.getKey().equals("in")&&!w.getKey().equals("on")&&!w.getKey().equals("that")&&!w.getKey().equals("it")&&!w.getKey().equals("The")&&!w.getKey().equals("at")&&!w.getKey().equals("which")&&!w.getKey().equals("he")&&!w.getKey().equals("as")
		       		 &&!w.getKey().equals("but")&&!w.getKey().equals("his")&&!w.getKey().equals("from")&&!w.getKey().equals("some")&&!w.getKey().equals("be")&&!w.getKey().equals("were")&&!w.getKey().equals("not") &&!w.getKey().equals("they")&&!w.getKey().equals("this")&&!w.getKey().equals("an")&&!w.getKey().equals("no")&&!w.getKey().equals("into")&&!w.getKey().equals("It")&&!w.getKey().equals("there")&&!w.getKey().equals("But")&&!w.getKey().equals("him")&&!w.getKey().equals("could")&&!w.getKey().equals("been")&&!w.getKey().equals("would")&&!w.getKey().equals("she")&&!w.getKey().equals("then")&&!w.getKey().equals("Then")&&!w.getKey().equals("have"))
		       		{
				
				exceptFunction.put(w.getKey(), w.getValue());
    		}}
		

		return exceptFunction;
	}

	 private static CategoryDataset getDataSet(BufferedReader bufferedReader) throws IOException {
		 Dao dao = new DaoImpl();
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         Map<String, Integer> map = dao.selectWordCountByDOrder(bufferedReader);
         
         List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
 
         for (Map.Entry<String, Integer>set:list.subList(0, 15)){
        	 dataset.addValue(set.getValue(), set.getKey(), set.getKey());
            }

        // for (Map.Entry<String, Integer> entry : list) {
        	// dataset.addValue(entry.getValue(), entry.getKey(), entry.getKey());
         //}
         //dataset.addValue(100, "����", "ƻ��");
       
         return dataset;
}

	 
	@Override
	public JFreeChart ShowCountHistogram(BufferedReader bufferedReader) throws IOException {
		
		
		CategoryDataset dataset = getDataSet(bufferedReader);
        JFreeChart chart = ChartFactory.createBarChart3D(
       		                 "Ӣ���ı���Ƶͳ��ͼ", // ͼ�����
                            "��������", // Ŀ¼�����ʾ��ǩ
                            "����", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                            false,          // �Ƿ����ɹ���
                            false           // �Ƿ�����URL����
                            );
        
        //�����￪ʼ
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));        //ˮƽ�ײ�����
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
          
          //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
        /*  
         JFrame frame=new JFrame("Java����ͳ��ͼ");
     	frame.setLayout(new GridLayout(1,1,10,10));
     	frame.add(new ChartPanel(chart,true));           //�������ͼ
     	frame.setBounds(50, 50, 800, 600);
     	frame.setVisible(true);
*/
		return chart;
	}

	@Override
	public Map<String, Integer> selectWordCountByDOrder(BufferedReader bufferedReader) throws IOException {
		Map<String, Integer> map = this.AllWordCount(bufferedReader);
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
        
		return result;
	}

}
