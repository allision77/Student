package Student;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;


/*
 * ��ѯѧ����Ϣ�࣬�����ṩ��ѯѧ����Ϣ����
 */
public class QueryStudentInformation extends JPanel implements ActionListener{
	private Student student = null;//ѧ������
	private StudentPicture studentPicture;//ѧ��ͼ��
	private HashMap<String, Student> informationTable = null;
	private JTextField numberTField,nameTField,sexTField,majorTField,gradeTField,birthdarTField;
	private JButton queryButton;//��ѯ��ť
	private FileInputStream fileInputStream = null;//�ļ�����������
	private ObjectInputStream objectInputStream = null;//��������������
	private File systemFile,imagePic;
	private JPanel messPanel = null;//��ʾ������Ϣ������
	
	
/*
 * ���췽������ʼ����ѯѧ����Ϣ����
 */
	public QueryStudentInformation(File file) {
		systemFile = file;
		studentPicture = new StudentPicture();
		informationTable = new HashMap<String,Student>();
		JLabel numberLabel = new JLabel("����Ҫ��ѯ��ѧ��ѧ�ţ�",JLabel.CENTER);
		numberTField = new JTextField(10);
		queryButton = new JButton("��ѯ");
		queryButton.addActionListener(this);
		Box numberBox = Box.createHorizontalBox();//���ˮƽbox
		numberBox.add(numberLabel);
		numberBox.add(numberTField);
		numberBox.add(queryButton);
		initMessPanel();
		JLabel picLabel = new JLabel("��Ƭ��",JLabel.LEFT);
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		picPanel.add(picLabel,BorderLayout.NORTH);
		JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,messPanel,picPanel);
		setLayout(new BorderLayout());
		add(numberBox,BorderLayout.NORTH);
		add(splitH, BorderLayout.CENTER);
		validate();
		
	}
	/*
	 * ��ʼ����ʾѧ����Ϣ���ֽ���
	 */

	private void initMessPanel() {
	JLabel nameLabel = new JLabel("������",JLabel.CENTER);
	nameTField = new JTextField(10);
	nameTField.setEditable(false);
	Box nameBox = Box.createHorizontalBox();//���ˮƽbox
	nameBox.add(nameLabel);
	nameBox.add(nameTField);
	JLabel sexLabel = new JLabel("�Ա�",JLabel.CENTER);
	sexTField = new JTextField(10);
	sexTField.setEditable(false);
	 Box sexBox = Box.createHorizontalBox();//���ˮƽbox
	 sexBox.add(sexLabel);
	 sexBox.add(sexTField);
	 JLabel majorLabel = new JLabel("רҵ��",JLabel.CENTER);
	 majorTField = new JTextField(10);
	 majorTField.setEditable(false);
	 Box majorBox = Box.createHorizontalBox();//���ˮƽbox
	 majorBox.add(majorLabel);
	 majorBox.add(majorTField);
	 JLabel gradeLabel = new JLabel("�꼶��",JLabel.CENTER);
	 gradeTField = new JTextField(10);
	 gradeTField.setEditable(false);
	 Box gradeBox = Box.createHorizontalBox();//���ˮƽbox
	 gradeBox.add(gradeLabel);
	 gradeBox.add(gradeTField);
	 JLabel birthdayLabel = new JLabel("������",JLabel.CENTER);
	 birthdarTField = new JTextField(10);
	 birthdarTField.setEditable(false);
	 Box birthdayBox = Box.createHorizontalBox();//���ˮƽbox
	 birthdayBox.add(birthdayLabel);
	 birthdayBox.add(birthdarTField);
	 Box boxH = Box.createVerticalBox();
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
	 * �������ѯ��ť����ѧ���ı����а��س�ʱִ�еĲ���
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==queryButton || e.getSource()==numberTField){
			String number = "";
			number = numberTField.getText();
			if(number.length()>0){
				try {
					fileInputStream = new FileInputStream(systemFile);
					objectInputStream = new ObjectInputStream(fileInputStream);
					informationTable = (HashMap<String, Student>) objectInputStream.readObject();
					fileInputStream.close();
					objectInputStream.close();
					
				} catch (Exception ee) {
					// TODO: handle exception
				}
				if(informationTable.containsKey(number)){
					student = informationTable.get(number);
					nameTField.setText(student.getName());
					majorTField.setText(student.getMajor());
					gradeTField.setText(student.getGrade());
					birthdarTField.setText(student.getBirther());
					sexTField.setText(student.getSex());
					studentPicture.setImageFile(student.getImagePic());
					studentPicture.repaint();
					}else {
						String warning = "��ѧԱ������";
						JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);
						clearMessage();
					}
			}else {
				String warning = "����Ҫ����ѧ��";
				JOptionPane.showMessageDialog(this, warning,"����",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	/*
	 * ����ʾ����Ϣ���
	 */
	public void clearMessage() {
		// TODO Auto-generated method stub
		numberTField.setText(null);
		nameTField.setText(null);
		gradeTField.setText(null);
		sexTField.setText(null);
		birthdarTField.setText(null);
		majorTField.setText(null);
		studentPicture.setImageFile(null);
		studentPicture.repaint();
		
	}

}
