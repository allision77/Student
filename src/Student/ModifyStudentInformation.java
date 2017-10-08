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
 * �޸�ѧ����Ϣ�࣬�ṩ�޸�ѧ����Ϣ����
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
	private JPanel messPanel;//��ʾ������Ϣ����
	
 /*
  * ���췽������ʼ���޸�ѧ����Ϣ����
  */
	public ModifyStudentInformation(File file) {
		// TODO Auto-generated constructor stub
		systemFile = file;
		studentPicture = new StudentPicture();
		informationTable = new HashMap<String,Student>();
		initMessPanel();
		picButton = new JButton("ѡ����Ƭ");
		picButton.addActionListener(this);
		JPanel picPanel = new JPanel();//ѡ��ѧ����Ƭ����
		picPanel.add(picButton);
		modifyButton = new JButton("�޸�");
		resetButton = new JButton("����");
		modifyButton.addActionListener(this);//����¼���������
		resetButton.addActionListener(this);//����¼���������
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
	 * ��ʼ����ʾѧ����Ϣ���ֽ���
	 */

	private void initMessPanel() {
	// TODO Auto-generated method stub
		JLabel numberLabel = new JLabel("(��)ѧ��",JLabel.CENTER);
		numberTField = new JTextField(5);
		beginModifyButton = new JButton("��ʼ�޸�");
		beginModifyButton.addActionListener(this);
		numberTField.addActionListener(this);
		Box numberBox = Box.createHorizontalBox();//����ˮƽbox
		numberBox.add(numberLabel);
		numberBox.add(numberTField);
		numberBox.add(beginModifyButton);
		
		JLabel nameLabel = new JLabel("���£����֣�",JLabel.CENTER);
		nameTField = new JTextField(5);
		Box nameBox = Box.createHorizontalBox();//����ˮƽbox
		nameBox.add(nameLabel);
		nameBox.add(nameTField);
		JLabel sexLabel = new JLabel("(��)�Ա�",JLabel.CENTER);
		maleRButton = new JRadioButton("��",true);
		femaleRButton = new JRadioButton("Ů",false);
		buttonGroup = new ButtonGroup();
		Box sexBox = Box.createHorizontalBox();//����ˮƽbox
		sexBox.add(sexLabel);
		sexBox.add(maleRButton);
		sexBox.add(femaleRButton);
		JLabel majorJLabel = new JLabel("(��)רҵ��",JLabel.CENTER);
		majorComBox = new JComboBox<String>();
		try {
			//���ļ��ж���רҵ���ƣ����뵽��Ͽ���
			FileReader fileReader = new FileReader("רҵ.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s = null;
			int i = 0;
			while((s=bufferedReader.readLine())!=null )
				majorComBox.addItem(s);
				fileReader.close();
				bufferedReader.close();
				
		} catch (IOException exp) {
			// TODO: handle exception
			//������쳣������ѧ�ͼ������ѧ�뼼�����뵽��Ͽ���
			majorComBox.addItem("��ƻ���");
			majorComBox.addItem("�������ѧ�뼼��");
			majorComBox.addItem("ͨ�Ź���");
		}
		Box majorBox = Box.createHorizontalBox();//����ˮƽbox
		majorBox.add(majorJLabel);
		majorBox.add(majorComBox);
		
		JLabel gradeLabel = new JLabel("(��)�꼶��",JLabel.CENTER);
		gradeTField = new JTextField(5);
		Box gradeBox =Box.createHorizontalBox();//����ˮƽbox
		gradeBox.add(gradeLabel);
		gradeBox.add(gradeTField);
		
		JLabel birthdayLabel= new JLabel("(��)������",JLabel.CENTER);
		birthdayTField = new JTextField(5);
		Box birthdayBox = Box.createHorizontalBox();//����ˮƽbox
		birthdayBox.add(birthdayLabel);
		birthdayBox.add(birthdayTField);
		
		Box boxH = Box.createVerticalBox();//������ֱbox
		boxH.add(numberBox);
		boxH.add(nameBox);
		boxH.add(sexBox);
		boxH.add(majorBox);
		boxH.add(gradeBox);
		boxH.add(birthdayBox);
		
		boxH.add(Box.createVerticalGlue());//��Ӵ�ֱ��ˮ
		messPanel = new JPanel();
		messPanel.add(boxH);
		
}
	/*
	 * �������ʼ�޸İ�ť���޸İ�ť��ѡ����Ƭ��ť����ѧ���ı����а��س�ʱִ�еĲ���
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
				//������ѧ��
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
					//��ѧ������
					modifyButton.setEnabled(true);
					picButton.setEnabled(true);
					student = informationTable.get(number);
					nameTField.setText(student.getName());
					if(student.getSex().equals("��"))maleRButton.setSelected(true);
					else
						femaleRButton.setSelected(true);
					gradeTField.setText(student.getGrade());
					birthdayTField.setText(student.getBirther());
					imagePic = student.getImagePic();
					studentPicture.setImageFile(imagePic);
					studentPicture.repaint();
				}else {
					//����ѧ�Ų�����
					modifyButton.setEnabled(false);
					picButton.setEnabled(false);
					String warning = "��ѧ�Ų�����";
					JOptionPane.showMessageDialog(this,warning, "����",JOptionPane.WARNING_MESSAGE);
					clearMessage();
				}
			}else {
				//û������ѧ��
				modifyButton.setEnabled(false);
				picButton.setEnabled(false);
				String warning = "������ѧ��";
				JOptionPane.showMessageDialog(this,warning, "����",JOptionPane.WARNING_MESSAGE);
				clearMessage();
			}
		}else if (e.getSource()==modifyButton) {
			//�����޸İ�ť
			String number = "";
			number = numberTField.getText();
			if(number.length()>0){
				//������ѧ��
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
					//��ѧ�Ŵ��ڣ����޸�
					String question = "ȷ���޸ĸ�������Ϣ��";
					JOptionPane.showMessageDialog(this, question,"����",JOptionPane.QUESTION_MESSAGE);
					String m = "������Ϣ�����޸�";
					int ok = JOptionPane.showConfirmDialog(this, m,"ȷ��",JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						//�޸ģ�����޸ĺ����Ϣ
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
									//�����޸ĺ����Ϣ
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
							//��ѧ�Ų�����
							String wraning = "û�и�ѧ��ѧ���Ļ�����Ϣ";
							JOptionPane.showMessageDialog(this, wraning,"����",JOptionPane.WARNING_MESSAGE);
							modifyButton.setEnabled(false);
							picButton.setEnabled(false);
							clearMessage();
						}
						
					}else {
						//ѧ��Ϊ��
						String warning = "��������ѧ��";
						JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);
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
						picButton.setText("����ѡ��");
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
			 * ����ʾ��Ϣ���
			 */
	public  void clearMessage() {
		// TODO Auto-generated method stub
		numberTField.setText(null);
		nameTField.setText(null);
		gradeTField.setText(null);
		birthdayTField.setText(null);
		picButton.setText("ѡ��");
		imagePic = null;
		studentPicture.setImageFile(imagePic);
		studentPicture.repaint();
		
	}

}
