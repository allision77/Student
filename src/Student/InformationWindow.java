package Student;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.*;

/*
 * 启动系统，完成主界面的初始化
 */

public class InformationWindow extends JFrame implements ActionListener{
	private InputStudentInformation inputstudentinformation;//录入信息
	private ModifyStudentInformation modifystudentinformation;//修改信息
	private QueryStudentInformation querystudentinformation;//查询信息
	private DeletStudentInformation deletstudentinformation;//删除信息
	
	private JMenuBar bar;//菜单栏
	private JMenu fileMenu;
	private JMenuItem inputItem,modifyItem,queryItem,deletItem,welcomeItem;//各菜单项
	private HashMap<String,Student> informationTable = null;//学生信息表
	private File file = null;
	private CardLayout card = null;
	private JLabel label = null;
	private JPanel pCenter;
	
	/*
	 * 构造方法，初始化界面
	 */
	public InformationWindow() throws IOException {
		// TODO Auto-generated constructor stub
		informationTable = new HashMap<String,Student>();
		initFrame();
		setVisible(true);
		setBounds(100, 50, 380, 350);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int n = JOptionPane.showConfirmDialog(null, "确认退出吗？",
						"确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
				if(n==JOptionPane.YES_NO_OPTION)
					System.exit(0);
				}
				
			
		});
		setResizable(false);
		validate();
		
	}
	/*
	 * 初始化主界面的各个组件
	 */
	private void initFrame() throws IOException {
		// TODO Auto-generated method stub
		inputItem = new JMenuItem("录入");
		modifyItem = new JMenuItem("修改");
		queryItem = new JMenuItem("查询");
		deletItem = new JMenuItem("删除");
		welcomeItem = new JMenuItem("返回首页");
		bar = new JMenuBar();
		fileMenu = new JMenu("菜单选项");
		fileMenu.add(inputItem);
		fileMenu.add(modifyItem);
		fileMenu.add(queryItem);
		fileMenu.add(deletItem);
		fileMenu.add(welcomeItem);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label = new JLabel("学籍管理系统",JLabel.CENTER);
		label.setIcon(new ImageIcon("welcome.jpg"));
		label.setFont(new Font("隶书", Font.BOLD, 36));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		informationTable = new HashMap<String,Student>();
		inputItem.addActionListener(this);
		modifyItem.addActionListener(this);
		queryItem.addActionListener(this);
		deletItem.addActionListener(this);
		welcomeItem.addActionListener(this);
		
		card = new CardLayout();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		file = new File("基本信息.txt");
		if(!file.exists()){
			try{
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(informationTable);
			objectOut.close();
			out.close();
			
		}catch (IOException e) {
			// TODO: handle exception
		}
	
		}
	    inputstudentinformation = new InputStudentInformation(file);
		modifystudentinformation = new ModifyStudentInformation(file);
		querystudentinformation = new QueryStudentInformation(file);
		deletstudentinformation = new DeletStudentInformation(file);
		pCenter.add("欢迎界面",label);
	    pCenter.add("录入界面",inputstudentinformation);
	    pCenter.add("修改界面",modifystudentinformation);
    	pCenter.add("查询界面",querystudentinformation);
		pCenter.add("删除界面",deletstudentinformation);
		add(pCenter,BorderLayout.CENTER);
		
	}
	/*
	 * 点击录入，修改，查询，删除，欢迎菜单时进行的操作
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==inputItem){
			
			inputstudentinformation.clearMessage();
			card.show(pCenter, "录入界面");
			
		}else if (e.getSource()==modifyItem) {
			modifystudentinformation.clearMessage();
			card.show(pCenter, "修改界面");
			
		}else if(e.getSource()==queryItem){
			querystudentinformation.clearMessage();
			card.show(pCenter, "查询界面");
			
		}else if (e.getSource()==deletItem) {
			card.show(pCenter, "删除界面");
		}else if(e.getSource()==welcomeItem){
				card.show(pCenter, "欢迎界面");
			
		}
}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new InformationWindow();

	}
	

	
}
