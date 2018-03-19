import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class qtcs extends JFrame implements MouseListener{
	JPanel jp=new JPanel();
	JPanel jp_under=new JPanel();
	
	JLabel back=new JLabel();
	
	JLabel add=new JLabel();
	
	TranslucenceJPanel jpcity[]=new TranslucenceJPanel[5];
	
	public qtcs()
	{
		for(int i=0;i<5;i++)
		{
			jpcity[i]=new TranslucenceJPanel();
		}
		this.setLayout(null);
		jp.setLayout(null);
		jp.setOpaque(false);
		jp.setBounds(0,0,1000,600);
		
		jp_under.setLayout(null);
		jp_under.setOpaque(false);
		jp_under.setBounds(0,0,1000,600);
		
		int i=0;
		while(i<Start.mycity.size()&&i<2)
		{
			JLabel jltemp=new JLabel();
			setLabel(jltemp,Start.mycity.get(i).city,58);
			JPanel jpm=Start.mycity.get(i).getMainPanel(0);
			jpcity[i].setBounds(50+i*325, 25,250, 425);
			jpcity[i].setOpaque(false);
			jpcity[i].add(jltemp);
			jpcity[i].add(jpm);
			jpcity[i].addMouseListener(this);
			jp.add(jpcity[i]);
			i++;
		}
		add.setBounds(50+2*325, 25,250, 425);
		setIcon("images/add.png",add);
		add.addMouseListener(this);
		jp.add(add);
		
		while(i<Start.mycity.size()&&i<5)
		{
			jpcity[i]=Start.mycity.get(i).getlittlePanel(0);
			jpcity[i].setOpaque(false);
			jpcity[i].setBounds(700-(i-2)*325, 450, 250, 125);
			jpcity[i].addMouseListener(this);
			this.jp.add(jpcity[i]);
			i++;
		}
		
		back.setBounds(0,500,100,100);
		setIcon("images/back.png",back);
		back.addMouseListener(this);
		jp.add(back);
		
		this.add(jp);
		
		
		String backgroundtemp="images/"+Start.mycity.get(0).weather+".jpg";
		ImageIcon background = new ImageIcon(backgroundtemp);  
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int windowsWedth = 1000;
		int windowsHeight = 600;
		this.setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, windowsWedth, windowsHeight);
		setSize(windowsWedth,windowsHeight);
		Image temp=background.getImage().getScaledInstance(this.getWidth(),this.getHeight(),background.getImage().SCALE_DEFAULT);  
		background=new ImageIcon(temp);  
		JLabel label = new JLabel(background); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		setUndecorated(true);
		label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		jp_under.add(label);
		this.add(jp_under);
		
		setVisible(true); 
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jpcity[0])
		{
			this.dispose();
		}
		if(e.getSource()==jpcity[1])
		{
			new Start(1);
			this.dispose();
		}
		if(e.getSource()==jpcity[2])
		{
			new Start(2);
			this.dispose();
		}
		if(e.getSource()==jpcity[3])
		{
			new Start(3);
			this.dispose();
		}
		if(e.getSource()==jpcity[4])
		{
			new Start(4);
			this.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			setIcon("images/back_up.png",back);
		}
		if(e.getSource()==add)
		{
			setIcon("images/add_up.png",add);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			setIcon("images/back.png",back);
		}
		if(e.getSource()==add)
		{
			setIcon("images/add.png",add);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			this.dispose();
		}
		if(e.getSource()==add)
		{
			if(Start.mycity.size()==5)
			{
				JOptionPane.showMessageDialog(this,"您列表已满，不可添加其他城市！"); 
			}
			else
			{
				Weather temp = null;
				boolean canPass=true;
				do
				{
					String inputValue;
					inputValue = JOptionPane.showInputDialog("请输入城市名:");
					if(inputValue.trim().length()!=0)
					{
						temp=new Weather(inputValue);
						temp.getWeatherData(0);
						if(temp.city!=null)
						{
							Start.mycity.add(temp);
							String input="";
							for(int index=0;index<Start.mycity.size();index++)
							{
								if(index!=0)
								{
									input+="\r\n";
								}
								input+=Start.mycity.get(index).city;
							}
							writeln(input,"citydata.txt");
							if(Start.mycity.size()<=2)
							{
								int i=Start.mycity.size()-1;
								JPanel jptemp=new JPanel();
								JLabel jltemp=new JLabel();
								setLabel(jltemp,Start.mycity.get(i).city,58);
								JPanel jpm=Start.mycity.get(i).getMainPanel(0);
								jptemp.setBounds(50+i*325, 25,250, 425);
								jptemp.setOpaque(false);
								jptemp.add(jltemp);
								jptemp.add(jpm);
								this.jp.add(jptemp);
								this.jp.repaint();
								this.jp.validate();
							}
							else if(Start.mycity.size()<=5)
							{
								int i=Start.mycity.size()-1;
								JPanel jp=Start.mycity.get(i).getlittlePanel(0);
								jp.setOpaque(false);
								jp.setBounds(700-(i-2)*325, 450, 250, 125);
								this.jp.add(jp);
								this.jp.repaint();
								this.jp.validate();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(this,"您输入的城市名有误，请重新输入！"); 
							canPass=false;
						}
					}
					else{
						canPass=false;
					}
				}while(!canPass);
			}
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
	public void writeln(String str,String pathname)
	{
		try {
            File writename = new File(pathname); // 相对路径，如果没有则要建立一个新的output。txt文件  
            writename.createNewFile(); // 创建新文件  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            out.write(str); // \r\n即为换行  
            out.flush();
            out.close(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
