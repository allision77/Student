package Student;

import java.awt.*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * 用来显示学生照片
 */
public class StudentPicture extends JPanel{
	
	private File imageFile;//存放图像文件的引用
	private Toolkit tool;//负责创建image对象
	
	
	public StudentPicture() {
		// TODO Auto-generated constructor stub
		tool = getToolkit();
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
	}
	/*
	 * 设置imagefile对象
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
		repaint();
	}
	/*
	 * 显示照片
	 */
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int w = getBounds().width;
		int h = getBounds().height;
		if(imageFile!=null){
			//获得图像
			Image image = tool.getImage(imageFile.getAbsolutePath());
			g.drawImage(image, 0, 0, w,h,this);//绘制图像
			
		}else {
			g.drawString("没有选择照片图像", 20, 30);
		}
		
	}
	

}
