package Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * ɾ��ѧ��������Ϣ
 */
public class DeletStudentInformation extends JPanel implements ActionListener{
	private HashMap<String, Student> informationTable = null;//������Ϣ��
	private JTextField numberTFiele,nameTField,majorTField,gradeTField,birthdayTField;
	private JRadioButton maleRadioButton,femaleRadioButton;
	private JButton deleteButton;//ɾ����ť
	private ButtonGroup buttonGroup = null;
	private FileInputStream fileInputStream = null;
	private ObjectInputStream objectInputStream = null;
	private FileOutputStream fileOutputStream = null;
	private ObjectOutputStream objectOutputStream = null;
	private File SystemFile = null;
	private JPanel messPanel;//��ʾ������Ϣ������

	
	/*
	 * ���췽������ʼ��ɾ��ѧ����Ϣ����
	 */
	public DeletStudentInformation(File file) {
		// TODO Auto-generated constructor stub
		SystemFile = file;
		informationTable = new HashMap<String,Student>();
		initMessPanel();
		add(messPanel);
		validate();
	}
	
	/*
	 * ��ʼ����ʾѧ����Ϣ���ֽ���
	 */

	private void initMessPanel() {
		// TODO Auto-generated method stub
		JLabel deleteLabel = new JLabel("ѧ�ţ�",JLabel.CENTER);
		numberTFiele = new JTextField(10);
		deleteButton = new JButton("ɾ��");
		deleteButton.addActionListener(this);
		numberTFiele.addActionListener(this);
		Box box1 = Box.createHorizontalBox();
		box1.add(deleteLabel);
		box1.add(numberTFiele);
		box1.add(deleteButton);
		JLabel nameLabel = new JLabel("������",JLabel.CENTER);
		nameTField = new JTextField(10);
		nameTField.setEditable(false);
		Box box2 = Box.createHorizontalBox();
		box2.add(nameLabel);
		box2.add(nameTField);
		JLabel sexLabel = new JLabel("�Ա�",JLabel.CENTER);
		maleRadioButton = new JRadioButton("��",false);
		femaleRadioButton = new JRadioButton("Ů",false);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRadioButton);
		buttonGroup.add(femaleRadioButton);
		Box box3 = Box.createHorizontalBox();
		box3.add(sexLabel);
		box3.add(maleRadioButton);
		box3.add(femaleRadioButton);
		
		JLabel majorLabel = new JLabel("רҵ��",JLabel.CENTER);
		majorTField = new JTextField(10);
		majorTField.setEditable(false);
		Box box4 = Box.createHorizontalBox();
		box4.add(majorLabel);
		box4.add(majorTField);
		
		JLabel gradeLabel = new JLabel("�꼶��",JLabel.CENTER);
		gradeTField = new JTextField(10);
		gradeTField.setEditable(false);
		Box box5 = Box.createHorizontalBox();
		box5.add(gradeLabel);
		box5.add(gradeTField);
		
		JLabel birthdayLabel = new JLabel("������",JLabel.CENTER);
		birthdayTField = new JTextField(10);
		birthdayTField.setEditable(false);
		Box box6 = Box.createHorizontalBox();
		box6.add(birthdayLabel);
		box6.add(birthdayTField);
		Box BoxH = Box.createVerticalBox();
		BoxH.add(box1);
		BoxH.add(box2);
		BoxH.add(box3);
		BoxH.add(box4);
		BoxH.add(box5);
		BoxH.add(box6);
		BoxH.add(Box.createVerticalGlue());
		messPanel = new JPanel();
		messPanel.add(BoxH);
		
		
	}
	/*
	 * �����ɾ����ť����ѧ���ı����лس�ʱִ�еĲ���
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==deleteButton ||e.getSource()==numberTFiele){
			String number = "";
			number = numberTFiele.getText();
			if(number.length()>0){
				try {
					fileInputStream = new FileInputStream(SystemFile);
					objectInputStream = new ObjectInputStream(fileInputStream);
					informationTable = (HashMap<String, Student>) objectInputStream.readObject();
					fileInputStream.close();
					objectInputStream.close();
				} catch (Exception ee) {
					// TODO: handle exception
				}if(informationTable.containsKey(number)){
					Student stu = (Student)informationTable.get(number);
					nameTField.setText(stu.getName());
					majorTField.setText(stu.getMajor());
					gradeTField.setText(stu.getGrade());
					birthdayTField.setText(stu.getBirther());
					if(stu.getSex().equals("��"))
						maleRadioButton.setSelected(true);
					else
						femaleRadioButton.setSelected(true);
					String m = "ȷ��Ҫɾ����ѧԱ��ȫ����Ϣ��";
					int ok = JOptionPane.showConfirmDialog(this, m,"ȷ��",JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						informationTable.remove(number);
						try {
							fileOutputStream = new FileOutputStream(SystemFile);
							objectOutputStream = new ObjectOutputStream(fileOutputStream);
							objectOutputStream.writeObject(informationTable);
							objectOutputStream.close();
							fileOutputStream.close();
							clearMessage();
						} catch (Exception ee) {
							// TODO: handle exception
						}
					}else if (ok==JOptionPane.NO_OPTION) {
							clearMessage();}
							
							
					}else{
						String warning = "��ѧ�Ų�����";
						JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);;
						numberTFiele.setText(null);
					}
					
					
				}
		}
	}

	public void clearMessage() {
		// TODO Auto-generated method stub
		numberTFiele.setText(null);
		nameTField.setText(null);
		majorTField.setText(null);
		gradeTField.setText(null);
		birthdayTField.setText(null);
		
	}

}
