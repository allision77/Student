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
 * ѧ��������Ϣ��¼��
 */

public class InputStudentInformation extends JPanel implements ActionListener{
	private Student student = null;//ѧ������
	private StudentPicture studentPicture;//ѧ��ͼ��
	private HashMap<String,Student> informationTable;
	private JTextField numberTField,nameTField,birtherTField,gradeTField;
	private JButton picButton;//ѡ����Ƭ��ť
	private JLabel promptLabel;//��ʾ��Ϣ
	private JComboBox<String> majorComBox;//רҵ�б��
	private JRadioButton maleRButton,famaleRButton;//��ѡ��ť��ѡ���л�Ů
	private ButtonGroup buttongroup = null;
	private JButton inputButton,resetButton;//���밴ť�����ð�ť
	private FileInputStream fileInputStream = null;//�ļ�����������
	private ObjectInputStream objectInputStream = null;//��������������
	private FileOutputStream fileOutputStream = null;//�ļ����������
	private ObjectOutputStream objectOutputStream = null;//�������������
	private File systemFile,imagePic;
	private JPanel putButtonPanel;//¼������ð�ť����
	private JPanel messPanel,picPanel;//������Ϣ����Ƭ������
	
	
	
	/*
	 * ���췽������ʼ��¼�����
	 */

	public InputStudentInformation(File file) {
		// TODO Auto-generated constructor stub
		systemFile = file;
		studentPicture = new StudentPicture();
		informationTable = new HashMap<String,Student>();
		promptLabel = new JLabel("�����������Ϣ",Label.LEFT);
		promptLabel.setFont(new Font("����", Font.BOLD, 13));//������ʾ��Ϣ����
		promptLabel.setForeground(Color.RED);
		promptLabel.setOpaque(true);//����Ϊ��͸��
		promptLabel.setBackground(new Color(216, 224, 231));//���ñ�����ɫ
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
	 * ��ʼ����Ƭ���ֽ���
	 */

	private void initPicPanel() {
		// TODO Auto-generated method stub
		JLabel picLabel = new JLabel("��Ƭ��",JLabel.LEFT);
		picButton = new JButton("ѡ����Ƭ");
		picButton.addActionListener(this);
		picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		picPanel.add(picLabel,BorderLayout.NORTH);
		picPanel.add(studentPicture, BorderLayout.CENTER);
		picPanel.add(picButton, BorderLayout.SOUTH);
		
		
	}
	
	/*
	 * ��ʼ��¼�룬���ð�ť����
	 */

	private void initPutButtonJPanel() {
		// TODO Auto-generated method stub
		inputButton = new JButton("¼��");
		resetButton = new JButton("����");
		inputButton.addActionListener(this);//����¼���������
		resetButton.addActionListener(this);//����¼���������
		putButtonPanel = new JPanel();
		putButtonPanel.setBackground(new Color(216, 224, 231));
		putButtonPanel.add(inputButton);
		putButtonPanel.add(resetButton);
		
		
	}
	/*
	 * ��ʼ����ʾ������Ϣ
	 */

	private void initMessPanel() {
		// TODO Auto-generated method stub
		JLabel numberLabel = new JLabel("ѧ�ţ�",JLabel.CENTER);
		numberTField = new JTextField(5);
		Box numberBox = Box.createHorizontalBox();//���ˮƽbox
		numberBox.add(numberLabel);
		numberBox.add(numberTField);
		JLabel nameLabel = new JLabel("������",JLabel.CENTER);
		nameTField = new JTextField(5);
		Box nameBox = Box.createHorizontalBox();//���ˮƽbox
		nameBox.add(nameLabel);
		nameBox.add(nameTField);
		JLabel sexLabel = new JLabel("�Ա�",JLabel.CENTER);
		maleRButton = new JRadioButton("��",true);
		famaleRButton = new JRadioButton("Ů",false);
		buttongroup = new ButtonGroup();
		buttongroup.add(maleRButton);
		buttongroup.add(famaleRButton);
		Box sexBox = Box.createHorizontalBox();//���ˮƽbox
		sexBox.add(sexLabel);
		sexBox.add(maleRButton);
		sexBox.add(famaleRButton);
		JLabel majorLabel = new JLabel("רҵ",JLabel.CENTER);
		majorComBox = new JComboBox<String>();
		try {
			//���ļ��ж���רҵ���ƣ����뵽��Ͽ���
			FileReader fileReader = new FileReader("רҵ.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s = null;
			int i = 0;
			while((s = bufferedReader.readLine())!=null){
				majorComBox.addItem(s);
				fileReader.close();
				bufferedReader.close();
				
			}
			
			
		} catch (IOException exp) {//������쳣������ѧ�ͼ������ѧ�뼼��������Ͽ���
			// TODO: handle exception
			majorComBox.addItem("�������ѧ�뼼��");
			majorComBox.addItem("���Ӧ��");
			majorComBox.addItem("ͨ�Ź���");
		}
		Box majorBox = Box.createHorizontalBox();//���ˮƽbox
		majorBox.add(majorLabel);
		majorBox.add(majorComBox);
		JLabel gradeLabel = new JLabel("�꼶��",JLabel.CENTER);
		gradeTField = new JTextField(5);
		Box gradeBox = Box.createHorizontalBox();//���ˮƽbox
		gradeBox.add(gradeLabel);
		gradeBox.add(gradeTField);
		JLabel birtherLabel = new JLabel("������",JLabel.CENTER);
		birtherTField = new JTextField(5);
		Box birtherBox = Box.createHorizontalBox();//���ˮƽbox
		birtherBox.add(birtherLabel);
		birtherBox.add(birtherTField);
		
		Box boxH = Box.createVerticalBox();
		boxH.add(numberBox);
		boxH.add(nameBox);
		boxH.add(birtherBox);
		boxH.add(gradeBox);
		boxH.add(sexBox);
		boxH.add(majorBox);
		boxH.add(Box.createVerticalGlue());//��Ӵ�ֱ��ˮ
		messPanel = new JPanel();
		messPanel.add(boxH);
	}
	
	/*
	 * �����¼�밴ť�����ð�ť��ѡ����Ƭ��ťʱִ�еĶ���
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==inputButton){//������¼�밴ť
			String number = "";
			number = numberTField.getText();//��ȡѧ����Ϣ
			if(number.length()>0){
				try {
					//���ļ��ж�ȡ��Ϣ
					fileInputStream = new FileInputStream(systemFile);
					objectInputStream = new ObjectInputStream(fileInputStream);
					informationTable = (HashMap<String, Student>) objectInputStream.readObject();
					fileInputStream.close();
					objectInputStream.close();
					
					
				} catch (Exception ee) {
					// TODO: handle exception
				}
				if(informationTable.containsKey(number)){
					//�����ѧ�Ŵ��ڣ���ʾ������Ϣ
					String warning = "��ѧ���Ļ�����Ϣ�Ѵ��ڣ��뵽�޸�ҳ���޸ģ�";
					JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);
				}else {
					//�����Ϣ�����ڣ�����Ϣ����
					String m = "ȷ��¼�������Ϣ��";
					int ok = JOptionPane.showConfirmDialog(this, m,"ȷ��",JOptionPane.YES_NO_OPTION,
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
							//����Ϣ�������ļ���
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
				String warning = "��������ѧ�ţ�";
				JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);
				
			}
			
		}else if (e.getSource()==picButton) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg","gif");
			chooser.setFileFilter(filter);
			int state = chooser.showOpenDialog(null);
			File choiceFile = chooser.getSelectedFile();
			if(choiceFile!=null&&state==JFileChooser.APPROVE_OPTION){
				picButton.setText("������ѡ��");
				imagePic = choiceFile;
				studentPicture.setImageFile(imagePic);
				studentPicture.repaint();//��ʾ��Ƭ
			}
			
			
		}else if (e.getSource()==resetButton) {
			clearMessage();
			
		}
		
		
	}
	/*
	 * ����ʾ����Ϣ���
	 */

	void clearMessage() {
		// TODO Auto-generated method stub
		numberTField.setText(null);
		nameTField.setText(null);
		gradeTField.setText(null);
		birtherTField.setText(null);
		picButton.setText("�ϴ���Ƭ");
		imagePic = null;
		studentPicture.setImageFile(imagePic);
		studentPicture.repaint();
		
	}

}
