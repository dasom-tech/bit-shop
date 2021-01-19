package main;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class MainClass {

	public static void main(String[] args) {
		new loginUI();

	}

}

class SearchID extends Frame implements WindowListener {
	
	TextField check;
	Button Searchbtn, backbtn;
	IDVO son;
	
	public SearchID() {
		setLayout(null);
		setTitle("아이디찾기");
		Label label = new Label("회원가입할때 사용한 이메일을 입력해주세요");
		label.setBounds(90, 100, 500, 20);
		add(label);
		
		check = new TextField();
		check.setBounds(150, 150, 100, 20);
		add(check);
		
		Searchbtn = new Button("아이디 찾기");
		Searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IDDAO dao = new IDDAO();
				String result = dao.findID(check.getText());
				
				if(result == null)
				{
					JOptionPane.showMessageDialog(null, "가입된 이메일이 없습니다.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + result +"입니다.");
				}
			}
		});

		backbtn = new Button("돌아가기");
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new loginUI();
				dispose();
			}
		});

		Searchbtn.setBounds(130, 220, 70, 20);
		backbtn.setBounds(205, 220, 60, 20);
		add(Searchbtn);
		add(backbtn);
		
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
		
	}


	@Override
	public void windowOpened(WindowEvent e) {}


	@Override
	public void windowClosing(WindowEvent e) {
	}


	@Override
	public void windowClosed(WindowEvent e) {}


	@Override
	public void windowIconified(WindowEvent e) {}


	@Override
	public void windowDeiconified(WindowEvent e) {}


	@Override
	public void windowActivated(WindowEvent e) {}


	@Override
	public void windowDeactivated(WindowEvent e) {}


}

class SearchPS extends Frame implements WindowListener {
	
	TextField checkEM, checkID;
	Button Searchbtn, backbtn;
	IDVO son;
	
	public SearchPS() {
		setLayout(null);
		setTitle("비밀번호찾기");
		Label label = new Label("회원가입할때 사용한 아이디와 이메일을 입력해주세요");
		label.setBounds(70, 100, 500, 20);
		add(label);
		
		checkID = new TextField();
		checkID.setBounds(150, 120, 100, 20);
		add(checkID);
		
		checkEM = new TextField();
		checkEM.setBounds(150, 150, 100, 20);
		add(checkEM);
		
		Searchbtn = new Button("비밀번호 찾기");
		Searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IDDAO dao = new IDDAO();
				String result = dao.findPS(checkEM.getText(), checkID.getText());
				
				if(result == null)
				{
					JOptionPane.showMessageDialog(null, "가입된 아이디나 이메일이 일치하지 않습니다.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "회원님의 비밀번호는 " + result +"입니다.");
				}
			}
		});

		backbtn = new Button("돌아가기");
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new loginUI();
				dispose();
			}
		});

		Searchbtn.setBounds(120, 220, 80, 20);
		backbtn.setBounds(210, 220, 60, 20);
		add(Searchbtn);
		add(backbtn);
		
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
		
	}


	@Override
	public void windowOpened(WindowEvent e) {}


	@Override
	public void windowClosing(WindowEvent e) {
	}


	@Override
	public void windowClosed(WindowEvent e) {}


	@Override
	public void windowIconified(WindowEvent e) {}


	@Override
	public void windowDeiconified(WindowEvent e) {}


	@Override
	public void windowActivated(WindowEvent e) {}


	@Override
	public void windowDeactivated(WindowEvent e) {}


}

class loginUI extends Frame implements WindowListener
{
	TextField IDT, PASSWORDT;
	Button loginbtn, signinbtn, loseID, losePS;
	Label label, idl, psl;
	public static String sendID;
	
	public loginUI()
	{
		setLayout(null);
		 IDDAO dao = new IDDAO();
	     String lastid = dao.lastID();

		//set position, etc
		setTitle("비트상회");
		  if(lastid == null)
	      {
	         IDT = new TextField();
	      }
	      else
	      {
	         IDT = new TextField(lastid);
	      }

		idl = new Label("ID : ");
		psl = new Label("PW : ");
		
		PASSWORDT = new TextField();
		idl.setBounds(135, 130, 50, 20);
		psl.setBounds(135, 160, 50, 20);
		IDT.setBounds(165, 130, 100, 20);
		PASSWORDT.setBounds(165, 160, 100, 20);
		PASSWORDT.setEchoChar('*');
		
		loginbtn = new Button("로그인하기");
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String IDS, PWS;
				IDS = IDT.getText();
				PWS = PASSWORDT.getText();
				
				if(IDS.isEmpty() || PWS.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "빈 항목이 있으면 안됩니다.");
				}
				else
				{
					Boolean loc = false;
					loc = dao.LoginCheck(IDS, PWS);
					
					System.out.println(loc);
					
					if(loc)
					{
						dao.UpdatelastID(IDS);
						sendID = IDS;
						new MenuView();
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "없는 아이디거나 비밀번호입니다.");
					}
				}
			}
		});
		
		signinbtn = new Button("회원가입");
		signinbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SignInUI();
				
			}
		});
		
		loseID = new Button("아이디 찾기");
		loseID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SearchID();
				dispose();
			}
		});
		
		losePS = new Button("비밀번호 찾기");
		losePS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SearchPS();
				dispose();
				
			}
		});
		
		
		
		loginbtn.setBounds(125, 220, 80, 20);
		signinbtn.setBounds(210, 220, 80, 20);
		loseID.setBounds(125, 250, 80, 20);
		losePS.setBounds(210, 250, 80, 20);
		
		label = new Label("welcome, 비트상회! 로그인 해주세요~");
		label.setBounds(105,30,250,100);
		
		//set position, etc
		
		//add items
		this.add(IDT);
		this.add(PASSWORDT);
		this.add(loginbtn);
		this.add(signinbtn);
		this.add(label);
		this.add(loseID);
		this.add(losePS);
		this.add(idl);
		this.add(psl);
		//add items
		
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
	}

	private void showMessageDialog(Object object, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class SignInUI extends Frame implements WindowListener
{
	TextField IDT, PASSWORDT, NAMET, EMAILT, PHONET, ADDRT;
	Button signinbtn;
	Label label, idl, psl, nal, eml, phonel, addrl;
	
	public SignInUI()
	{
		setLayout(null);
		
		//set position, etc
		setTitle("회원가입");
		IDT = new TextField();
		PASSWORDT = new TextField();
		NAMET = new TextField();
		EMAILT = new TextField();
		PHONET = new TextField();
		ADDRT = new TextField();
		idl = new Label("아이디   : ");
		psl = new Label("비밀번호 : ");
		nal = new Label("이름 : ");
		eml = new Label("이메일: ");
		phonel = new Label("휴대폰: ");
		addrl = new Label("주소 : ");
		
		
		idl.setBounds(130, 100, 100, 20);
		psl.setBounds(130, 120, 100, 20);
		nal.setBounds(130, 140, 100, 20);
		eml.setBounds(130, 160, 100, 20);
		phonel.setBounds(130, 180, 100, 20);
		addrl.setBounds(130, 200, 100, 20);
		
		
		IDT.setBounds(190, 100, 100, 20);
		PASSWORDT.setBounds(190, 120, 100, 20);
		NAMET.setBounds(190, 140, 100, 20);
		EMAILT.setBounds(190, 160, 100, 20);
		PHONET.setBounds(190, 180, 100, 20);
		ADDRT.setBounds(190, 200, 100, 20);
		
		PASSWORDT.setEchoChar('*');
		
		signinbtn = new Button("완료");
		signinbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ids, pws, nas, ems, addrs, phones; 
				Integer points;
				ids = IDT.getText();
				pws = PASSWORDT.getText();
				nas = NAMET.getText();
				ems = EMAILT.getText();
				phones = PHONET.getText();
				addrs = ADDRT.getText();
				points = 2000;
				
				IDDAO dao = new IDDAO();
				
				if(ids.isEmpty() || pws.isEmpty() || nas.isEmpty() || ems.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "빈 항목이 있으면 안됩니다.");
				}else
				{
					if(dao.check(ids) == true)
					{
						JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
					}
					else
					{
						Boolean resultb = true;
						resultb = dao.RejoinCheck(nas, phones);
						System.out.println("re" + resultb);
					
						if(resultb)
						{
							points = 0;
							IDVO NEWMEM = new IDVO(ids, pws, nas, ems, phones, addrs, points);
							int result = dao.insertData(NEWMEM);
							System.out.println(result);
							JOptionPane.showMessageDialog(null, "재가입자에겐 포인트가 지급되지 않습니다.");				
							dispose();
							
						}
						else
						{
							IDVO NEWMEM = new IDVO(ids, pws, nas, ems, phones, addrs, points);
							int result = dao.insertData(NEWMEM);
							System.out.println(result);
							JOptionPane.showMessageDialog(null, "첫가입 축하! 2000 포인트가 지급되었습니다~");				
							dispose();
						}
					}
				}
				
			}
			
		});
		signinbtn.setBounds(180, 240, 60, 20);
		
		label = new Label("정보를 입력해주세요~^^");
		label.setBounds(145,60,150,20);
		
		//set position, etc
		
		//add items
		this.add(IDT);
		this.add(PASSWORDT);
		this.add(NAMET);
		this.add(EMAILT);
		this.add(PHONET);
		this.add(ADDRT);
		this.add(signinbtn);
		this.add(label);
		this.add(idl);
		this.add(psl);
		this.add(nal);
		this.add(eml);
		this.add(phonel);
		this.add(addrl);
		//add items
		
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class loginIN extends Frame implements WindowListener
{
	Label label;
	Label inf1,inf2,inf3,inf4, inf5, inf6, inf7, inf8;
	Button signoutbtn, updatebtn, mainbtn;
	
	public loginIN()
	{
		setLayout(null);
		
		IDDAO dao = new IDDAO();
		IDVO svo = dao.selectId(loginUI.sendID);
		
		//set position, etc
		setTitle("회원정보");
		label = new Label();
		label.setBounds(110,50,450,20);
		
		signoutbtn = new Button("회원탈퇴");
		signoutbtn.setBounds(140,240,60,30);
		signoutbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new memberSignOut();
				dispose();
			}
		});
		
		updatebtn = new Button("정보수정");
		updatebtn.setBounds(265,240,60,30);
		updatebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateMem();
				dispose();
			}
		});
		
		mainbtn = new Button("메인화면");
		mainbtn.setBounds(380,240,60,30);
		mainbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MenuView();
				dispose();
			}
		});
	
		inf1 = new Label("ID    : " + svo.getID());
		inf1.setBounds(110,70,380,20);
		inf1.setBackground(Color.lightGray);
		inf2 = new Label("PW    : " + svo.getPW());
		inf2.setBounds(110,90,380,20);
		inf2.setBackground(Color.lightGray);
		inf3 = new Label("NAME  : " + svo.getNA());
		inf3.setBounds(110,110,380,20);
		inf3.setBackground(Color.lightGray);
		inf4 = new Label("EMAIL : " + svo.getEM());
		inf4.setBounds(110,130,380,20);
		inf4.setBackground(Color.lightGray);
		inf5 = new Label("PHONE : " + svo.getPHONE());
		inf5.setBounds(110,150,380,20);
		inf5.setBackground(Color.lightGray);
		inf6 = new Label("ADDRESS : " + svo.getADDR());
		inf6.setBounds(110,170,380,20);
		inf6.setBackground(Color.lightGray);
		inf7 = new Label("MONEY : " + svo.getMONEY());
		inf7.setBounds(110,190,380,20);
		inf7.setBackground(Color.lightGray);
		inf8 = new Label("POINT : " + svo.getPOINT());
		inf8.setBounds(110,210,380,20);
		inf8.setBackground(Color.lightGray);
		
		//set position, etc
		
		//add items
		this.add(label);
		this.add(inf1);
		this.add(inf2);
		this.add(inf3);
		this.add(inf4);
		this.add(inf5);
		this.add(inf6);
		this.add(inf7);
		this.add(inf8);
		this.add(signoutbtn);
		this.add(updatebtn);
		this.add(mainbtn);
		//add items
		
		
		setSize(600,400);
		addWindowListener(this);
		setVisible(true);
	}
	
	public loginIN(int i) {
		// 아무것도 안함
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class memberSignOut extends Frame implements WindowListener {
	// 회원탈퇴
	TextField checkid, checkpw;
	Button deletebtn, backbtn;
	IDVO son;
	Label idl, psl;
	public memberSignOut() {
		setLayout(null);
		
		setTitle("회원탈퇴");
		Label label = new Label("정말 탈퇴하시는건가요?ㅠㅠ");
		label.setBounds(130, 100, 500, 20);
		add(label);
		
		
		idl = new Label("ID : ");
		psl = new Label("PW : ");
		idl.setBounds(120, 150, 50, 20);
		psl.setBounds(120, 180, 50, 20);
		checkid = new TextField();
		checkid.setBounds(150, 150, 100, 20);
		checkpw = new TextField();
		checkpw.setBounds(150, 180, 100, 20);
		add(checkid);
		add(checkpw);
		
		deletebtn = new Button("회원탈퇴");
		deletebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원탈퇴버튼 누르면 id, pw 일치하는지 확인 후...
				// DBMS를 통해서 이름과 전화번호를 singoutmember테이블에 저장 & 기존 mem테이블에서는 삭제
				IDDAO dao = new IDDAO();
				boolean nullcheck = dao.isEmpty(checkid.getText(), checkpw.getText());
				if(nullcheck) { 
					JOptionPane.showMessageDialog(null, "빈칸없이 입력요망");
				}
				else {
					int success = dao.checkUser(checkid.getText(), checkpw.getText()); // 체크하고 맞으면 탈퇴
					if(success == 0) {
						JOptionPane.showMessageDialog(null, "탈퇴실패 아이디와 비번 정확히 입력");
					} else if( success > 0){
						JOptionPane.showMessageDialog(null, "탈퇴되었습니다~");

						new loginUI();
						dispose();
					}
				}
			}
		});

		// 탈퇴취소버튼
		backbtn = new Button("취소");
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: 탈퇴취소버튼
				// 누르면 메인으로 돌아감
				new loginIN();
				dispose();
			}
		});

		deletebtn.setBounds(135, 220, 60, 20);
		backbtn.setBounds(205, 220, 60, 20);
		add(deletebtn);
		add(backbtn);
		this.add(idl);
		this.add(psl);
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
		
	}


	@Override
	public void windowOpened(WindowEvent e) {}


	@Override
	public void windowClosing(WindowEvent e) {
	}


	@Override
	public void windowClosed(WindowEvent e) {}


	@Override
	public void windowIconified(WindowEvent e) {}


	@Override
	public void windowDeiconified(WindowEvent e) {}


	@Override
	public void windowActivated(WindowEvent e) {}


	@Override
	public void windowDeactivated(WindowEvent e) {}


}




class MenuView extends Frame implements WindowListener, ActionListener {
	   
	   Label label;
	   Button order, info, logout,charge, myorder;
	   
	   IDDAO dao = new IDDAO();
	   ProductVO pvo;
	   int[] itemNo;
	   Panel panel[] = new Panel[3];
	   Button btn[] = new Button[3];
	   Label pname[] = new Label[3];
	   Label price[] = new Label[3];
	   Label pinfo[] = new Label[3];
	   int[] pno = new int[3];

	   public MenuView() {
	      setLayout(null);
	      
	      //set position, etc
	      	setTitle("비트상회 메인");
	         label = new Label();
	         label.setBounds(150,50,180,20);
	         
	         // 상품번호를 정수배열로 받아옴
	         itemNo = dao.selectPno();
	         // itemNo에서 상품을 랜덤으로 3개 뽑음
	         Set<Integer> random = new HashSet<Integer>();
	         
	         for (int i = 0; i < 40; i++) {
	            int index = (int)( (Math.random() * itemNo.length ) );
	            int ranNum = itemNo[index];
	            random.add(ranNum);         
	            if(random.size() == 3) {
	               break;
	            }
	         }
	         
	         for (int i = 0; i < panel.length; i++) {
	            panel[i] = new Panel();
	            panel[i].setBounds(40, 50+50*i, 500, 30);
	            this.add(panel[i]);
	            
	            btn[i] = new Button("구입하기");
	            btn[i].setBounds(550, 50+50*i, 70, 30);
	            btn[i].addActionListener(this);
	            this.add(btn[i]);
	         }
	         
	         int i = 0;
	         for (int item : random) {
	            pvo = dao.selectItem(item);
	            pname[i]= new Label( pvo.getPNAME() );
	            price[i]= new Label( pvo.getPRICE().toString() + "원");
	            pinfo[i]= new Label( pvo.getPINFO() );
	            
	            panel[i].add(pname[i]);
	            panel[i].add(price[i]);
	            panel[i].add(pinfo[i]);
	            
	            pno[i] = item;
	            
	            i++;
	         }
	         
	         order = new Button("상품주문");
	         order.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new PrdtOrder();
	            	dispose();
	            }
	         });
	         
	         info = new Button("회원정보");
	         info.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new loginIN();
	               dispose();
	            }
	         });

	         logout = new Button("로그아웃");
	         logout.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new loginUI();
	               dispose();
	            }
	         });
	         
	         charge = new Button("충전하기");
	         charge.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new chargeMoney();
	               dispose();
	            }
	         });
	         myorder = new Button("주문내역");
	         myorder.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					new myorderPage();
					dispose();
				}
	         });
	         myorder.setBounds(200, 220, 80, 40);
	         this.add(myorder);
			
			
	         order.setBounds(100,220,80,40);
	         info.setBounds(400,220,80,40);
	         logout.setBounds(500, 220, 80, 40);
	         charge.setBounds(300, 220, 80, 40);
	         
	         
	      //set position, etc
	      //add items
	         this.add(label);
	         this.add(order);
	         this.add(info);
	         this.add(logout);
	         this.add(charge);
	      //add items
	      
	      setSize(700,300);
	      addWindowListener(this);
	      setVisible(true);
	   }

	   @Override
	   public void windowOpened(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void windowClosing(WindowEvent e) {
	      // TODO Auto-generated method stub
	      System.exit(0);
	   }

	   @Override
	   public void windowClosed(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void windowIconified(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void windowDeiconified(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void windowActivated(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void windowDeactivated(WindowEvent e) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void actionPerformed(ActionEvent e) {       // 상품구입용 버튼액션추가
	      Object obj = e.getSource();
	      for (int i = 0; i < btn.length; i++) {
	         if( obj==btn[i] )new itemBuy(pno[i]);
	      }
	   }
}

class chargeMoney extends Frame implements WindowListener
{
	
	Label label;
	
	public chargeMoney() {
		setLayout(null);
		setTitle("머니충전");
		String[] text = {"10000원", "30000원", "50000원", "100000원"};
		Button[] moneybtn = new Button[4];
		
		Button mainbtn;
		//set position, etc
			int x = 115;
			int y = 100;
			int cnt = 0;
			
			label = new Label("얼마를 지갑에 넣을까요?^^");
			label.setBounds(130,50,180,20);
			
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					moneybtn[cnt] = new Button(text[cnt]);
					moneybtn[cnt].setBounds(x, y, 70, 20);
					this.add(moneybtn[cnt]);
					
					x = x + 100;
					cnt++;
					
				}
				y = y + 70;
				x = 115;
			}
			moneybtn[0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						
						IDDAO dao = new IDDAO();
						System.out.println(loginUI.sendID);
						
						Integer moneys = 10000;
						int havemoney = dao.loadMoney(loginUI.sendID);
						
						boolean ok = dao.check(loginUI.sendID);
						
						try {
							ok = dao.Charge(loginUI.sendID, havemoney+moneys);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						System.out.println(ok);
						JOptionPane.showMessageDialog(null, "10,000원 충전 되었습니다.");
						new loginIN(); 
						dispose();
				}
			});
			moneybtn[1].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						
						IDDAO dao = new IDDAO();
						System.out.println(loginUI.sendID);
						
						Integer moneys = 30000;
						int havemoney = dao.loadMoney(loginUI.sendID);
						
						boolean ok = dao.check(loginUI.sendID);
						
						try {
							ok = dao.Charge(loginUI.sendID, havemoney+moneys);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						System.out.println(ok);
						JOptionPane.showMessageDialog(null, "30,000원 충전 되었습니다.");
						new loginIN(); 
						dispose();
				}
			});
			moneybtn[2].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						
						IDDAO dao = new IDDAO();
						System.out.println(loginUI.sendID);
						
						Integer moneys = 50000;
						int havemoney = dao.loadMoney(loginUI.sendID);
						
						boolean ok = dao.check(loginUI.sendID);
						
						try {
							ok = dao.Charge(loginUI.sendID, havemoney+moneys);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						System.out.println(ok);
						JOptionPane.showMessageDialog(null, "50,000원 충전 되었습니다.");
						new loginIN(); 
						dispose();
				}
			});
			moneybtn[3].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						
						IDDAO dao = new IDDAO();
						System.out.println(loginUI.sendID);
						
						Integer moneys = 100000;
						int havemoney = dao.loadMoney(loginUI.sendID);
						
						boolean ok = dao.check(loginUI.sendID);
						
						try {
							ok = dao.Charge(loginUI.sendID, havemoney+moneys);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						System.out.println(ok);
						JOptionPane.showMessageDialog(null, "100,000원 충전 되었습니다.");
						new loginIN(); 
						dispose();
				}
			});
			mainbtn = new Button("메인화면");
			mainbtn.setBounds(170,240,60,30);
			mainbtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new MenuView();
					dispose();
				}
			});
		//set position, etc
		//add items
		this.add(label);
		this.add(mainbtn);
		//add items
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class UpdateMem extends Frame implements WindowListener
{
	TextField IDT, PASSWORDT, NAMET, EMAILT, PHONET, ADDRT;
	Button signinbtn;
	Label label, idl, psl, nal, eml, phonel, addrl;
	
	public UpdateMem()
	{
		setLayout(null);
		
		setTitle("회원정보수정");
		//set position, etc
		IDT = new TextField();
		IDT.setEditable(false);
		IDT.setText(loginUI.sendID);
		PASSWORDT = new TextField();
		NAMET = new TextField();
		EMAILT = new TextField();
		PHONET = new TextField();
		ADDRT = new TextField();
		idl = new Label("아이디   : ");
		psl = new Label("비밀번호 : ");
		nal = new Label("이름 : ");
		eml = new Label("이메일: ");
		phonel = new Label("휴대폰: ");
		addrl = new Label("주소 : ");
		
		
		idl.setBounds(130, 80, 100, 20);
		psl.setBounds(130, 100, 100, 20);
		nal.setBounds(130, 120, 100, 20);
		eml.setBounds(130, 140, 100, 20);
		phonel.setBounds(130, 160, 100, 20);
		addrl.setBounds(130, 180, 100, 20);
		
		
		IDT.setBounds(190, 80, 100, 20);
		PASSWORDT.setBounds(190, 100, 100, 20);
		NAMET.setBounds(190, 120, 100, 20);
		EMAILT.setBounds(190, 140, 100, 20);
		PHONET.setBounds(190, 160, 100, 20);
		ADDRT.setBounds(190, 180, 100, 20);
		
		PASSWORDT.setEchoChar('*');
		
		signinbtn = new Button("수정 완료");
		signinbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String ids, pws, nas, ems, addrs, phones; 
				Integer points;
				ids = IDT.getText();
				pws = PASSWORDT.getText();
				nas = NAMET.getText();
				ems = EMAILT.getText();
				phones = PHONET.getText();
				addrs = ADDRT.getText();
				points =  2000;
				
				
				if(pws.isEmpty() || nas.isEmpty() || ems.isEmpty() || phones.isEmpty() ||
						addrs.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "빈 항목이 있으면 안됩니다.");
				}else
				{
					IDDAO dao = new IDDAO();
					System.out.println(loginUI.sendID);
					IDVO son = new IDVO(loginUI.sendID,pws,nas,ems,phones,addrs);
					
					
					boolean ok = dao.check(loginUI.sendID);
					if(ok)
					{
						try {
							ok = dao.updateMember(son);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						System.out.println(ok);
						JOptionPane.showMessageDialog(null, "회원 정보가 수정되었습니다~");				
						new MenuView();
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");	
					}
				}
				
			}
			
		});
		signinbtn.setBounds(180, 240, 60, 20);
		
		label = new Label();
		label.setBounds(165,10,100,20);
		
		//set position, etc
		
		//add items
		this.add(IDT);
		this.add(PASSWORDT);
		this.add(NAMET);
		this.add(EMAILT);
		this.add(PHONET);
		this.add(ADDRT);
		this.add(signinbtn);
		this.add(label);
		this.add(idl);
		this.add(psl);
		this.add(nal);
		this.add(eml);
		this.add(phonel);
		this.add(addrl);
		//add items
		
		
		setSize(400,300);
		addWindowListener(this);
		setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		new MenuView();
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class myorderPage extends Frame implements WindowListener
{
   Label label;
   IDDAO dao = new IDDAO();
   
   public static int howmany;
   public static int cnt = 0;
   
   public myorderPage() {
      setLayout(null);
      Button mainbtn;
      
      //set position, etc
      setLayout(null);
      setTitle("주문내역");
      int howManyReviews = dao.reviewCount(loginUI.sendID);
      howmany = howManyReviews;
      
      ProductVO[] pvo = new ProductVO[howManyReviews];
      Panel pinfo[] = new Panel[howManyReviews];
      Label namel[] = new Label[howManyReviews];
      Label pricel[] = new Label[howManyReviews];
      Label amountl[] = new Label[howManyReviews];
      int y = 50;
      
      for (int i = 0; i < howManyReviews; i++) {
         pvo[i] = dao.selectItem(dao.selectProduct(loginUI.sendID, i+1));
         pinfo[i] = new Panel();
         pinfo[i].setBounds(10, y, 400, 50);
         
         String name = pvo[i].getPNAME();
         String info = pvo[i].getPINFO();
         int price= pvo[i].getPRICE();
         int pno = pvo[i].getPNO();
         
         namel[i] = new Label();
         namel[i].setText(name);
         pinfo[i].add(namel[i]);
         pricel[i] = new Label();
         pricel[i].setText("" + price);
         pinfo[i].add(pricel[i]);
         
         amountl[i] = new Label();
         amountl[i].setText("갯수 : " + dao.loadamount(loginUI.sendID, i+1) + " 총액 : " + price * dao.loadamount(loginUI.sendID, i+1));
         pinfo[i].add(amountl[i]);
         
         cnt = cnt + (price * dao.loadamount(loginUI.sendID, i+1));
         
         this.add(pinfo[i]);
         
         y = y + 40;
      }
      
      
      mainbtn = new Button("메인화면");
      mainbtn.setBounds(170,600,60,30);
      mainbtn.setVisible(true);
      mainbtn.addActionListener(new ActionListener() {
            
         @Override
         public void actionPerformed(ActionEvent e) {
            new MenuView();
            dispose();
         }
      });
      //set position, etc
      //add items
      this.add(mainbtn);
      //add items
      
      setSize(400,700);
      addWindowListener(this);
      setVisible(true);
   }
   
   @Override
   public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowClosing(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowActivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }
}

//상품구입
class itemBuy extends Frame implements WindowListener, ActionListener{
 
 IDDAO dao = new IDDAO(); 
 Button plusb, minusb, buyb, backb;
 Label totprice;
 Label namel, pricel, infol;
 TextField purquan;
 int count, price, tot, pno;
 
 public itemBuy() {
 }
 
 public itemBuy(int pno) {
    ProductVO pvo;
    setLayout(null);
    setTitle("구매하기");
    this.pno = pno;
    
    pvo = dao.selectItem(pno);
    String name = pvo.getPNAME();
    String info = pvo.getPINFO();
    price = pvo.getPRICE();
    
    
    Panel pinfo = new Panel();
    pinfo.setBounds(100, 100, 400, 100);
    namel = new Label();
    namel.setText(name);
    namel.setBounds(10, 10, 400, 30);
    pinfo.add(namel);
    
    pricel = new Label();
    pricel.setText("" + price + " 원");
    pricel.setBounds(10, 35, 400, 30);
    pinfo.add(pricel);
    
    infol = new Label();
    infol.setText(info);
    infol.setBounds(10, 70, 400, 30);
    pinfo.add(infol);
    
    this.add(pinfo);
    
    count = 1;
    
    purquan = new TextField();
    purquan.setText(count + "");
    purquan.setBounds(240, 200, 70, 30); 
    purquan.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
          count = Integer.parseInt(purquan.getText());
          tot = price * count;
          totprice.setText( "총 금액 : " + tot + " 원");
       }
    });
    
    totprice = new Label();
    totprice.setBounds(400, 200, 200, 30);
    totprice.setText( "총 금액 : " + price + " 원");

    minusb = new Button("-");
    minusb.setBounds(180, 200, 50, 30);
    minusb.addActionListener(this);
    
    plusb = new Button("+");
    plusb.setBounds(320, 200, 50, 30);
    plusb.addActionListener(this);
    
    buyb = new Button("결제하기");
    buyb.setBounds(150, 250, 100, 30);
    buyb.addActionListener(this);
    
    backb = new Button("구입취소");
    backb.setBounds(300, 250, 100, 30);
    backb.addActionListener(this);
    
    this.add(purquan);
    this.add(totprice);
    this.add(minusb);
    this.add(plusb);
    this.add(buyb);
    this.add(backb);

    setSize(700,400);
    addWindowListener(this);
    setVisible(true);
    
 }
 
 
 @Override
 public void actionPerformed(ActionEvent e) {
    Button btn = (Button)e.getSource();
    String title = btn.getLabel();
    
    if(title.equals("+")) {
       if(count < 100) {
          count++;
          tot = price * count;
          purquan.setText(count + "");      // count 출력
          totprice.setText( "총 금액 : " + tot + " 원");
       }
    }
    else if(title.equals("-")) {   
       if(count > 1) {      
          count--;
          tot = price * count;
          purquan.setText(count + "");      // count 출력
          totprice.setText( "총 금액 : " + tot + " 원");
       } else if ( count == 1) {
          JOptionPane.showMessageDialog(null, "최초 구매수량은 1개입니다");
       }
    }
    else if(title.equals("결제하기")) {
       count = Integer.parseInt(purquan.getText());
       tot = price * count;
       if(count == 1) tot = price;     // count설정을 안하고 기본값(1)상태로 넘어가면 금액이 0으로 나와서 추가한 코드
       
       new itemPay(tot, count , loginUI.sendID, pno );   // 결제화면
    }
    else if(title.equals("구입취소")) {
           this.dispose();
    }

 }

 
 @Override
 public void windowOpened(WindowEvent e) {
 }

 @Override
 public void windowClosing(WindowEvent e) {
 }

 @Override
 public void windowClosed(WindowEvent e) {
 }

 @Override
 public void windowIconified(WindowEvent e) {
 }

 @Override
 public void windowDeiconified(WindowEvent e) {   
 }

 @Override
 public void windowActivated(WindowEvent e) {
 }

 @Override
 public void windowDeactivated(WindowEvent e) {
 }
 
}


//상품결제
class itemPay extends Frame implements WindowListener {
 
 IDDAO dao = new IDDAO();
 IDVO vo = new IDVO();
 ProductVO pvo = new ProductVO();
 
 Label totprice, itemcount, money, point, plabel;
 TextField pointTf;
 Button ok, back;
 
 int pno, price, tot, count, usepoint, finalpay;
 int balpo, baldon;      // ( bal -> balance 잔액)
 String id;
 
 public itemPay() {
 }
 
 public itemPay(int tot, int count, String id, int pno) {
    setLayout(null);
    setTitle("결제하기");
    this.id = id;
    this.pno = pno;
    this.tot = tot;
    this.count = count;
    
    vo = dao.selectId(loginUI.sendID);
    int don = vo.getMONEY();            // 소지금
    int po = vo.getPOINT();               // 소지포인트
    
    pvo = dao.selectItem(pno);            // 재고반영할 부분
    int pstock = (pvo.getPSTOCK() - count);   // 상품재고 - 구매수량
    
    totprice = new Label(); 
    totprice.setText("금액 : " + tot + " 원");
    totprice.setBounds(100, 60, 100, 30);
    itemcount = new Label();
    itemcount.setText("구입수량 : " + count + " 개");
    itemcount.setBounds(100, 100, 100, 30);
    
    money = new Label();
    money.setText("현재 지갑잔액: " + don + "원");
    money.setBounds(100, 160, 150, 30);
    point = new Label();
    point.setText("현재 포인트잔액: " + po);
    point.setBounds(350, 160, 150, 30);
    
    // 지갑 - 포인트 사용할 건지
    plabel = new Label("사용할 포인트 입력");
    plabel.setBounds(100, 200, 150, 30);
    pointTf = new TextField(20);
    pointTf.setText("0");         // 기본값 : 0
    pointTf.setBounds(350, 200, 100, 30);
    
    ok = new Button("결제");
    ok.setBounds(150, 250, 100, 30);
    ok.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
          usepoint = Integer.parseInt(pointTf.getText());      // 사용포인트
          
          finalpay = tot - usepoint;      // 최종결제금액 = 총금액 - 사용포인트
          baldon = don - finalpay;      // 지갑에 남은 돈 (지갑돈 - 최종결제금액)
          balpo = (po - usepoint) + (int)(finalpay * 0.005);     // 남은 포인트 (실결제액의 0.5%적립);       
          

          if(don < finalpay ) {
             JOptionPane.showMessageDialog(null, "지갑을 충전 후 사용해주세요^^");         
          } else {
             
             if(usepoint > po) {
                JOptionPane.showMessageDialog(null, "소지한 포인트보다 많은 포인트는 사용불가~");   
                return;
             } 
             else if(usepoint > tot) {
                JOptionPane.showMessageDialog(null, "결제할 금액만큼 포인트가 차감됩니다");
                finalpay = 0;
                baldon = don;
                balpo = po - tot;
             }
             
             dao.insertReviewList( loginUI.sendID, pno, count);   // 리뷰리스트에 저장(별점,후기는 null)
             dao.updatePstock(pno, pstock);      // product 재고반영
             JOptionPane.showMessageDialog(null, (int)(finalpay * 0.005) + "포인트가 지급되었습니다.^^"); 
             new balance(baldon, balpo);         // 잔액출력 & 결제완료창
             dispose();
          }
       }
    });
    
    back = new Button("취소");
    back.setBounds(300, 250, 100, 30);
    back.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
          dispose();
       }
    });
       
       this.add(totprice);
       this.add(itemcount);
       this.add(money);
       this.add(point);
       this.add(plabel);
       this.add(pointTf);
       this.add(ok);
       this.add(back);
       
       setSize(700,400);
       addWindowListener(this);
       setVisible(true);
       
    }

 @Override
 public void windowOpened(WindowEvent e) {
 }

 @Override
 public void windowClosing(WindowEvent e) {
 }

 @Override
 public void windowClosed(WindowEvent e) {
 }

 @Override
 public void windowIconified(WindowEvent e) {
 }

 @Override
 public void windowDeiconified(WindowEvent e) {
 }

 @Override
 public void windowActivated(WindowEvent e) {
 }

 @Override
 public void windowDeactivated(WindowEvent e) {
 }
 
}   	

//잔액출력 & 결제완료창
class balance extends Frame implements WindowListener {
	
	Label label1, label2, label3, label4, label5;
	Button ok;
	int baldon, balpo;
	
	public balance(int baldon, int balpo) {
		IDDAO dao = new IDDAO();
		
		setLayout(null);
		this.baldon = baldon;
		this.balpo = balpo;
		
		label1 = new Label("결제가 완료되었습니다");
		label1.setBounds(100, 100, 200, 20);
		
		label2 = new Label("지갑 잔액 : " + baldon);
		label2.setBounds(100, 150, 200, 20);
		label3 = new Label("포인트 잔액 : " + balpo);
		label3.setBounds(100, 200, 200, 20);
		
		ok = new Button("확인");
		ok.setBounds(100, 300, 100, 20);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dao.updateBalance( loginUI.sendID, baldon, balpo);
				try {
					
	            if(PrdtOrder.ta.isEnabled())
	            {
	               PrdtOrder.ta.append("" + PrdtOrder.show + "      " + PrdtOrder.price[PrdtOrder.j] + "       " + PrdtOrder.count + "        " + PrdtOrder.price[PrdtOrder.j] * PrdtOrder.count
	                        + "원" + "\n");
	            }
				}catch (NullPointerException e2) {
				}
				dispose();
			}
		});
	
		add(label1);		
		add(label2);
		add(label3);
		add(ok);
		
		
		setSize(700,400);
		addWindowListener(this);
		setVisible(true);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	
}

class PrdtOrder extends Frame implements WindowListener {
    static public int count = 0;
    static public String show = "";
    static public boolean ok = false;
    static public int j;
    public static TextArea ta;
    static public int price[] = { 10000, 14000, 4300, 3000, 13000, 5000, 20000, 5000 };
 
    public PrdtOrder() {
 
        // 프레임 설정
        setTitle("상품 주문");
        setBounds(0, 0, 625, 600);
        setBackground(Color.black);
 
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 255, 215));
        pNorth.setLayout(null);
        pNorth.setSize(0, 400);
 
        // 배열 설정 부분
        ProductVO pro = new ProductVO();
        String menu[] = { "케일(500g)", "적두(500g)", "바나나(1팩)", "브로콜리(1송이)", "딸기잼(700g)", "찹쌀가루(500g)", "황설탕(5kg)", "화장솜(2팩)" };       
        int pno[] = {1001,1002,1003,1004,2001,2002,2003,3003};
        
        Button bt[] = new Button[menu.length];
        TextField num[] = new TextField[menu.length];
        Button minus[] = new Button[menu.length];
        Button plus[] = new Button[menu.length];
        Button ok[] = new Button[menu.length];
        Label l[] = new Label[menu.length];
 
        // 버튼 설정 부분
        for (int i = 0; i < menu.length; i++) {
 
            // 버튼
            bt[i] = new Button(menu[i]);
            if (i < 4) {
                bt[i].setBounds(25 + i * 150, 50, 100, 60);
            } else {
                bt[i].setBounds(25 + (i - 4) * 150, 200, 100, 60);
            }
            bt[i].setLabel(menu[i]);
 
            // 숫자 버튼
            num[i] = new TextField("0");
            num[i].setBackground(Color.white);
            num[i].setEditable(false);
            num[i].setBounds(bt[i].getX() + 25, bt[i].getY() + 80, 40, 20);
 
            // "-" 버튼
            minus[i] = new Button("-");
            minus[i].setBounds(bt[i].getX(), num[i].getY(), 20, 20);
            minus[i].setEnabled(false);
 
            // "+" 버튼
            plus[i] = new Button("+");
            plus[i].setBounds(bt[i].getX() + (100 - 20), num[i].getY(), 20, 20);
            plus[i].setEnabled(false);
 
            // 가격
            l[i] = new Label(price[i] + "원");
            l[i].setBounds(bt[i].getX() + 25, num[i].getY() - 20, 100, 20);
 
            // 확인 버튼
            ok[i] = new Button("확인");
            ok[i].setBounds(bt[i].getX(), num[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);
 
            pNorth.add(bt[i]);
            pNorth.add(num[i]);
            pNorth.add(minus[i]);
            pNorth.add(plus[i]);
            pNorth.add(l[i]);
            pNorth.add(ok[i]);
        }
 
        // 중앙
        ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("   상품명        단가        수량        합계\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
 
        // 남쪽
        Panel pSouth = new Panel();
        pSouth.setBackground(new Color(255, 255, 215));
 
        Button bt2 = new Button("초기화");
        Button bt3 = new Button("돌아가기");
        pSouth.add(bt2);
        pSouth.add(bt3);
        
 
        // 초기화 버튼
        bt2.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < menu.length; i++) {
                    bt[i].setEnabled(true);
                    minus[i].setEnabled(false);
                    plus[i].setEnabled(false);
                    num[i].setText("0");
                    ta.setText("   상품명               단가             수량             합계\n\n");
 
                }
            }
        });
 
 
        //bt3 메뉴로 돌아가기
        
        bt3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView();
                dispose();
            }
        });
      
        
        add(pNorth, BorderLayout.NORTH);
        add(ta, BorderLayout.CENTER);
        add(pSouth, BorderLayout.SOUTH);
        setVisible(true);
 
      
        for (int i = 0; i < menu.length; i++) {
            j = i;
            int o = i;
            int counti[] = new int[menu.length];
 
            // 상품 버튼 이벤트
            bt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    minus[o].setEnabled(true);
                    plus[o].setEnabled(true);
                    bt[o].setEnabled(false);
                    ok[o].setEnabled(true);
                    
                    count = 0;
                    counti[o] = 0;
                }
            });
            
            
 
            // "-" 버튼 이벤트
            minus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	counti[o] = counti[o] - 1;
                    num[o].setText(counti[o] + "");
                    ok[o].setEnabled(true);
                    if(counti[o] > 1) {      
                    	counti[o]--;
                     } else if ( num[o].getText().equals("1")) {
                        JOptionPane.showMessageDialog(null, "최초 구매수량은 1개입니다");
                     }
                }
            });
            
            // "+" 버튼 이벤트
            plus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	counti[o] = counti[o] + 1;
                    num[o].setText(counti[o] + "");
                    ok[o].setEnabled(true);
                    if (count > 0) {
                        minus[o].setEnabled(true);
                    }
                }
            });
            
            //확인 버튼 이벤트
            ok[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	count = counti[o];
                	new itemPay(price[o] * counti[o], counti[o], loginUI.sendID, pno[o]);
                	j = o;
                    show = bt[o].getActionCommand();
                    ok[o].setEnabled(false);
                   }
            });
        }
    }

   @Override
   public void windowActivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowClosing(WindowEvent e) {
      System.exit(0);
      
   }

   @Override
   public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub
      
   }
    
}