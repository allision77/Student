package Student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * 学生基本信息的录入
 */

public class InputStudentInformation extends JPanel implements ActionListener{
	private Student student = null;//学生对象
	private StudentPicture studentPicture;//学生图像
	private HashMap<String,Student> informationTable;
	private JTextField numberTField,nameTField,birtherTField,gradeTField;
	private JButton picButton;//选择照片按钮
	private JLabel promptLabel;//提示信息
	private JComboBox<String> majorComBox;//专业列表框
	private JRadioButton maleRButton,famaleRButton;//单选按钮，选择男或女
	private ButtonGroup buttongroup = null;
	private JButton inputButton,resetButton;//输入按钮，重置按钮
	private FileInputStream fileInputStream = null;//文件输入流对象
	private ObjectInputStream objectInputStream = null;//对象输入流对象
	private FileOutputStream fileOutputStream = null;//文件输出流对象
	private ObjectOutputStream objectOutputStream = null;//对象输出流对象
	private File systemFile,imagePic;
	private JPanel putButtonPanel;//录入和重置按钮容器
	private JPanel messPanel,picPanel;//基本信息和照片的容器
	
	
	
	/*
	 * 构造方法，初始化录入界面
	 */

	public InputStudentInformation(File file) {
		// TODO Auto-generated constructor stub
		systemFile = file;
		studentPicture = new StudentPicture();
		informationTable = new HashMap<String,Student>();
		promptLabel = new JLabel("请输入个人信息",Label.LEFT);
		promptLabel.setFont(new Font("宋体", Font.BOLD, 13));//设置提示信息字体
		promptLabel.setForeground(Color.RED);
		promptLabel.setOpaque(true);//设置为不透明
		promptLabel.setBackground(new Color(216, 224, 231));//设置背景颜色
		initMessPanel();
		initPutButtonJPanel();
		initPicPanel();
		setLayout(new BorderLayout());
		JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				messPanel,picButton);
		add(promptLabel,BorderLayout.NORTH);
		add(splitH, BorderLayout.CENTER);
		add(putButtonPanel, BorderLayout.SOUTH);
		validate();
		
		
	}
	
	/*
	 * 初始化照片部分界面
	 */

	private void initPicPanel() {
		// TODO Auto-generated method stub
		JLabel picLabel = new JLabel("照片：",JLabel.LEFT);
		picButton = new JButton("选择照片");
		picButton.addActionListener(this);
		picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		picPanel.add(picLabel,BorderLayout.NORTH);
		picPanel.add(studentPicture, BorderLayout.CENTER);
		picPanel.add(picButton, BorderLayout.SOUTH);
		
		
	}
	
	/*
	 * 初始化录入，重置按钮界面
	 */

	private void initPutButtonJPanel() {
		// TODO Auto-generated method stub
		inputButton = new JButton("录入");
		resetButton = new JButton("重置");
		inputButton.addActionListener(this);//添加事件监听对象
		resetButton.addActionListener(this);//添加事件监听对象
		putButtonPanel = new JPanel();
		putButtonPanel.setBackground(new Color(216, 224, 231));
		putButtonPanel.add(inputButton);
		putButtonPanel.add(resetButton);
		
		
	}
	/*
	 * 初始化显示界面信息
	 */

	private void initMessPanel() {
		// TODO Auto-generated method stub
		JLabel numberLabel = new JLabel("学号：",JLabel.CENTER);
		numberTField = new JTextField(5);
		Box numberBox = Box.createHorizontalBox();//添加水平box
		numberBox.add(numberLabel);
		numberBox.add(numberTField);
		JLabel nameLabel = new JLabel("姓名：",JLabel.CENTER);
		nameTField = new JTextField(5);
		Box nameBox = Box.createHorizontalBox();//添加水平box
		nameBox.add(nameLabel);
		nameBox.add(nameTField);
		JLabel sexLabel = new JLabel("性别：",JLabel.CENTER);
		maleRButton = new JRadioButton("男",true);
		famaleRButton = new JRadioButton("女",false);
		buttongroup = new ButtonGroup();
		buttongroup.add(maleRButton);
		buttongroup.add(famaleRButton);
		Box sexBox = Box.createHorizontalBox();//添加水平box
		sexBox.add(sexLabel);
		sexBox.add(maleRButton);
		sexBox.add(famaleRButton);
		JLabel majorLabel = new JLabel("专业",JLabel.CENTER);
		majorComBox = new JComboBox<String>();
		try {
			//从文件中读入专业名称，加入到组合框中
			FileReader fileReader = new FileReader("专业.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s = null;
			int i = 0;
			while((s = bufferedReader.readLine())!=null){
				majorComBox.addItem(s);
				fileReader.close();
				bufferedReader.close();
				
			}
			
			
		} catch (IOException exp) {//如果有异常，将数学和计算机科学与技术加入组合框中
			// TODO: handle exception
			majorComBox.addItem("计算机科学与技术");
			majorComBox.addItem("会计应用");
			majorComBox.addItem("通信工程");
		}
		Box majorBox = Box.createHorizontalBox();//添加水平box
		majorBox.add(majorLabel);
		majorBox.add(majorComBox);
		JLabel gradeLabel = new JLabel("年级：",JLabel.CENTER);
		gradeTField = new JTextField(5);
		Box gradeBox = Box.createHorizontalBox();//添加水平box
		gradeBox.add(gradeLabel);
		gradeBox.add(gradeTField);
		JLabel birtherLabel = new JLabel("出生：",JLabel.CENTER);
		birtherTField = new JTextField(5);
		Box birtherBox = Box.createHorizontalBox();//添加水平box
		birtherBox.add(birtherLabel);
		birtherBox.add(birtherTField);
		
		Box boxH = Box.createVerticalBox();
		boxH.add(numberBox);
		boxH.add(nameBox);
		boxH.add(birtherBox);
		boxH.add(gradeBox);
		boxH.add(sexBox);
		boxH.add(majorBox);
		boxH.add(Box.createVerticalGlue());//添加垂直胶水
		messPanel = new JPanel();
		messPanel.add(boxH);
	}
	
	/*
	 * 当点击录入按钮，重置按钮和选择照片按钮时执行的动作
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==inputButton){//如果点击录入按钮
			String number = "";
			number = numberTField.getText();//读取学号信息
			if(number.length()>0){
				try {
					//从文件中读取信息
					fileInputStream = new FileInputStream(systemFile);
					objectInputStream = new ObjectInputStream(fileInputStream);
					informationTable = (HashMap<String, Student>) objectInputStream.readObject();
					fileInputStream.close();
					objectInputStream.close();
					
					
				} catch (Exception ee) {
					// TODO: handle exception
				}
				if(informationTable.containsKey(number)){
					//如果该学号存在，显示警告信息
					String warning = "该学生的基本信息已存在，请到修改页面修改！";
					JOptionPane.showMessageDialog(this, warning,"警告",JOptionPane.WARNING_MESSAGE);
				}else {
					//如果信息不存在，将信息保存
					String m = "确定录入该生信息？";
					int ok = JOptionPane.showConfirmDialog(this, m,"确认",JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_NO_OPTION){
						String name = nameTField.getText();
						String major = (String) majorComBox.getSelectedItem();
						String grade = gradeTField.getText();
						String birth = birtherTField.getText();
						String sex = null;
						if(maleRButton.isSelected())
							sex = maleRButton.getText();
						else
							sex = famaleRButton.getText();
						student = new Student();
						student.setNumber(number);
						student.setName(name);
						student.setImagePic(imagePic);
						student.setGrade(grade);
						student.setMajor(major);
						student.setSex(sex);
						student.setBirther(birth);
		//System.out.println(number+""+name+""+major+""+grade+""+birth);
						try {
							//将信息保存在文件中
							fileOutputStream = new FileOutputStream(systemFile);
							objectOutputStream = new ObjectOutputStream(fileOutputStream);
							informationTable.put(number, student);
							objectOutputStream.writeObject(informationTable);
							objectOutputStream.close();
							fileOutputStream.close();
							clearMessage();
							
						} catch (Exception ee) {
							// TODO: handle exception
						}
						
								
					}
				}
			}else {
				String warning = "必须输入学号！";
				JOptionPane.showMessageDialog(this, warning,"警告",JOptionPane.WARNING_MESSAGE);
				
			}
			
		}else if (e.getSource()==picButton) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg","gif");
			chooser.setFileFilter(filter);
			int state = chooser.showOpenDialog(null);
			File choiceFile = chooser.getSelectedFile();
			if(choiceFile!=null&&state==JFileChooser.APPROVE_OPTION){
				picButton.setText("请重新选择");
				imagePic = choiceFile;
				studentPicture.setImageFile(imagePic);
				studentPicture.repaint();//显示照片
			}
			
			
		}else if (e.getSource()==resetButton) {
			clearMessage();
			
		}
		
		
	}
	/*
	 * 将显示的信息清空
	 */

	void clearMessage() {
		// TODO Auto-generated method stub
		numberTField.setText(null);
		nameTField.setText(null);
		gradeTField.setText(null);
		birtherTField.setText(null);
		picButton.setText("上传照片");
		imagePic = null;
		studentPicture.setImageFile(imagePic);
		studentPicture.repaint();
		
	}

}
