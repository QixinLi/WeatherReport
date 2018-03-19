/* 
 *重写这个类来实现半透明的JPanel  
 */  
import java.awt.AlphaComposite;  
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;  
  
import javax.swing.JPanel;  
  
public class TranslucenceJPanel extends JPanel implements MouseListener{  
      
    private BufferedImage background;  
      
    private float transparency=0;  
      
    public TranslucenceJPanel(){  
    	this.addMouseListener(this);
    }  
      
      
      
    /**这个方法用来设置JPanel的透明度 
     *  
     * @param transparency:透明度 
     *  
     * @return void 
     */  
    public void setTransparent(float transparency) {  
        this.transparency = transparency;  
    }  
      
    @Override  
    protected void paintComponent(Graphics g){  
        super.paintComponent(g);  
          
            Graphics2D graphics2d = (Graphics2D) g.create();  
              
            graphics2d.setComposite(AlphaComposite.SrcOver.derive(transparency));  
              
              
            graphics2d.setColor(getBackground());  
            graphics2d.fillRect(0, 0, getWidth(), getHeight());  
              
//          graphics2d.drawImage(background, 0, 0, getWidth(), getHeight(), 46, 114, 315, 521, this);  
              
            graphics2d.dispose();  
    }



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this)
		{
			this.setTransparent((float) 0.2);
			this.repaint();
		}
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this)
		{
			this.setTransparent(0);
			this.repaint();
		}
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}  
      
}  
