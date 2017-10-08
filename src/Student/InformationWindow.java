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
 * ����ϵͳ�����������ĳ�ʼ��
 */

public class InformationWindow extends JFrame implements ActionListener{
	private InputStudentInformation inputstudentinformation;//¼����Ϣ
	private ModifyStudentInformation modifystudentinformation;//�޸���Ϣ
	private QueryStudentInformation querystudentinformation;//��ѯ��Ϣ
	private DeletStudentInformation deletstudentinformation;//ɾ����Ϣ
	
	private JMenuBar bar;//�˵���
	private JMenu fileMenu;
	private JMenuItem inputItem,modifyItem,queryItem,deletItem,welcomeItem;//���˵���
	private HashMap<String,Student> informationTable = null;//ѧ����Ϣ��
	private File file = null;
	private CardLayout card = null;
	private JLabel label = null;
	private JPanel pCenter;
	
	/*
	 * ���췽������ʼ������
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
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���",
						"ȷ�϶Ի���",JOptionPane.YES_NO_CANCEL_OPTION);
				if(n==JOptionPane.YES_NO_OPTION)
					System.exit(0);
				}
				
			
		});
		setResizable(false);
		validate();
		
	}
	/*
	 * ��ʼ��������ĸ������
	 */
	private void initFrame() throws IOException {
		// TODO Auto-generated method stub
		inputItem = new JMenuItem("¼��");
		modifyItem = new JMenuItem("�޸�");
		queryItem = new JMenuItem("��ѯ");
		deletItem = new JMenuItem("ɾ��");
		welcomeItem = new JMenuItem("������ҳ");
		bar = new JMenuBar();
		fileMenu = new JMenu("�˵�ѡ��");
		fileMenu.add(inputItem);
		fileMenu.add(modifyItem);
		fileMenu.add(queryItem);
		fileMenu.add(deletItem);
		fileMenu.add(welcomeItem);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label = new JLabel("ѧ������ϵͳ",JLabel.CENTER);
		label.setIcon(new ImageIcon("welcome.jpg"));
		label.setFont(new Font("����", Font.BOLD, 36));
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
		file = new File("������Ϣ.txt");
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
		pCenter.add("��ӭ����",label);
	    pCenter.add("¼�����",inputstudentinformation);
	    pCenter.add("�޸Ľ���",modifystudentinformation);
    	pCenter.add("��ѯ����",querystudentinformation);
		pCenter.add("ɾ������",deletstudentinformation);
		add(pCenter,BorderLayout.CENTER);
		
	}
	/*
	 * ���¼�룬�޸ģ���ѯ��ɾ������ӭ�˵�ʱ���еĲ���
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==inputItem){
			
			inputstudentinformation.clearMessage();
			card.show(pCenter, "¼�����");
			
		}else if (e.getSource()==modifyItem) {
			modifystudentinformation.clearMessage();
			card.show(pCenter, "�޸Ľ���");
			
		}else if(e.getSource()==queryItem){
			querystudentinformation.clearMessage();
			card.show(pCenter, "��ѯ����");
			
		}else if (e.getSource()==deletItem) {
			card.show(pCenter, "ɾ������");
		}else if(e.getSource()==welcomeItem){
				card.show(pCenter, "��ӭ����");
			
		}
}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new InformationWindow();

	}
	

	
}
