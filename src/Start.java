import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Start extends JFrame implements MouseListener{
	JPanel jp=new JPanel();
	JPanel jpw=new JPanel();
	JPanel jpwmain=new JPanel();
	JPanel jpwvice=new JPanel();
	JLabel timenow;
	JLabel clothes=new JLabel();
	JLabel cysm=new JLabel();
	JLabel cityname=new JLabel();
	JLabel label;
	
	JLabel qtcs=new JLabel();
	JLabel ckxx=new JLabel();
	JLabel dwcx=new JLabel();
	
	TranslucenceJPanel vice1;
	TranslucenceJPanel vice2;
	TranslucenceJPanel vice3;
	TranslucenceJPanel vice4;
	TranslucenceJPanel main;
	
	TranslucenceJPanel exit=new TranslucenceJPanel();
	
	public static List<Weather> mycity=new ArrayList();
	
	public Start(int i)
	{
		jp.setLayout(null);
		jp.setBounds(0, 0, 1000, 500);
		jp.setOpaque(false);
		jpw.setLayout(null);
		jpw.setBounds(0, 0, 1000, 500);
		jpw.setOpaque(false);
		jpwmain.setOpaque(false);
		jpwvice.setOpaque(false);
		
		Calendar now = Calendar.getInstance();
		timenow=new JLabel("    "+now.get(Calendar.YEAR)+"年"
				+(now.get(Calendar.MONTH)+1)+"月"
				+now.get(Calendar.DAY_OF_MONTH)+"日"
				+now.get(Calendar.HOUR_OF_DAY)+"时");
		timenow.setFont(new Font("宋体",Font.BOLD,20));
		timenow.setForeground(Color.WHITE);
		timenow.setBounds(15, 30, 500, 25);
		jp.add(timenow);
		
		
	    jpwmain.setBounds(75, 150, 200, 300);
		jpwmain.setLayout(new GridLayout(1,1));
		jpwmain.setOpaque(false);
	    
		jpwvice.setBounds(350, 50, 600, 300);
		jpwvice.setLayout(new GridLayout(1,4));
		jpwvice.setOpaque(false);
		
		vice1=mycity.get(i).getVicePanel(1);
		vice1.addMouseListener(this);
		jpwvice.add(vice1);
		vice2=mycity.get(i).getVicePanel(2);
		vice2.addMouseListener(this);
		jpwvice.add(vice2);
		/*vice3=mycity.get(i).getVicePanel(3);
		vice3.addMouseListener(this);
		jpwvice.add(vice3);
		vice4=mycity.get(i).getVicePanel(4);
		vice4.addMouseListener(this);
		jpwvice.add(vice4);*/
		
		JPanel main=mycity.get(i).getMainPanel(0);
		jpwmain.add(main);
		
		jpw.add(jpwmain);
		jpw.add(jpwvice);
		
		cityname.setBounds(75,50,200, 100);
		setLabel(cityname, mycity.get(i).city,58);
		jp.add(cityname);
		
		clothes.setSize(100, 100);
		setIcon("images/穿衣.png",clothes);
		clothes.setBounds(350, 375, 100, 100);
		jp.add(clothes);
		
		qtcs.setBounds(50,520,250,63);
		setIcon("images/其他城市.png",qtcs);
		qtcs.addMouseListener(this);
		jp.add(qtcs);
		
		ckxx.setBounds(375,520,250,63);
		setIcon("images/查看详细.png",ckxx);
		ckxx.addMouseListener(this);
		jp.add(ckxx);
		
		dwcx.setBounds(700,520,250,63);
		setIcon("images/点我出行.png",dwcx);
		dwcx.addMouseListener(this);
		jp.add(dwcx);
		
		exit.setLayout(new GridLayout(1,1));
		exit.setBounds(950,0,50,50);
		exit.setBackground(Color.white);
		exit.setOpaque(false);
		JLabel exitmsg=new JLabel();
		setLabel(exitmsg,"×",38);
		//exitmsg.setFont(new Font("黑体",Font.BOLD,38));
		exit.setTransparent(0);
		exit.addMouseListener(this);
		exit.add(exitmsg);
		jp.add(exit);
		
		setLabel(cysm,"<html>"+mycity.get(i).cysm+"</html>",28);
		cysm.setBounds(475, 375,475,100);
		jp.add(cysm);
		
		jp.add(jpw);
		//定义背景、大小
		//mycity.get(0).getWeatherData(0);
		String backgroundtemp="images/"+mycity.get(i).weather+".jpg";
		ImageIcon background = new ImageIcon(backgroundtemp);  
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int windowsWedth = 1000;
		int windowsHeight = 600;
		this.setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, windowsWedth, windowsHeight);
		setSize(windowsWedth,windowsHeight);
		Image temp=background.getImage().getScaledInstance(this.getWidth(),this.getHeight(),background.getImage().SCALE_DEFAULT);  
		background=new ImageIcon(temp);  
		label = new JLabel(background); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		setUndecorated(true);
		label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		jp.add(label);
		this.add(jp);
		setVisible(true); 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ckxx)
		{
			setIcon("images/查看详细1.png",ckxx);
		}
		if(e.getSource()==qtcs)
		{
			setIcon("images/其他城市1.png",qtcs);
		}
		if(e.getSource()==dwcx)
		{
			setIcon("images/点我出行1.png",dwcx);
		}
		if(e.getSource()==vice1)
		{
			mycity.get(0).getVicePanel(1);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice1.repaint();
		}
		if(e.getSource()==vice2)
		{
			mycity.get(0).getVicePanel(2);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice2.repaint();
		}
		if(e.getSource()==vice3)
		{
			mycity.get(0).getVicePanel(3);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice3.repaint();
		}
		if(e.getSource()==vice4)
		{
			mycity.get(0).getVicePanel(4);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice4.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ckxx)
		{
			setIcon("images/查看详细.png",ckxx);
		}
		if(e.getSource()==qtcs)
		{
			setIcon("images/其他城市.png",qtcs);
		}
		if(e.getSource()==dwcx)
		{
			setIcon("images/点我出行.png",dwcx);
		}
		if(e.getSource()==vice1)
		{
			mycity.get(0).getVicePanel(0);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice1.repaint();
		}
		if(e.getSource()==vice2)
		{
			mycity.get(0).getVicePanel(0);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice2.repaint();
		}
		if(e.getSource()==vice3)
		{
			mycity.get(0).getVicePanel(0);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice3.repaint();
		}
		if(e.getSource()==vice4)
		{
			mycity.get(0).getVicePanel(0);
			setIcon("images/"+mycity.get(0).weather+".jpg",label);
			vice4.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ckxx)
		{
			new ckxx(mycity.get(0));
		}
		if(e.getSource()==qtcs)
		{
			new qtcs();
		}
		if(e.getSource()==dwcx)
		{
			File file = new File("map.html");
	        Runtime ce=Runtime.getRuntime();
	        System.out.println(file.getAbsolutePath());
	        try {
				ce.exec("cmd   /c   start  "+file.getAbsolutePath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
