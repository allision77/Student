package Student;

import java.awt.*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * ������ʾѧ����Ƭ
 */
public class StudentPicture extends JPanel{
	
	private File imageFile;//���ͼ���ļ�������
	private Toolkit tool;//���𴴽�image����
	
	
	public StudentPicture() {
		// TODO Auto-generated constructor stub
		tool = getToolkit();
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
	}
	/*
	 * ����imagefile����
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
		repaint();
	}
	/*
	 * ��ʾ��Ƭ
	 */
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int w = getBounds().width;
		int h = getBounds().height;
		if(imageFile!=null){
			//���ͼ��
			Image image = tool.getImage(imageFile.getAbsolutePath());
			g.drawImage(image, 0, 0, w,h,this);//����ͼ��
			
		}else {
			g.drawString("û��ѡ����Ƭͼ��", 20, 30);
		}
		
	}
	

}
