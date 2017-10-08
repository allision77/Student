package Student;

import java.awt.BorderLayout;
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
 * 修改学生信息类，提供修改学生信息界面
 */

public class ModifyStudentInformation extends JPanel implements ActionListener{
	private StudentPicture studentPicture;
	private HashMap<String, Student> informationTable= null;
	private JTextField numberTField,nameTField,gradeTField,birthdayTField;
	private JComboBox majorComBox;
	private JButton picButton;
	private JRadioButton maleRButton,femaleRButton;
	private JButton beginModifyButton,modifyButton,resetButton;
	private ButtonGroup buttonGroup = null;
	private Student student = null;
	private FileInputStream inOne = null;
	private ObjectInputStream inTwo = null;
	private FileOutputStream outOne = null;
	private ObjectOutputStream outTwo = null;
	private File systemFile,imagePic;
	private JPanel messPanel;//显示基本信息容器
	
 /*
  * 构造方法，初始化修改学生信息界面
  */
	public ModifyStudentInformation(File file) {
		// TODO Auto-generated constructor stub
		systemFile = file;
		studentPicture = new StudentPicture();
		informationTable = new HashMap<String,Student>();
		initMessPanel();
		picButton = new JButton("选择照片");
		picButton.addActionListener(this);
		JPanel picPanel = new JPanel();//选择学生照片容器
		picPanel.add(picButton);
		modifyButton = new JButton("修改");
		resetButton = new JButton("重置");
		modifyButton.addActionListener(this);//添加事件监听对象
		resetButton.addActionListener(this);//添加事件监听对象
		JPanel putButtonJPanel = new JPanel();
		putButtonJPanel.add(modifyButton);
		putButtonJPanel.add(resetButton);
		setLayout(new BorderLayout());
		JSplitPane splitV = new JSplitPane(JSplitPane.VERTICAL_SPLIT,picPanel,studentPicture);
		JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,messPanel,splitV);
		add(splitH,BorderLayout.CENTER);
		add(putButtonJPanel,BorderLayout.SOUTH);
		validate();
		
	}
	/*
	 * 初始化显示学生信息部分界面
	 */

	private void initMessPanel() {
	// TODO Auto-generated method stub
		JLabel numberLabel = new JLabel("(旧)学号",JLabel.CENTER);
		numberTField = new JTextField(5);
		beginModifyButton = new JButton("开始修改");
		beginModifyButton.addActionListener(this);
		numberTField.addActionListener(this);
		Box numberBox = Box.createHorizontalBox();//建立水平box
		numberBox.add(numberLabel);
		numberBox.add(numberTField);
		numberBox.add(beginModifyButton);
		
		JLabel nameLabel = new JLabel("（新）名字：",JLabel.CENTER);
		nameTField = new JTextField(5);
		Box nameBox = Box.createHorizontalBox();//建立水平box
		nameBox.add(nameLabel);
		nameBox.add(nameTField);
		JLabel sexLabel = new JLabel("(新)性别：",JLabel.CENTER);
		maleRButton = new JRadioButton("男",true);
		femaleRButton = new JRadioButton("女",false);
		buttonGroup = new ButtonGroup();
		Box sexBox = Box.createHorizontalBox();//建立水平box
		sexBox.add(sexLabel);
		sexBox.add(maleRButton);
		sexBox.add(femaleRButton);
		JLabel majorJLabel = new JLabel("(新)专业：",JLabel.CENTER);
		majorComBox = new JComboBox<String>();
		try {
			//从文件中读入专业名称，加入到组合框中
			FileReader fileReader = new FileReader("专业.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s = null;
			int i = 0;
			while((s=bufferedReader.readLine())!=null )
				majorComBox.addItem(s);
				fileReader.close();
				bufferedReader.close();
				
		} catch (IOException exp) {
			// TODO: handle exception
			//如果有异常，将数学和计算机科学与技术加入到组合框中
			majorComBox.addItem("会计基础");
			majorComBox.addItem("计算机科学与技术");
			majorComBox.addItem("通信工程");
		}
		Box majorBox = Box.createHorizontalBox();//建立水平box
		majorBox.add(majorJLabel);
		majorBox.add(majorComBox);
		
		JLabel gradeLabel = new JLabel("(新)年级：",JLabel.CENTER);
		gradeTField = new JTextField(5);
		Box gradeBox =Box.createHorizontalBox();//建立水平box
		gradeBox.add(gradeLabel);
		gradeBox.add(gradeTField);
		
		JLabel birthdayLabel= new JLabel("(新)出生：",JLabel.CENTER);
		birthdayTField = new JTextField(5);
		Box birthdayBox = Box.createHorizontalBox();//建立水平box
		birthdayBox.add(birthdayLabel);
		birthdayBox.add(birthdayTField);
		
		Box boxH = Box.createVerticalBox();//建立垂直box
		boxH.add(numberBox);
		boxH.add(nameBox);
		boxH.add(sexBox);
		boxH.add(majorBox);
		boxH.add(gradeBox);
		boxH.add(birthdayBox);
		
		boxH.add(Box.createVerticalGlue());//添加垂直胶水
		messPanel = new JPanel();
		messPanel.add(boxH);
		
}
	/*
	 * 当点击开始修改按钮，修改按钮，选择照片按钮和在学号文本框中按回车时执行的操作
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==beginModifyButton || e.getSource()==numberTField){
			String number = "";
			imagePic = null;
			student = null;
			number = numberTField.getText();
			if(number.length()>0){
				//输入了学号
				try {
					inOne = new FileInputStream(systemFile);
					inTwo = new ObjectInputStream(inOne);
					informationTable = (HashMap<String, Student>) inTwo.readObject();
					inOne.close();
					inTwo.close();
					
					
				} catch (Exception ee) {
					// TODO: handle exception
				}
				if(informationTable.containsKey(number)){
					//该学生存在
					modifyButton.setEnabled(true);
					picButton.setEnabled(true);
					student = informationTable.get(number);
					nameTField.setText(student.getName());
					if(student.getSex().equals("男"))maleRButton.setSelected(true);
					else
						femaleRButton.setSelected(true);
					gradeTField.setText(student.getGrade());
					birthdayTField.setText(student.getBirther());
					imagePic = student.getImagePic();
					studentPicture.setImageFile(imagePic);
					studentPicture.repaint();
				}else {
					//输入学号不存在
					modifyButton.setEnabled(false);
					picButton.setEnabled(false);
					String warning = "该学号不存在";
					JOptionPane.showMessageDialog(this,warning, "警告",JOptionPane.WARNING_MESSAGE);
					clearMessage();
				}
			}else {
				//没有输入学号
				modifyButton.setEnabled(false);
				picButton.setEnabled(false);
				String warning = "请输入学号";
				JOptionPane.showMessageDialog(this,warning, "警告",JOptionPane.WARNING_MESSAGE);
				clearMessage();
			}
		}else if (e.getSource()==modifyButton) {
			//按下修改按钮
			String number = "";
			number = numberTField.getText();
			if(number.length()>0){
				//输入了学号
				try {
					inOne = new FileInputStream(systemFile);
					inTwo = new ObjectInputStream(inOne);
					informationTable = (HashMap<String, Student>) inTwo.readObject();
					inOne.close();
					inTwo.close();
					
				} catch (Exception ee) {
					// TODO: handle exception
				}
				if(informationTable.containsKey(number)){
					//该学号存在，就修改
					String question = "确定修改该生的信息吗？";
					JOptionPane.showMessageDialog(this, question,"警告",JOptionPane.QUESTION_MESSAGE);
					String m = "基本信息将被修改";
					int ok = JOptionPane.showConfirmDialog(this, m,"确认",JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						//修改，获得修改后的信息
						String name = nameTField.getText();
						if(name.length()==0)
							name = student.getName();
							String sex = null;
							if(maleRButton.isSelected())
								sex = maleRButton.getText();
							else
								sex = femaleRButton.getText();
							String major = (String) majorComBox.getSelectedItem();
							if(major==null)
								major = student.getMajor();
								String grade = gradeTField.getText();
								if(grade.length()==0)
									grade = student.getGrade();
								String birth = birthdayTField.getText();
								if(birth.length()==0)
									birth = student.getBirther();
								if(imagePic==null)
									imagePic = student.getImagePic();
								Student stu = new Student();
								stu.setNumber(number);
								stu.setName(name);
								stu.setMajor(major);
								stu.setBirther(birth);
								stu.setGrade(grade);
								stu.setSex(sex);
								stu.setImagePic(imagePic);
								try {
									//保存修改后的信息
									outOne = new FileOutputStream(systemFile);
									outTwo = new ObjectOutputStream(outOne);
									informationTable.put(number, stu);
									outTwo.writeObject(informationTable);
									outTwo.close();
									outOne.close();
									clearMessage();
									
								} catch (Exception ee) {
									// TODO: handle exception
								} 
								modifyButton.setEnabled(false);
								picButton.setEnabled(false);
								}
								else if (ok==JOptionPane.NO_OPTION) {
									modifyButton.setEnabled(true);
									picButton.setEnabled(true);
									
								}
						}else {
							//该学号不存在
							String wraning = "没有该学号学生的基本信息";
							JOptionPane.showMessageDialog(this, wraning,"警告",JOptionPane.WARNING_MESSAGE);
							modifyButton.setEnabled(false);
							picButton.setEnabled(false);
							clearMessage();
						}
						
					}else {
						//学号为空
						String warning = "必须输入学号";
						JOptionPane.showMessageDialog(this, warning,"警告",JOptionPane.WARNING_MESSAGE);
						modifyButton.setEnabled(false);
						clearMessage();
						picButton.setEnabled(false);
					}
				}else if (e.getSource()==picButton) {
					JFileChooser chooer = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Images", "jpg","gif");
					chooer.setFileFilter(filter);
					int state = chooer.showOpenDialog(null);
					File choiceFile = chooer.getSelectedFile();
					if(choiceFile!=null && state==JFileChooser.APPROVE_OPTION){
						picButton.setText("重新选择");
						imagePic = choiceFile;
						studentPicture.setImageFile(imagePic);
						studentPicture.repaint();
						
					}
					
				}else if (e.getSource()==resetButton) {
					clearMessage();
					modifyButton.setEnabled(false);
					picButton.setEnabled(false);
					
				}
				}
			/*
			 * 将显示信息清空
			 */
	public  void clearMessage() {
		// TODO Auto-generated method stub
		numberTField.setText(null);
		nameTField.setText(null);
		gradeTField.setText(null);
		birthdayTField.setText(null);
		picButton.setText("选择");
		imagePic = null;
		studentPicture.setImageFile(imagePic);
		studentPicture.repaint();
		
	}

}
