package in.pitsol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Notepad_type implements ActionListener,ChangeListener
{
	static int count=0;
	private String path="";
	private String text;
	private String faltu;
	private String text1;
	private File file1, fontfile;
	private JFileChooser jfc;
	private JColorChooser jccB,jccF;
	private String file=""; 
	private JFrame jframe,jframe1,jframe2,jframe3;
	private JTextArea jtextarea;
	private JTextField jtextfield;
	private JLabel jl1,jl2,jpreview,jlfind;
	private JButton jb1,jb2,jPreview,jApply,jExit,jFind,jCancel;
	private JComboBox jcbFont,jcbStyle,jcbSize;
	private JRadioButton r1,r2,r3,r4,r5,r6;
	private JCheckBox matchcase;
	private ButtonGroup bg1,bg2;
	private JMenu menu1,menu2,menu3,menu4; 
	private JMenuBar menubar;
	private JTabbedPane tp;
	private JCheckBoxMenuItem lcase,ucase,tcase,wcase,scase;
	private JMenuItem menuitemNew,menuitemOpen,menuitemSave,menuitemColor,menuitemSaveAs,menuitemExit,menuitemFont,menuitemCut,menuitemCopy,menuitemPaste,menuitemDel,menuitemFind; 
	private JScrollPane jsp;
	private JPanel panel,pB,pF;
	private int rvalue=0;
	Notepad_type()
	{
		jframe=new JFrame("Untitled");
		menubar=new JMenuBar();
		menu1=new JMenu("File");	
		menu2=new JMenu("Format");
		menu3=new JMenu("Transform");
		menu4=new JMenu("Edit");
		menuitemNew=new JMenuItem("New"); 
		menuitemOpen=new JMenuItem("Open"); 
		menuitemSave=new JMenuItem("Save");
		menuitemSaveAs=new JMenuItem("Save As");
		menuitemColor=new JMenuItem("Color");
		menuitemFont=new JMenuItem("Font");
		menuitemExit=new JMenuItem("Exit");
		menuitemCut=new JMenuItem("Cut");
		menuitemCopy=new JMenuItem("Copy");
		menuitemPaste=new JMenuItem("Paste");
		menuitemDel=new JMenuItem("Delete");
		menuitemFind=new JMenuItem("Find");
		lcase=new JCheckBoxMenuItem("LowerCase");
		ucase=new JCheckBoxMenuItem("UpperCase");
		tcase=new JCheckBoxMenuItem("ToggleCase");
		wcase=new JCheckBoxMenuItem("WordCase");
		scase=new JCheckBoxMenuItem("SentenceCase");
		menu1.add(menuitemNew);
		menu1.add(menuitemOpen);
		menu1.add(menuitemSave);
		menu1.add(menuitemSaveAs);
		menu1.add(menuitemExit);
		menu2.add(menuitemColor);
		menu2.add(menuitemFont);
		menu3.add(lcase);
		menu3.add(ucase);
		menu3.add(tcase);
		menu3.add(wcase);
		menu3.add(scase);
		menu4.add(menuitemCut);
		menu4.add(menuitemCopy);
		menu4.add(menuitemPaste);
		menu4.add(menuitemDel);
		menu4.add(menuitemFind);
		menu1.setMnemonic('F');
		menu2.setMnemonic('M');
		menu3.setMnemonic('T');
		menu4.setMnemonic('E');
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu4);
		menubar.add(menu3); 
		KeyStroke ctrln=KeyStroke.getKeyStroke('N',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		menuitemNew.setAccelerator(ctrln);
		KeyStroke ctrlo=KeyStroke.getKeyStroke('O',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		menuitemOpen.setAccelerator(ctrlo);
		KeyStroke ctrls=KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		menuitemSave.setAccelerator(ctrls);
		KeyStroke ctrle=KeyStroke.getKeyStroke('E',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		menuitemExit.setAccelerator(ctrle);
		jframe.setJMenuBar(menubar); 
		jfc=new JFileChooser();
		jfc.addActionListener(this);
		menuitemNew.addActionListener(this);
		menuitemOpen.addActionListener(this);
		menuitemSave.addActionListener(this);
		menuitemSaveAs.addActionListener(this);
		menuitemExit.addActionListener(this);
		menuitemColor.addActionListener(this);
		menuitemFont.addActionListener(this);
		menuitemCut.addActionListener(this);
		menuitemCopy.addActionListener(this);
		menuitemPaste.addActionListener(this);
		menuitemDel.addActionListener(this);
		menuitemFind.addActionListener(this);
		lcase.addActionListener(this);
		ucase.addActionListener(this);
		tcase.addActionListener(this);
		wcase.addActionListener(this);
		scase.addActionListener(this);
		jtextarea=new JTextArea();
		jsp=new JScrollPane(jtextarea);  
		jframe.add(jsp); //*****Frame BorderLayout is by default CENTER.
		jframe.setSize(400, 400);
		//jframe.setResizable(false);//So that no size changes can be made.
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE); 
		try
		{  
			text1=" ";
			FileReader foe=new FileReader("fontfile.txt");
			int i;
			while((i=foe.read())!=-1)
			{
				text1+=""+(char)i;
			}
			String tokens[]=text1.split(",");
			for(int m=0;m<tokens.length;m++)
			{
				System.out.println(tokens[m]);
			}
			int style=0;
	 		if(tokens[1].equals("Bold"))
	 		{
	 			style=Font.BOLD; 
	 		}
	 		if(tokens[1].equals("Italics"))
	 		{
	 			style=Font.ITALIC; 
	 		}
	 		if(tokens[1].equals("Plain"))
	 		{  
	 			style=Font.PLAIN;
	 		}
	 		int size=Integer.valueOf(tokens[2]);
	 		System.out.println(size);
	 		System.out.println(tokens[0]);
			jtextarea.setFont(new Font(tokens[0].trim(),style,size));
			foe.close();
		}
		catch(Exception e1)
		{
			System.out.println("entered catch");
			System.out.println(e1);
		}
	}
	public static void main(String[] args) 
	{
		new Notepad_type();
	} 
	@Override
	public void actionPerformed(ActionEvent e)   
	{
		try
		{
			FileWriter fw = new FileWriter("default.txt",true);
		 	
			
			//********************//		
			//****************************/
			
		 	if(e.getSource()==menuitemSave)
		 	{ 
		 		System.out.println(count); 
		 		if(jframe.getTitle().equals("Untitled"))
		 		{
		 			rvalue=jfc.showSaveDialog(jframe);
		 			path=jfc.getSelectedFile().getAbsolutePath();
		 			System.out.println("HI");
					if(rvalue==jfc.APPROVE_OPTION) 
					{
						file1=new File(jfc.getSelectedFile().getAbsolutePath());
						jframe.setTitle(file1.getName());
						try
						{ 
							FileWriter fw1=new FileWriter(file1);
							fw1.write(jtextarea.getText());
							fw1.close();
						} 
						catch(Exception e1)
						{ 
							System.out.println(e1);
						}
						count=1;
					}
		 		} 
		 		else 
		 		{
		 			FileWriter fw1=new FileWriter(path);  
					fw1.write(jtextarea.getText());  
					System.out.println("Data Saved");
					path=jfc.getSelectedFile().getAbsolutePath();
					fw1.close();
		 		}
		 		
		 	}
		 	//*****************//
		 if(e.getSource()==menuitemSaveAs)
			{ 
			 System.out.println("hi");
				rvalue=jfc.showSaveDialog(jframe);
				path=jfc.getSelectedFile().getAbsolutePath();
			
				if(rvalue==jfc.APPROVE_OPTION) 
				{
					File file=new File(jfc.getSelectedFile().getAbsolutePath());
					jframe.setTitle(file.getName());
					System.out.println(file.getAbsolutePath());
					try
					{  
						FileWriter fw1=new FileWriter(file);
						fw1.write(jtextarea.getText());
						fw1.close();
					}
					catch(Exception e1)
					{ 
						System.out.println(e1);
					}
					count=1;
				}
			} 
		 
		 //************************//
		 	if(e.getSource()==menuitemExit)
		 	{
		 		text1="";
		 		if(jtextarea.getText().equals(""))  
		 		{ 
		 			if(jframe.getTitle().equals("Untitled"))
		 				System.exit(0);
		 			else 
		 			{ 
		 				System.out.println("entered 1");
		 				try   
						{
							FileReader fr=new FileReader(path);
							int i;
							while((i=fr.read())!=-1)
							{
								text1+=""+(char)i;
							}
						}
						catch(Exception e2)
						{
							System.out.println(e2);
						}
		 				System.out.println("Entered 2");
						System.out.println(text1);
		 				if(jtextarea.getText().equals(text1)) 
		 				{
		 					System.out.println("data matched");
		 					System.exit(0);
		 				} 
		 				else 
		 				{
		 					System.out.println("entered 3");
		 					int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
							if(rv==JOptionPane.YES_OPTION)
							{
								try
								{ 
									FileWriter fw1=new FileWriter(path);
									fw1.write(jtextarea.getText());
									fw1.close();
								} 
								catch(Exception e1)
								{ 
							 		System.out.println(e1);
								} 
								System.exit(0);
							}
							else if(rv==JOptionPane.NO_OPTION)
							{
								System.exit(0);
							} 

		 				}
		 			}
		 			
		 		} 
		 		else
		 		{ 
		 			if(jframe.getTitle()!="Untitled")
		 			{
		 				System.out.println("exist checked");
 						try  
						{
							FileReader fr=new FileReader(path);
							int i;
							while((i=fr.read())!=-1)
							{
								text1+=""+(char)i;
							}
						}
						catch(Exception e2)
						{
							System.out.println(e2);
						}
						System.out.println(text1); 
						if(jtextarea.getText().equals(text1))
						{
							System.out.println("Exit"); 
							System.exit(0);
						}
						else {
						int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
						if(rv==JOptionPane.YES_OPTION)
						{
							try
							{ 
								FileWriter fw1=new FileWriter(path);
								fw1.write(jtextarea.getText());
								fw1.close();
							} 
							catch(Exception e1)
							{ 
						 		System.out.println(e1);
							} 
							System.exit(0);
						}
						else if(rv==JOptionPane.NO_OPTION)
						{ 
							System.exit(0);
						} 
		 			}
		 		}
		 			else
		 			{
		 				int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
						if(rv==JOptionPane.NO_OPTION)
						{
							System.exit(0); 
						}
						else if(rv==JOptionPane.YES_OPTION)  
						{
							rvalue=jfc.showSaveDialog(jframe);
							
								if(rvalue==jfc.APPROVE_OPTION) 
								{
								 	File file=new File(jfc.getSelectedFile().getAbsolutePath());
									jframe.setTitle(file.getName());
									System.out.println(file.getAbsolutePath());
									try
									{ 
										FileWriter fw1=new FileWriter(file);
										fw1.write(jtextarea.getText());
										fw1.close(); 
									}
									catch(Exception e1)
									{
										System.out.println(e1);
									}
									//count=1;
								}	
							System.exit(0);
						}
		 			}
		 		}
		 	}
		 	
		 	//*******************************//
		 	 
		 	if(e.getSource()==menuitemOpen) 
			{
		 		text="";
		 		text1="";
				if(jtextarea.getText().equals(""))
				{
					 rvalue=jfc.showOpenDialog(jframe); 
					 path=jfc.getSelectedFile().getAbsolutePath();
						if(rvalue==jfc.APPROVE_OPTION)  
						{
							File file=new File(jfc.getSelectedFile().getAbsolutePath());
							jframe.setTitle(file.getName());
							System.out.println("HI");
							System.out.println(file.getAbsolutePath());
							try
							{ 
								FileReader fr=new FileReader(file);
								int i;
								while((i=fr.read())!=-1)
								{
									text+=""+(char)i;
								}
								
								fw.close();
								System.out.println("HI");
								jtextarea.setText(""+text);   
							}
							catch(Exception e1)
							{
								System.out.println(e1);
							}
				       }
				}
				else
				{
					System.out.println("outside exist check"); 
					System.out.println(jframe.getTitle());
					if(jframe.getTitle()!="Untitled")   
					{
						System.out.println("exist checked");
						try  
						{
							FileReader fr=new FileReader(path);
							int i;
							while((i=fr.read())!=-1)
							{
								text1+=""+(char)i;
							}
						}
						catch(Exception e2)
						{
							System.out.println(e2);
						}
						if(jtextarea.getText().equals(text1))
						{
							rvalue=jfc.showOpenDialog(jframe);
							path=jfc.getSelectedFile().getAbsolutePath();
							 
							if(rvalue==jfc.APPROVE_OPTION)  
							{
								File file=new File(jfc.getSelectedFile().getAbsolutePath());
								jframe.setTitle(file.getName());
								System.out.println("HI");
								System.out.println(file.getAbsolutePath());
								try
								{ 
									FileReader fr=new FileReader(file);
									int i;
									while((i=fr.read())!=-1)
									{
										text+=""+(char)i;
									}
									
									fw.close();
									System.out.println("HI");
									jtextarea.setText(""+text);  
								}
								catch(Exception e1)
								{
									System.out.println(e1);
								}
					       }
						}
						else {
							
						int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
						if(rv==JOptionPane.YES_OPTION)
						{
							try
							{ 
								FileWriter fw1=new FileWriter(path);
								fw1.write(jtextarea.getText());
								fw1.close();
							} 
							catch(Exception e1)
							{ 
								System.out.println(e1);
							}
							rvalue=jfc.showOpenDialog(jframe);  
							path=jfc.getSelectedFile().getAbsolutePath(); 
							if(rvalue==jfc.APPROVE_OPTION)  
							{
								File file=new File(jfc.getSelectedFile().getAbsolutePath());
								jframe.setTitle(file.getName());
								System.out.println("HI");
								System.out.println(file.getAbsolutePath());
								try
								{ 
									FileReader fr=new FileReader(file);
									int i;
									while((i=fr.read())!=-1)
									{
										text+=""+(char)i;
									}
									
									fw.close();
									System.out.println("HI");
									jtextarea.setText(""+text);  
								}
								catch(Exception e1)
								{
									System.out.println(e1);
								}
					       }
						}
						else if(rv==JOptionPane.NO_OPTION)
						{
							rvalue=jfc.showOpenDialog(jframe); 
							path=jfc.getSelectedFile().getAbsolutePath();
							 
							if(rvalue==jfc.APPROVE_OPTION)  
							{
								File file=new File(jfc.getSelectedFile().getAbsolutePath());
								jframe.setTitle(file.getName());
								System.out.println("HI");
								System.out.println(file.getAbsolutePath());
								try
								{ 
									FileReader fr=new FileReader(file);
									int i;
									while((i=fr.read())!=-1)
									{
										text+=""+(char)i;
									}
									
									fw.close();
									System.out.println("HI");
									jtextarea.setText(""+text);  
								}
								catch(Exception e1)
								{
									System.out.println(e1);
								}
					       }
						}
					}
				}
					else
					{
					int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
					if(rv==JOptionPane.YES_OPTION)  
					{
							rvalue=jfc.showSaveDialog(jframe);
							if(rvalue==jfc.APPROVE_OPTION)
							{
								file1=new File(jfc.getSelectedFile().getAbsolutePath());
								//jframe.setTitle(file1.getName());
								try
								{ 
									FileWriter fw1=new FileWriter(file1);
									fw1.write(jtextarea.getText());
									fw1.close();
								} 
								catch(Exception e1)
								{ 
									System.out.println(e1);
								}
							}
							
							rvalue=jfc.showOpenDialog(jframe);
							path=jfc.getSelectedFile().getAbsolutePath();
							if(rvalue==jfc.APPROVE_OPTION)
							{
								File file=new File(jfc.getSelectedFile().getAbsolutePath());
								jframe.setTitle(file.getName());
								//System.out.println("HI");
								System.out.println(file.getAbsolutePath());
								try
								{ 
									FileReader fr=new FileReader(file);
									int i;
									while((i=fr.read())!=-1)
									{
										text+=""+(char)i;
									}
									fw.close();
									//System.out.println("HI");
									jtextarea.setText(""+text); 
								} 
								catch(Exception e1)
								{
									System.out.println(e1);
								}
							}
					}//if
					else if(rv==JOptionPane.NO_OPTION)
					{
						rvalue=jfc.showOpenDialog(jframe);
						path=jfc.getSelectedFile().getAbsolutePath();
						if(rvalue==jfc.APPROVE_OPTION)
						{
							File file=new File(jfc.getSelectedFile().getAbsolutePath());
							jframe.setTitle(file.getName());
							//System.out.println("HI");
							System.out.println(file.getAbsolutePath());
							try
							{ 
								FileReader fr=new FileReader(file);
								int i;
								while((i=fr.read())!=-1)
								{
									text+=""+(char)i;
								}
								fw.close();
								//System.out.println("HI");
								jtextarea.setText(""+text); 
							}
							catch(Exception e1)
							{
								System.out.println(e1);
							}
					    }
				   } 
			   }
			} 
		} 	
		 	//*********************//
		 	
		 	if(e.getSource()==menuitemNew) 
			{
				if(jtextarea.getText().equals(""))
				{
					jframe.setTitle("Untitled");
					count=0;
				}
				else
				{
					System.out.println("outside exist check");
					System.out.println(jframe.getTitle());
					if(jframe.getTitle()!="Untitled")   
					{
						System.out.println("exist checked");
						try  
						{
							FileReader fr=new FileReader(path);
							int i;
							while((i=fr.read())!=-1)
							{
								text1+=""+(char)i;
							}
						}
						catch(Exception e2)
						{
							System.out.println(e2);
						}
						if(jtextarea.getText().equals(text1))
						{
							jtextarea.setText("");
						}
						else {
						int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
						if(rv==JOptionPane.YES_OPTION)
						{
							try
							{ 
								FileWriter fw1=new FileWriter(path);
								fw1.write(jtextarea.getText());
								fw1.close();
							}  
							catch(Exception e1) 
							{ 
								System.out.println(e1);
							}
							jtextarea.setText("");
							jframe.setTitle("Untitled");
							count=0;
						}
						else if(rv==JOptionPane.NO_OPTION)
						{
							jframe.setTitle("Untitled");
							jtextarea.setText("");
							count=0;
						}
						}
					}
					else
					{
					int rv=JOptionPane.showConfirmDialog(jframe,"Do you wish to save existing data?");
					if(rv==JOptionPane.NO_OPTION)
					{
						jtextarea.setText("");
						count=0;
					}
					if(rv==JOptionPane.YES_OPTION)   
					{
						System.out.println("hiii1");
						rvalue=jfc.showSaveDialog(jframe);
					
						if(rvalue==jfc.APPROVE_OPTION) 
						{
							File file=new File(jfc.getSelectedFile().getAbsolutePath());
							jframe.setTitle(file.getName());
							System.out.println(file.getAbsolutePath());
							try
							{  
								FileWriter fw1=new FileWriter(file);
								fw1.write(jtextarea.getText());
								fw1.close();
								count=0;
							}
							catch(Exception e1)
							{
								System.out.println(e1);
							}
							count=1;
						}
						jframe.setTitle("Untitled"); 
						jtextarea.setText("");
						count=0;
					} 
				}
			}
		}
		 	//******************//
		 	if(e.getSource()==menuitemColor) 
		 	{ 
		 		jframe1=new JFrame("Color formatting");
		 		jccB=new JColorChooser();
		 		jccF=new JColorChooser();
		 		pB=new JPanel(new BorderLayout()); //new BorderLayout() has been done so that our panel comes in between the frame as by default panel is flow layout.
		 		pB.add(jccB);
		 		pF=new JPanel(new BorderLayout());
		 		pF.add(jccF);
		 		jccB.getSelectionModel().addChangeListener(this);
		 		jccF.getSelectionModel().addChangeListener(this);
		 		tp=new JTabbedPane();
		 		tp.setBounds(10,10,450,300);
		 		tp.add("BackGround",pB); 
		 		tp.add("ForeGround",pF);
		 		jExit=new JButton("Close");
		 		jExit.setBounds(200,320,80,40);
		 		jExit.addActionListener(this);
		 		jframe1.add(tp);
		 		jframe1.add(jExit);
		 		jframe1.setLayout(null);
		 		jframe1.setSize(500, 400);
		 		jframe1.setUndecorated(true);
		 		jframe1.setLocation(400, 0); 
				jframe1.setVisible(true); 
				System.out.println("Gone through");
		 	}	
		 	if(e.getSource()==jExit)
		 	{
		 		jframe1.dispose();
		 	}
		
		 	////////**********************/////////////////
		 	if(e.getSource()==menuitemFont)
		 	{ 
		 		jframe2=new JFrame("Font formatting");
		 		jframe2.setLayout(null);
		 		jcbFont=new JComboBox(); 
		 		jcbFont.setBounds(10, 10, 150, 40);
		 		GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment();  //Code for getting all fonts in this PC
		 		Font fonts[]=g.getAllFonts();											  //
		 		for(Font f:fonts)														  //
		 		{																		  //
		 			jcbFont.addItem(f.getFontName());									  //
		 		}																		  //Code ends here.
		 		
		 		String Styles[]= {"Bold","Plain","Italics"};
		 		jcbStyle=new JComboBox<String>(Styles);
		 		jcbStyle.setBounds(180, 10, 150, 40);
		 		Integer Sizes[]= {15,16,17,18,19,20};
		 		jcbSize=new JComboBox<Integer>(Sizes);
		 		jcbSize.setBounds(350, 10, 150, 40);
		 		jpreview=new JLabel("It's a beautiful day... :) ");
		 		jpreview.setBounds(20, 200, 300, 30);
		 		jPreview=new JButton("Preview");
		 		jPreview.setBounds(50,300,150,40);
		 		jApply=new JButton("Apply");
		 		jApply.setBounds(250,300,150,40);
		 		jPreview.addActionListener(this);
		 		jApply.addActionListener(this);
		 		jframe2.add(jcbFont);
		 		jframe2.add(jcbStyle);
		 		jframe2.add(jcbSize);
		 		jframe2.add(jpreview);
		 		jframe2.add(jPreview);
		 		jframe2.add(jApply);
		 		jframe2.setSize(550, 400);
		 		jframe2.setLocation(400, 0);
				//jframe.setResizable(false);//So that no size changes can be made.
				jframe2.setVisible(true);
		 	}   
		 	if(e.getSource()==jPreview)
		 	{
		 		System.out.println("hi");
		 		//jtextarea.setFont( new Font("Georgia",Font.BOLD,20));
		 		String font=jcbFont.getSelectedItem().toString();
		 		String str=jcbStyle.getSelectedItem().toString();
		 		int style=0; 
		 		if(str.equals("Bold"))
		 		{
		 			style=Font.BOLD;
		 		}
		 		if(str.equals("Italics"))
		 		{
		 			style=Font.ITALIC;
		 		}
		 		if(str.equals("Plain"))
		 		{
		 			style=Font.PLAIN;
		 		}
		 		System.out.println(style);
		 		int size=(Integer)jcbSize.getSelectedItem();
		 		System.out.println(size);
		 		jpreview.setFont(new Font(font,style,size));
		 	}
		 	if(e.getSource()==jApply)
		 	{
		 		System.out.println("hi");
		 		String font=jcbFont.getSelectedItem().toString();
		 		String str=jcbStyle.getSelectedItem().toString();
		 		int style=0;
		 		if(str.equals("Bold"))
		 		{
		 			style=Font.BOLD; 
		 		}
		 		if(str.equals("Italics"))
		 		{
		 			style=Font.ITALIC; 
		 		}
		 		if(str.equals("Plain"))
		 		{ 
		 			style=Font.PLAIN;
		 		}
		 		System.out.println(style);
		 		int size=(Integer)jcbSize.getSelectedItem(); 
		 		System.out.println(size);
		 		try
				{  
					FileWriter fow=new FileWriter("fontfile.txt");
					if(str.equals("Bold"))
			 		{
						fow.write(font+","+"Bold"+","+size);
			 			//style=Font.BOLD; 
			 		}
			 		if(str.equals("Italics"))
			 		{
			 			fow.write(font+","+"Italics"+","+size);
			 		}
			 		if(str.equals("Plain"))
			 		{ 
			 			fow.write(font+","+"Plain"+","+size);
			 		}
					fow.close();
					count=0;
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
		 		jtextarea.setFont(new Font(font,style,size));
		 		System.out.println(font+style+size); 
		 		jframe2.dispose();
		 	}
		 	if(e.getSource()==lcase) 
		 	{
		 		System.out.println("Entered lcase");
		 			if(lcase.isSelected())
		 			{
			 			ucase.setSelected(false);
			 			tcase.setSelected(false);
			  			wcase.setSelected(false);
			 			scase.setSelected(false);
			 			jtextarea.setText(jtextarea.getText().toLowerCase());
		 			}
		 	}
		 	if(e.getSource()==ucase) 
		 	{
		 		System.out.println("Entered ucase");
		 			if(ucase.isSelected())
		 			{
			 			lcase.setSelected(false);
			 			tcase.setSelected(false);
			 			wcase.setSelected(false);
			 			scase.setSelected(false);
			 			jtextarea.setText(jtextarea.getText().toUpperCase());
		 			}
		 	}
		 	if(e.getSource()==wcase) 
		 	{
		 		System.out.println("Entered wcase");
		 		text1="";
		 		faltu=""; 
		 		text1=jtextarea.getText(); 
		 		System.out.println(text1);
		 		
		 		char c[]=text1.trim().toCharArray();
		 		if(c[0]>='a' && c[0]<='z')
		 		{
		 			c[0]-=32;
		 		}
		 		for(int i=1;i<c.length;i++)
		 		{
		 			if(c[i]==' ' && c[i+1]>='a' && c[i+1]<='z')
		 			{
		 				c[i+1]-=32;
		 			}
		 			else if(c[i]>='A' && c[i]<='Z' && c[i-1]!=' ')
		 			{
		 				c[i]+=32;
		 			}
		 		}
		 		for(int i=0;i<c.length;i++)
		 		{
		 			faltu+=c[i];
		 		}
		 			if(wcase.isSelected())
		 			{
			 			ucase.setSelected(false);
			 			tcase.setSelected(false);
			 			lcase.setSelected(false);
			 			scase.setSelected(false);
			 			jtextarea.setText(faltu);
		 			}
		 	}
		 	if(e.getSource()==tcase)
		 	{
		 		text1="";
		 		faltu="";
		 		text1=jtextarea.getText();
		 		char c[]=text1.toCharArray();
		 		for(int i=0;i<c.length;i++)
		 		{
		 			if(c[i]>='a' && c[i]<='z')
		 					c[i]-=32;
		 			else if(c[i]>='A' && c[i]<='Z')
		 					c[i]+=32;
		 		}
		 		for(int i=0;i<c.length;i++)
		 		{
		 			faltu+=c[i];
		 		}
		 		if(tcase.isSelected()) 
	 			{
		 			ucase.setSelected(false);
		 			wcase.setSelected(false);
		 			lcase.setSelected(false);
		 			scase.setSelected(false);
		 			jtextarea.setText(faltu);
	 			}
		 	}
		 	if(e.getSource()==scase)
		 	{
		 		text1="";
		 		faltu="";
		 		System.out.println("entered scase");
		 		text1=jtextarea.getText();
		 		char c[]=text1.trim().toCharArray();
		 		if(c[0]>='a' && c[0]<='z')
		 			c[0]-=32;
		 		for(int i=1;i<c.length;i++)
		 		{ 
		 			if(c[i]>='A' && c[i]<='Z')
		 				c[i]+=32;
		 		}
		 		for(int i=0;i<c.length;i++)
		 		{
		 			faltu+=c[i];
		 		}
		 		if(scase.isSelected())
	 			{
		 			ucase.setSelected(false);
		 			wcase.setSelected(false);
		 			lcase.setSelected(false);
		 			tcase.setSelected(false);
		 			jtextarea.setText(faltu);
	 			} 
		 	}	 	
		 	if(e.getSource()==menuitemCut) 
		 	{
		 		jtextarea.cut();
		 	}
		 	if(e.getSource()==menuitemPaste)
		 	{
		 		jtextarea.paste();
		 	}
		 	if(e.getSource()==menuitemCopy)
		 	{
		 		jtextarea.copy();
		 	} 
		 	if(e.getSource()==menuitemDel)
		 	{
		 		jtextarea.setText(jtextarea.getText().replace(jtextarea.getSelectedText(),""));
		 	}
		 	if(e.getSource()==menuitemFind)
		 	{
		 		jframe3=new JFrame("Find");
		 		jframe3.setLayout(null);
		 		jlfind=new JLabel("To Find :");
		 		jlfind.setBounds(10,20,80,40);
		 		jtextfield=new JTextField(); 
		 		jtextfield.setBounds(80,20,120,40);
		 		jFind=new JButton("Find");
		 		jFind.setBounds(230, 20, 80, 40);
		 		jFind.addActionListener(this);
		 		jCancel=new JButton("Cancel");
		 		jCancel.setBounds(230, 70, 80, 40);
		 		jCancel.addActionListener(this); 
		 		matchcase=new JCheckBox("Matchcase");
		 		matchcase.setBounds(50, 120,100,40);
		 		jframe3.add(jlfind);
		 		jframe3.add(jtextfield);
		 		jframe3.add(jFind);
		 		jframe3.add(jCancel);
		 		jframe3.add(matchcase);
		 		jframe3.setSize(350,200);
		 		jframe3.setLocation(30, 200);
		 		jframe3.setVisible(true);
		 		System.out.println("left frame");  
		 	}
		 	if(e.getSource()==jCancel)
		 	{ 
		 		jframe3.dispose();
		 	} 
		 	if(e.getSource()==jFind)
		 	{
		 		int i,j;
		 		String datatofind=jtextfield.getText();
		 		String dataoftextarea=jtextarea.getText();
		 		if(matchcase.isSelected())
		 		{
		 			String b=datatofind.toLowerCase();
		 			String a=dataoftextarea.toLowerCase();
		 			for(i=0;i<a.length();i++)
			 		{
			 			for(j=0;j<b.length();j++)
			 			{
			 				if(a.charAt(i)!=b.charAt(j))
			 					break;
			 				i++;
			 			}
			 			if(j==b.length())
			 			{
			 				i--;
			 				int start=a.indexOf(b);
			 				int end=start+b.length()-1;
			 				System.out.println(start);
			 				System.out.println(end);
			 				System.out.println("data to find found");
			 				jtextarea.setSelectionStart(start);
			 				jtextarea.setSelectionEnd(end+1); 
			 				count=1;
			 			}
			 		} 
			 		if(count!=1) 
			 		{ 
			 			JOptionPane.showMessageDialog(jframe,"Not Found :(" );
			 		} 
			 		jframe3.dispose();
		 		}
		 		else
		 		{
		 			for(i=0;i<dataoftextarea.length();i++)
			 		{
			 			for(j=0;j<datatofind.length();j++)
			 			{
			 				if(dataoftextarea.charAt(i)!=datatofind.charAt(j))
			 					break;
			 				i++;
			 			}
			 			if(j==datatofind.length())
			 			{
			 				i--;
			 				int start=dataoftextarea.indexOf(datatofind);
			 				int end=start+datatofind.length()-1;
			 				System.out.println(start);
			 				System.out.println(end);
			 				System.out.println("data to find found");
			 				jtextarea.setSelectionStart(start);
			 				jtextarea.setSelectionEnd(end+1);
			 				count=1;
			 			}
			 		}
			 		if(count!=1)
			 		{
			 			JOptionPane.showMessageDialog(jframe,"Not Found :(" );
			 		}
			 		jframe3.dispose();
		 		}
		 		
		 	}
		 	fw.close();  
			}
		
		catch(Exception e2)
		{
			System.out.println(e2);
		}
}
	@Override
	public void stateChanged(ChangeEvent e) 
	{ 
		if(e.getSource()==jccB.getSelectionModel()) 
		{
			System.out.println("Entered Background");
			Color c=jccB.getColor();
			jtextarea.setBackground(c);
		}
		if(e.getSource()==jccF.getSelectionModel())
		{
			System.out.println("Entered Foreground");
			Color c=jccF.getColor();
			jtextarea.setForeground(c);
		}
	}
} 