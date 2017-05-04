import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.awt.Rectangle;

import javax.swing.table.*;

/**
 *��ҳ��
 *
 */



///ѡ���ļ�Ŀ¼
/*
 * 
 * 
 * JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	chooser.showOpenDialog(null);
    	String path = chooser.getSelectedFile().getPath();
    	System.out.println(path);
 * */

public class MainFrame extends JFrame implements ActionListener {
    public static PrintWriter pw = null; //
    public static String indexpath ="";
    String srcpath = ""; //srcpathҪ�������ĵ�·����indexpath�����洢·����
    String queryString = ""; //��ѯ�ʡ�
    int rows = 5; //������ÿҳ5�С�
    int begin = 0; //�ӵ�һ�������ʼ��ʾ��
    ArrayList reslist = new ArrayList(); //��Ų�ѯ��õ���·����score��
    String logname = "Retrieve.log";
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JTextField jTextField4 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    JButton jButton7 = new JButton();
    JButton jButton8 = new JButton();
    
    //��ʷ��¼button
    JButton jButton11 = new JButton();
    JButton jButton12 = new JButton();
    JButton jButton13 = new JButton();
    JButton jButton14 = new JButton();
    JButton jButton15 = new JButton();
    
    JPanel jPanel1 = new JPanel(new BorderLayout());
    int height;
    int width;
    MyTable table = new MyTable();
    JTable restable = new JTable(table);
    TableColumn column = null;

    public MainFrame() {
        super();
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
                logname)), true); //���������
        setTitle("��ý����Ϣ����ϵͳ");
        this.setVisible(true);
        setSize(400, 330);
        setLocation(400, 150);
        width = this.getWidth();
        height = this.getHeight();
        setLayout(null);

        Container con = this.getContentPane();
        jLabel1.setText("�ĵ�·��");
        jLabel1.setBounds(new Rectangle(16, 14, 50, 19));
        jLabel2.setText("����·��");
        jLabel2.setBounds(new Rectangle(16, 38, 50, 19));
        jLabel3.setText("��ѯ�ؼ���");
        jLabel3.setBounds(new Rectangle(16, 65, 50, 19));
        jLabel4.setText("��ѯ�������");
        jLabel4.setBounds(new Rectangle(16, 108, 107, 19));
        jTextField1.setBounds(new Rectangle(82, 14, 207, 19));
        jTextField2.setBounds(new Rectangle(82, 38, 207, 19));
        jTextField2.setText("");
        jTextField3.setBounds(new Rectangle(82, 65, 207, 19));
        jTextField4.setBounds(new Rectangle(172, 106, 210, 19));
        jTextField4.setBackground(this.getBackground());
        jTextField4.setBorder(BorderFactory.createEmptyBorder());
        jTextField4.setEditable(false);
        jButton1.setBounds(new Rectangle(340, 19, 40, 27));
        jButton1.setText("��������");
        jButton1.addActionListener(new MainFrame_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(290, 65, 40, 19));
        jButton2.setText("��ʼ��ѯ");
        jButton2.addActionListener(new MainFrame_jButton2_actionAdapter(this));
        jButton3.setBounds(new Rectangle(116, 267, 74, 19));
        jButton3.setText("��һҳ");
        jButton3.addActionListener(this);
        jButton4.setBounds(new Rectangle(16, 267, 78, 19));
        jButton4.setText("��ҳ");
        jButton4.addActionListener(this);
        jButton5.setBounds(new Rectangle(213, 267, 76, 19));
        jButton5.setText("��һҳ");
        jButton5.addActionListener(this);
        jButton6.setBounds(new Rectangle(312, 267, 72, 19));
        jButton6.setText("βҳ");
        jButton6.addActionListener(this);
        jPanel1.setBackground(Color.BLUE);
        jPanel1.setBounds(new Rectangle(16, 127, 365, 133));
        
        //��Ŀ¼
        jButton7.setText("��Ŀ¼");
        jButton7.setBounds(new Rectangle(290, 14, 40, 19));
        jButton7.addActionListener(new openDir1(this));
        
        jButton8.setText("��Ŀ¼");
        jButton8.setBounds(new Rectangle(290, 38, 40, 19));
        jButton8.addActionListener(new openDir2(this));
        
        
        //��ʷ��¼button
        jButton11.setBounds(new Rectangle(82, 87, 30, 19));
        jButton11.addActionListener(new history1(this));
        jButton11.setVisible(false);
        
        jButton12.setBounds(new Rectangle(120, 87, 30, 19));
        jButton12.addActionListener(new history2(this));
        jButton12.setVisible(false);
     
        jButton13.setBounds(new Rectangle(160,87, 30, 19));
        jButton13.addActionListener(new history3(this));
        jButton13.setVisible(false);
        
        jButton14.setBounds(new Rectangle(200, 87, 30, 19));
        jButton14.addActionListener(new history4(this));
        jButton14.setVisible(false);
        
        jButton15.setBounds(new Rectangle(240, 87, 30, 19));
        jButton15.addActionListener(new history5(this));
        jButton15.setVisible(false);
        
        //����ʾ�����table�趨�п�
        for (int i = 0; i < 3; i++) {
            column = restable.getColumnModel().getColumn(i);
            switch (i) {
            case 0:
                column.setPreferredWidth(width / 8);
                break;
            case 1:
                column.setPreferredWidth(width * 280 / 400);
                break;
            case 2:
                column.setPreferredWidth(width * 70 / 400);
                break;
            }
        }
        //����ʾ�����table�趨�иߡ�
        restable.setRowHeight(23);
        restable.setColumnSelectionAllowed(false);
        restable.setRowSelectionAllowed(true);
        restable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    if(restable.getSelectedColumn()!=1)
                        return;
                    String fpath = (String) table.getValueAt(restable.getSelectedRow(), restable.getSelectedColumn());
                    Runtime   r   =   Runtime.getRuntime();
                    try {
                        r.exec("cmd   /c   start   " + fpath);
                    } catch (IOException ex) {
                    }
                }
            }
        });

        jPanel1.add(restable.getTableHeader(), BorderLayout.NORTH);
        jPanel1.add(restable, BorderLayout.CENTER);
        con.addComponentListener(new MainFrame_con_componentAdapter(this)); //�Զ�������С��
        con.add(jLabel1);
        con.add(jLabel2);
        con.add(jTextField1);
        con.add(jTextField2);
        con.add(jLabel3);
        con.add(jTextField3);
        con.add(jTextField4);
        con.add(jLabel4);
        con.add(jButton4);
        con.add(jButton3);
        con.add(jButton5);
        con.add(jButton2);
        con.add(jButton1);
        con.add(jButton6);
        con.add(jButton7);
        con.add(jButton8);
        con.add(jButton11);
        con.add(jButton12);
        con.add(jButton13);
        con.add(jButton14);
        con.add(jButton15);
        con.add(jPanel1);
        WindowDestroyer myListener = new WindowDestroyer();
        addWindowListener(myListener);
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }

    //Auto resize.
    public void con_componentResized(ComponentEvent e) {
        int oldheight = height;
        int oldwidth = width;
        height = this.getHeight();
        width = this.getWidth();
        jLabel1.setSize(jLabel1.getWidth() * width / oldwidth,
                        jLabel1.getHeight() * height / oldheight);
        jLabel2.setSize(jLabel2.getWidth() * width / oldwidth,
                        jLabel2.getHeight() * height / oldheight);
        jLabel3.setSize(jLabel3.getWidth() * width / oldwidth,
                        jLabel3.getHeight() * height / oldheight);
        jLabel4.setSize(jLabel4.getWidth() * width / oldwidth,
                        jLabel4.getHeight() * height / oldheight);
        jTextField1.setSize(jTextField1.getWidth() * width / oldwidth,
                            jTextField1.getHeight() * height / oldheight);
        jTextField2.setSize(jTextField2.getWidth() * width / oldwidth,
                            jTextField2.getHeight() * height / oldheight);
        jTextField3.setSize(jTextField3.getWidth() * width / oldwidth,
                            jTextField3.getHeight() * height / oldheight);
        jTextField4.setSize(jTextField4.getWidth() * width / oldwidth,
                            jTextField4.getHeight() * height / oldheight);
        jButton1.setSize(jButton1.getWidth() * width / oldwidth,
                         jButton1.getHeight() * height / oldheight);
        jButton2.setSize(jButton2.getWidth() * width / oldwidth,
                         jButton2.getHeight() * height / oldheight);
        jButton3.setSize(jButton3.getWidth() * width / oldwidth,
                         jButton3.getHeight() * height / oldheight);
        jButton4.setSize(jButton4.getWidth() * width / oldwidth,
                         jButton4.getHeight() * height / oldheight);
        jButton5.setSize(jButton5.getWidth() * width / oldwidth,
                         jButton5.getHeight() * height / oldheight);
        jButton6.setSize(jButton6.getWidth() * width / oldwidth,
                         jButton6.getHeight() * height / oldheight);
        jButton7.setSize(jButton7.getWidth() * width / oldwidth,
                jButton7.getHeight() * height / oldheight);
        jButton8.setSize(jButton8.getWidth() * width / oldwidth,
                jButton8.getHeight() * height / oldheight);
        jButton11.setSize(jButton11.getWidth() * width / oldwidth,
                jButton11.getHeight() * height / oldheight);
        jButton12.setSize(jButton12.getWidth() * width / oldwidth,
                jButton12.getHeight() * height / oldheight);
        jButton13.setSize(jButton13.getWidth() * width / oldwidth,
                jButton13.getHeight() * height / oldheight);
        jButton14.setSize(jButton14.getWidth() * width / oldwidth,
                jButton14.getHeight() * height / oldheight);
        jButton15.setSize(jButton15.getWidth() * width / oldwidth,
                jButton15.getHeight() * height / oldheight);
        jPanel1.setSize(jPanel1.getWidth() * width / oldwidth,
                        jPanel1.getHeight() * height / oldheight);
        jLabel1.setLocation(jLabel1.getLocation().x * width / oldwidth,
                            jLabel1.getLocation().y * height / oldheight);
        jLabel2.setLocation(jLabel2.getLocation().x * width / oldwidth,
                            jLabel2.getLocation().y * height / oldheight);
        jLabel3.setLocation(jLabel3.getLocation().x * width / oldwidth,
                            jLabel3.getLocation().y * height / oldheight);
        jLabel4.setLocation(jLabel4.getLocation().x * width / oldwidth,
                            jLabel4.getLocation().y * height / oldheight);
        jTextField1.setLocation(jTextField1.getLocation().x * width / oldwidth,
                                jTextField1.getLocation().y * height /
                                oldheight);
        jTextField2.setLocation(jTextField2.getLocation().x * width / oldwidth,
                                jTextField2.getLocation().y * height /
                                oldheight);
        jTextField3.setLocation(jTextField3.getLocation().x * width / oldwidth,
                                jTextField3.getLocation().y * height /
                                oldheight);
        jTextField4.setLocation(jTextField4.getLocation().x * width / oldwidth,
                                jTextField4.getLocation().y * height /
                                oldheight);
        jButton1.setLocation(jButton1.getLocation().x * width / oldwidth,
                             jButton1.getLocation().y * height / oldheight);
        jButton2.setLocation(jButton2.getLocation().x * width / oldwidth,
                             jButton2.getLocation().y * height / oldheight);
        jButton3.setLocation(jButton3.getLocation().x * width / oldwidth,
                             jButton3.getLocation().y * height / oldheight);
        jButton4.setLocation(jButton4.getLocation().x * width / oldwidth,
                             jButton4.getLocation().y * height / oldheight);
        jButton5.setLocation(jButton5.getLocation().x * width / oldwidth,
                             jButton5.getLocation().y * height / oldheight);
        jButton6.setLocation(jButton6.getLocation().x * width / oldwidth,
                             jButton6.getLocation().y * height / oldheight);
        jButton7.setLocation(jButton7.getLocation().x * width / oldwidth,
                jButton7.getLocation().y * height / oldheight);
        jButton8.setLocation(jButton8.getLocation().x * width / oldwidth,
                jButton8.getLocation().y * height / oldheight);
        jButton11.setLocation(jButton11.getLocation().x * width / oldwidth,
                jButton11.getLocation().y * height / oldheight);
        jButton12.setLocation(jButton12.getLocation().x * width / oldwidth,
                jButton12.getLocation().y * height / oldheight);
        jButton13.setLocation(jButton13.getLocation().x * width / oldwidth,
                jButton13.getLocation().y * height / oldheight);
        jButton14.setLocation(jButton14.getLocation().x * width / oldwidth,
                jButton14.getLocation().y * height / oldheight);
        jButton15.setLocation(jButton15.getLocation().x * width / oldwidth,
                jButton15.getLocation().y * height / oldheight);
        jPanel1.setLocation(jPanel1.getLocation().x * width / oldwidth,
                            jPanel1.getLocation().y * height / oldheight);
        restable.setSize(restable.getSize().width * width / oldwidth,
                         restable.getSize().height * height / oldheight);
        for (int i = 0; i < 3; i++) {
            column = restable.getColumnModel().getColumn(i);
            switch (i) {
            case 0:
                column.setPreferredWidth(width / 8);
                break;
            case 1:
                column.setPreferredWidth(width * 280 / 400);
                break;
            case 2:
                column.setPreferredWidth(width * 70 / 400);
                break;
            }
        }
        restable.setRowHeight(restable.getRowHeight() * height / oldheight);
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        String srcpath, indexpath;
        srcpath = jTextField1.getText();
        indexpath = jTextField2.getText();
        switch (LuceneProc.CreateIndex(srcpath, indexpath)) {
        case 0:
            JOptionPane.showMessageDialog(this, "�����Ѿ�����!");
            break;
        case 1:
        case 2:
        default:
            JOptionPane.showMessageDialog(this, "����������������·���Ƿ���ȷ��");
        }
    }

    public void jButton2_actionPerformed(ActionEvent e) throws Exception {
        indexpath = jTextField2.getText();
        queryString = jTextField3.getText();
        reslist.clear();
        if (queryString.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "�������ѯ�ʣ�");
        } else {
        	//ִ�м���
            switch (LuceneProc.SearchProc(indexpath, queryString, reslist)) {
            case 0:
                jTextField4.setText("��ѯ�����" + reslist.size() + "��,��ǰΪ��" +
                                    (begin / rows + 1) + "ҳ");
                String[] ss = new String[2];
                table.clear();
                begin = 0;
                for (int i = begin; i < reslist.size() && i < begin + rows; i++) {
                    ss = (String[]) reslist.get(i);
                    table.setValueAt(1 + i, i - begin, 0);
                    table.setValueAt(ss[0], i - begin, 1);
                    table.setValueAt(ss[1], i - begin, 2);
                }
                break;
            case 1:
            case 2:
            default:
                JOptionPane.showMessageDialog(this, "�����������������Ƿ���ڣ�·���Ƿ���ȷ��");
            }
            FileOperation fOpertion = new FileOperation();
            String fileDir = indexpath+"/test.txt";
            File file = new File(fileDir);
            this.isAddHistory(fileDir);//���ø�����ʷ��¼
            String txtContent = fOpertion.readTxtFile(file);
            Pattern pattern = Pattern.compile("[$]+");
            String[] strs = pattern.split(txtContent);
            //����ʷ��¼������ť��ǩ��
            switch(strs.length){
            case 0:
            	break;
            case 2:
            	jButton11.setText(strs[1]);
            	jButton11.setVisible(true);
            	break;
            case 3:
            	 jButton11.setText(strs[1]);
                 jButton12.setText(strs[2]);
                 jButton11.setVisible(true);
                 jButton12.setVisible(true);
                 break;
            case 4:
            	 jButton11.setText(strs[1]);
                 jButton12.setText(strs[2]);
                 jButton13.setText(strs[3]);
                 jButton11.setVisible(true);
                 jButton12.setVisible(true);
                 jButton13.setVisible(true);
                 break;
            case 5:
            	 jButton11.setText(strs[1]);
                 jButton12.setText(strs[2]);
                 jButton13.setText(strs[3]);
                 jButton14.setText(strs[4]);
                 jButton11.setVisible(true);
                 jButton12.setVisible(true);
                 jButton13.setVisible(true);
                 jButton14.setVisible(true);
                 break;
            case 6:
            	 jButton11.setText(strs[1]);
                 jButton12.setText(strs[2]);
                 jButton13.setText(strs[3]);
                 jButton14.setText(strs[4]);
                 jButton15.setText(strs[5]);
                 jButton11.setVisible(true);
                 jButton12.setVisible(true);
                 jButton13.setVisible(true);
                 jButton14.setVisible(true);
                 jButton15.setVisible(true);
                 break;
            default:
            }
        }
    }

    //�ж��Ƿ������ʷ��¼
    public void isAddHistory(String fileDir) throws Exception{
    	 File file = new File(fileDir);
    	 FileOperation fOpertion = new FileOperation();
         String txtContent = fOpertion.readTxtFile(file);
         Pattern pattern = Pattern.compile("[$]+");
         String[] strs = pattern.split(txtContent.trim());
         if(Arrays.asList(strs).contains(queryString.trim()) == false){
        	 if(strs.length<6){
    	         fOpertion.contentToTxt(file,"$"+queryString);
    	     }else{
    	        String historyStr = "";
    	        for(int i=1;i<5;i++){
    	        	historyStr = historyStr + "$"+strs[i+1];        			 
    	        }
    	        historyStr =historyStr+"$"+ queryString;
    	        fOpertion.writeTxtFile(file,historyStr);//���ǵ�ԭ������
    	    }
    	 }	  
    }         
    
    //���ļ���
    public void JB_openDir1(ActionEvent e) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	chooser.showOpenDialog(null);
    	String path = chooser.getSelectedFile().getPath();
    	 jTextField1.setText(path);//����ļ���·�����ı���
    }
    ////���ļ���
    public void JB_openDir2(ActionEvent e) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	chooser.showOpenDialog(null);
    	String path = chooser.getSelectedFile().getPath();
    	jTextField2.setText(path);//����ļ���·�����ı���
    	
    }
    
    ////����ʷ��¼���뵽�ؼ�����
    @SuppressWarnings("deprecation")
	public void inputHistory1(ActionEvent e) {	
    	jTextField3.setText(jButton11.getLabel());//�����ʷ��¼���ؼ�����	
    }
    public void inputHistory2(ActionEvent e) {	
    	jTextField3.setText(jButton12.getLabel());//�����ʷ��¼���ؼ�����	
    }
    public void inputHistory3(ActionEvent e) {	
    	jTextField3.setText(jButton13.getLabel());//�����ʷ��¼���ؼ�����
    }
    public void inputHistory4(ActionEvent e) {	
    	jTextField3.setText(jButton14.getLabel());//�����ʷ��¼���ؼ�����
    }
    public void inputHistory5(ActionEvent e) {	
    	jTextField3.setText(jButton15.getLabel());//�����ʷ��¼���ؼ�����
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (reslist.size() == 0) {
            JOptionPane.showMessageDialog(this, "�㻹û�в�ѯ���ѯ���Ϊ�գ�");
        } else if (e.getActionCommand().equals("��ҳ")) {
            jTextField4.setText("��ѯ�����" + reslist.size() + "��,��ǰΪ��" +
                                (begin / rows + 1) + "ҳ");
            begin = 0;
            String[] ss = new String[2];
            table.clear();
            for (int i = begin; i < reslist.size() && i < begin + rows; i++) {
                ss = (String[]) reslist.get(i);
                table.setValueAt(1 + i, i - begin, 0);
                table.setValueAt(ss[0], i - begin, 1);
                table.setValueAt(ss[1], i - begin, 2);
            }
        } else if (e.getActionCommand().equals("��һҳ")) {
            if (begin + rows >= reslist.size()) {
                JOptionPane.showMessageDialog(this, "�Ѿ������һҳ��");
                return;
            }
            begin += rows;
            jTextField4.setText("��ѯ�����" + reslist.size() + "��,��ǰΪ��" +
                                (begin / rows + 1) + "ҳ");
            String[] ss = new String[2];
            table.clear();
            for (int i = begin; i < reslist.size() && i < begin + rows; i++) {
                ss = (String[]) reslist.get(i);
                table.setValueAt(1 + i, i - begin, 0);
                table.setValueAt(ss[0], i - begin, 1);
                table.setValueAt(ss[1], i - begin, 2);
            }
        } else if (e.getActionCommand().equals("��һҳ")) {
            if (begin == 0) {
                JOptionPane.showMessageDialog(this, "�Ѿ��ǵ�һҳ��");
                return;
            }
            begin -= rows;
            jTextField4.setText("��ѯ�����" + reslist.size() + "��,��ǰΪ��" +
                                (begin / rows + 1) + "ҳ");
            String[] ss = new String[2];
            table.clear();
            for (int i = begin; i < reslist.size() && i < begin + rows; i++) {
                ss = (String[]) reslist.get(i);
                table.setValueAt(1 + i, i - begin, 0);
                table.setValueAt(ss[0], i - begin, 1);
                table.setValueAt(ss[1], i - begin, 2);
            }
        } else if (e.getActionCommand().equals("βҳ")) {
            begin = reslist.size() / rows * rows;
            if (reslist.size() % rows == 0) {
                begin -= rows;
            }
            jTextField4.setText("��ѯ�����" + reslist.size() + "��,��ǰΪ��" +
                                (begin / rows + 1) + "ҳ");
            String[] ss = new String[2];
            table.clear();
            for (int i = begin; i < reslist.size() && i < begin + rows; i++) {
                ss = (String[]) reslist.get(i);
                table.setValueAt(1 + i, i - begin, 0);
                table.setValueAt(ss[0], i - begin, 1);
                table.setValueAt(ss[1], i - begin, 2);
            }
        }
    }
}


class MainFrame_con_componentAdapter extends ComponentAdapter {
    private MainFrame adaptee;
    MainFrame_con_componentAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void componentResized(ComponentEvent e) {
        adaptee.con_componentResized(e);
    }
}


class WindowDestroyer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        MainFrame.pw.close();
        System.exit(0);
    }
}


class MainFrame_jButton2_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jButton2_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
			adaptee.jButton2_actionPerformed(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}


class MainFrame_jButton1_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jButton1_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

//��Ŀ¼������
class openDir1 implements ActionListener {
    private MainFrame adaptee;
    openDir1(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.JB_openDir1(e);
    }
}

//��Ŀ¼������
class openDir2 implements ActionListener {
    private MainFrame adaptee;
    openDir2(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.JB_openDir2(e);
    }
}

//��ʷ��¼1
class history1 implements ActionListener {
  private MainFrame adaptee;
  history1(MainFrame adaptee) {
      this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
      adaptee.inputHistory1(e);
  }
}

//��ʷ��¼2
class history2 implements ActionListener {
private MainFrame adaptee;
history2(MainFrame adaptee) {
    this.adaptee = adaptee;
}

public void actionPerformed(ActionEvent e) {
    adaptee.inputHistory2(e);
}
}

//��ʷ��¼3
class history3 implements ActionListener {
private MainFrame adaptee;
history3(MainFrame adaptee) {
    this.adaptee = adaptee;
}

public void actionPerformed(ActionEvent e) {
    adaptee.inputHistory3(e);
}
}

//��ʷ��¼4
class history4 implements ActionListener {
private MainFrame adaptee;
history4(MainFrame adaptee) {
  this.adaptee = adaptee;
}

public void actionPerformed(ActionEvent e) {
  adaptee.inputHistory4(e);
}
}

//��ʷ��¼5
class history5 implements ActionListener {
private MainFrame adaptee;
history5(MainFrame adaptee) {
  this.adaptee = adaptee;
}

public void actionPerformed(ActionEvent e) {
  adaptee.inputHistory5(e);
}
}


