import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Weather extends JPanel{
	String city;//������
	String temperature;//�¶�
	String weather;//����״��
	String wind;//����
	String pollution;//������Ⱦ�̶�
	String zwx;//������ָ��
	String ktjy;//�յ�����
	String cyzs;//����ָ��
	String cysm;//����˵��
	String tgzs;//���ָ��
	String tgsm;//���˵��
	String ydzs;//�˶�ָ��
	String ydsm;//�˶�ָ����ϸ˵��
	String updatetime;//����ʱ��
	
	
	public Weather(String city)
	{
		this.city=city;
	}
	public void getWeatherData(int day)
	{
		String data[]=new String[15];
		String link;
        URL url;
        try {
			this.city=java.net.URLEncoder.encode(this.city, "gb2312");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("�ݲ�֧�ָó��л���в�����");
		}
        link="http://php.weather.sina.com.cn/xml.php?city="+this.city+"&password=DJOYnieT8234jlsK&day="+day;
        try {
            url = new URL(link);  
            String[] nodes = {
            		"city",
            		"status1",
            		"temperature1",
            		"temperature2",
            		"direction1",
            		"pollution",
            		"zwx",
            		"ktk_l",
            		"chy",
            		"chy_shuoming",
            		"ssd",
            		"ssd_s",
            		"yd",
            		"yd_s",
            		"udatetime"
            }; 
            WeatherAPI parser = new WeatherAPI(url);
            parser.getValue(nodes,data);
            this.city=data[0];
    		this.weather=data[1];
    		this.temperature=data[3]+"�㡪"+data[2]+"��";
    		this.wind=data[4];
    		this.pollution=data[5];
    		this.zwx=data[6];
    		this.ktjy=data[7];
    		this.cyzs=data[8];
    		this.cysm=data[9];
    		this.tgzs=data[10];
    		this.tgsm=data[11];
    		this.ydzs=data[12];
    		this.ydsm=data[13];
    		this.updatetime=data[14];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
	}
	public JPanel getMainPanel(int i)
	{
		JPanel jpmain=new JPanel();
		JPanel jpvice=new JPanel();
		JLabel img=new JLabel();//����ͼƬ
		JLabel t=new JLabel();//�¶�
		JLabel weather=new JLabel();//����
		JLabel wind=new JLabel();//����
		
		jpmain.setLayout(new GridLayout(2,1));
		jpvice.setLayout(new GridLayout(3,1));
		
		getWeatherData(i);
		
		if(i==0)
		{
			img.setSize(140,140);
			setLabel(t,this.temperature,38);
			setLabel(weather,this.weather,38);
			setLabel(wind,this.wind,38);
		}
		else
		{
			img.setSize(90,90);
			setLabel(t,this.temperature,28);
			setLabel(weather,this.weather,28);
			setLabel(wind,this.wind,28);
		}
		
		String temp="images/"+this.weather+".png";
		setIcon(temp,img);
		
		jpvice.add(t);
		jpvice.add(weather);
		jpvice.add(wind);
		
		jpvice.setOpaque(false);
		
		jpmain.add(img);
		jpmain.add(jpvice);
		
		jpmain.setOpaque(false);
		
		return jpmain;
	}
	
	public TranslucenceJPanel getVicePanel(int i)
	{
		Calendar now = Calendar.getInstance();
		TranslucenceJPanel jp=new TranslucenceJPanel();
		jp.setBackground(Color.WHITE);
		jp.setTransparent(0);
		JLabel date=new JLabel();
		setLabel(date,(now.get(Calendar.MONTH)+1)+"��"+(now.get(Calendar.DAY_OF_MONTH)+i)+"��",28);
		jp.add(date);
		jp.add(getMainPanel(i));
		jp.setOpaque(false);
		return jp;
	}
	
	public TranslucenceJPanel getlittlePanel(int i)
	{
		TranslucenceJPanel jp=new TranslucenceJPanel();
		jp.setBackground(Color.WHITE);
		jp.setTransparent(0);
		jp.setSize(250,125);
		getWeatherData(i);
		JLabel jl=new JLabel();
		setLabel(jl,this.city,38);
		JLabel img=new JLabel();
		img.setSize(100,100);
		setIcon("images/"+this.weather+".png",img);
		jp.add(jl);
		jp.add(img);
		jp.repaint();
		return jp;
	}
	
	private void setLabel(JLabel jl,String str,int fontweight)
	{
		jl.setText(str);
		jl.setFont(new Font("Times New Romans",Font.BOLD,fontweight));
		jl.setForeground(Color.WHITE);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
	}
	public void setIcon(String file,JLabel com)
	{ 
		ImageIcon ico=new ImageIcon(file);  
		Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),ico.getImage().SCALE_DEFAULT);  
		ico=new ImageIcon(temp);  
		com.setIcon(ico);  
		com.setHorizontalAlignment(SwingConstants.CENTER);
	}
}


class WeatherAPI {
    InputStream inStream;
    Element root;
    public WeatherAPI(URL url) {
        InputStream inStream = null;
        try {
            inStream = url.openStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (inStream != null) {
            this.inStream = inStream;
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder domBuilder = domfac.newDocumentBuilder();
                Document doc = domBuilder.parse(inStream);
                root = doc.getDocumentElement();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Map<String, String> getValue(String[] nodes,String[] data) {  
        if (inStream == null || root==null) {
            return null;  
        }
        Map<String, String> map = new HashMap<String, String>();  
        // ��ʼ��ÿ���ڵ��ֵΪnull  
        for (int i = 0; i < nodes.length; i++) {  
            map.put(nodes[i], null);  
        }
        // ������һ�ڵ�  
        NodeList topNodes = root.getChildNodes();  
        if (topNodes != null) 
        {  
            for (int i = 0; i < topNodes.getLength(); i++) 
            {  
                Node book = topNodes.item(i);  
                if (book.getNodeType() == Node.ELEMENT_NODE) 
                {  
                    for (int j = 0; j < nodes.length; j++) 
                    {  
                        for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) 
                        {  
                            if (node.getNodeType() == Node.ELEMENT_NODE&&node.getNodeName().equals(nodes[j])) 
                            {  
                                    String val = node.getTextContent();
                                    data[j]=val; 
                            }
                        }
                    }
                }
            }
        }
        return map;
    }
}
