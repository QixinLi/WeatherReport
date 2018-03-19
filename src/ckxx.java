import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ckxx extends JFrame implements MouseListener
{
	JPanel jp=new JPanel();
	JLabel jb_city=new JLabel();
	JLabel jb_xxsm=new JLabel();
	JLabel updatetime=new JLabel();
	JLabel back=new JLabel();
	
	public ckxx(Weather mywea)
	{
		jp.setLayout(null);
		jp.setOpaque(false);
		
		jb_city.setBounds(50,25,200,100);
		setLabel(jb_city,mywea.city,58);
		jp.add(jb_city);
		
		JPanel tempP=mywea.getMainPanel(0);
		tempP.setBounds(50,120,200,300);
		jp.add(tempP);
		
		updatetime.setBounds(550, 15, 300,50);
		setLabel(updatetime,"最后更新时间:"+mywea.updatetime,18);
		jp.add(updatetime);
		
		String weatherdata="<html>大气污染程度:"+mywea.pollution+
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;体感指数:"+mywea.tgzs+
							"<br/>紫外线指数:"+mywea.zwx+
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;空调建议:"+mywea.ktjy+
							"<br/>穿衣指数:"+mywea.cyzs+
							"<br/>穿衣说明:"+mywea.cysm+
							"<br/>体感指数:"+mywea.tgzs+
							"<br/>体感说明:"+mywea.tgsm+
							"<br/>运动指数:"+mywea.ydzs+
							"<br/>运动指数详细说明:"+mywea.ydsm+"</html>";
		this.jb_xxsm.setBounds(350,100,600,500);
		setLabel(this.jb_xxsm,weatherdata,28);
		jp.add(this.jb_xxsm);
		
		back.setBounds(0,500,100,100);
		setIcon("images/back.png",back);
		back.addMouseListener(this);
		jp.add(back);
		
		String backgroundtemp="images/"+mywea.weather+".jpg";
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
		if(e.getSource()==back)
		{
			setIcon("images/back_up.png",back);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			setIcon("images/back.png",back);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			this.dispose();
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
