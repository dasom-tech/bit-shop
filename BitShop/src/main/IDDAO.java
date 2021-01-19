package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;


public class IDDAO {
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USER = "test"; 
	final String PASSWORD = "test"; 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public IDDAO() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public IDVO selectId(String id) {
		IDVO son = null;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT ID, PW, NAME, EMAIL, PHONE, ADDRESS, MONEY, POINT"
					+ "  FROM MEM "
					+ " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//3-3. SQL문장 실행요청
			rs = pstmt.executeQuery();
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() ) {
				son = new IDVO();
				son.setID(rs.getString("ID"));
				son.setPW(rs.getString("PW"));
				son.setNA(rs.getString("NAME"));
				son.setEM(rs.getString("EMAIL"));
				son.setPHONE(rs.getString("PHONE"));
				son.setADDR(rs.getString("ADDRESS"));
				son.setMONEY(rs.getInt("MONEY"));
				son.setPOINT(rs.getInt("POINT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return son;
	}
	
	String id = null;
	String pw = null;
	
	public Boolean LoginCheck(String id, String pw) {
		this.id = id;
		this.pw = pw;
		Boolean result = false;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT * "
					+ "  FROM MEM "
					+ " WHERE ID = '" + id + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String checker;
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() == false || (id.isEmpty()) == true) {
				result = false;
			}else {
				sql = "select * from (select * from MEM where id='" + id + "')";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next() == true) {
                    if (rs.getString(2).equals(pw)) 
                    {
                    	result = true;
                    } else {
                        result = false;
                    }
                }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	String name = null;
	String phone = null;
	public Boolean RejoinCheck(String name, String phone) {
		this.name = name;
		this.phone = phone;
		Boolean result = false;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT NAME, PHONE "
					+ "  FROM SIGNOUTMEMBER "
					+ " WHERE NAME = '" + name + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next() == false || (name.isEmpty()) == true) {
				result = false;
			}else {
				if(rs.getString("PHONE").equals(phone))
				{
					result = true;
				}
				else
				{
					result = false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	String email = null;
	private String addr;
	public String findID(String email) {
		this.email = email;
		
		String result = null;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT EMAIL, ID "
					+ "  FROM MEM "
					+ " WHERE EMAIL = '" + email + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String checker;
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() == false || (email.isEmpty()) == true) {
				result = null;
			}else {
				result = rs.getString("ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public String findPS(String email, String id) {
		this.email = email;
		this.id = id;
		
		String result = null;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT EMAIL, ID , PW "
					+ "  FROM MEM "
					+ " WHERE ID = '" + id + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String checker;
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() == false || (id.isEmpty()) == true) {
				result = null;
			}else {
				if(rs.getString("EMAIL").equals(email))
				{
					result = rs.getString("PW");
				}
				else
				{
					result = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return result;
	}

	public int insertData(IDVO son) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "INSERT INTO MEM "
					+ "       (ID, PW, NAME, EMAIL, PHONE, ADDRESS, POINT) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, son.getID());
			pstmt.setString(2, son.getPW());
			pstmt.setString(3, son.getNA());
			pstmt.setString(4, son.getEM());
			pstmt.setString(5, son.getPHONE());
			pstmt.setString(6, son.getADDR());
			pstmt.setInt(7, son.getPOINT());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	public int SignoutInsert (SignoutVO son) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "INSERT INTO SIGNOUTMEMBER "
					+ "       (NAME, PHONE) "
					+ "VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, son.getNAME());
			pstmt.setString(2, son.getPHONE());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	public boolean check(String id) {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = ""
					+ "SELECT ID "
					+ "    FROM MEM "
					+ " WHERE ID = '" + id + "'";
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next() == false)
			{
				return false;
			}
			else
			{
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	// id,pw empty check
	public boolean isEmpty(String id, String pw) {
	    if (null == pw || pw.isEmpty() || null == id || id.isEmpty()  ) {
	        return true;
	    }
	    return false;
	}
	
	public boolean isEmpty(String pw) {
	    if (null == pw || pw.isEmpty()) {
	        return true;
	    }
	    return false;
	}
	
	public int checkUser(String id, String pw) {
		SignoutVO test = null; 
		int count = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			String sql = ""
					+ "SELECT ID, PW, NAME, PHONE "
					+ "  FROM MEM "
					+ " WHERE ID = ? AND PW = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 if (rs.getString("ID").equals(id) && rs.getString("PW").equals(pw)) 
	             {
					 test = new SignoutVO(rs.getString("NAME"), rs.getString("PHONE"));
					 SignoutInsert(test);
					 count = deleteData(id, pw);
	             } else {
	            	 return count;
	             }
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}
	
	public int checkUser(String pw) {
		
		int count = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			String sql = ""
					+ "SELECT * "
					+ "  FROM MEM "
					+ " WHERE PW = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			
			rs = pstmt.executeQuery();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}
	
	public int deleteData(String id, String pw) {
		int count = 0;
		try {
			// 2. DB 연동하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			//3. Statement 객체 생성해서 sql 실행요청
			String sql = "DELETE FROM MEM WHERE ID = ? AND PW = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);	
			pstmt.setString(2, pw);	
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return count;
	}
	
	   public ProductVO selectItem(int pno) {
		      ProductVO itemvo = null;      
		      // DB 연동작업 (SELECT)
		      try {
		         // 2. DB 연동하고 connection 객체 생성
		         conn = DriverManager.getConnection(URL, USER, PASSWORD);

		         //3. sql 실행을 위한 준비
		         String sql = ""
		               + "SELECT PNO, PNAME, PRICE, PINFO, PSTOCK "
		               + "FROM PRODUCT "
		               + " WHERE PNO = ? ";
		         // 3-1 SQL을 전달하고 실행준비 요청
		         pstmt = conn.prepareStatement(sql); 
		         // 3-2. SQL문장의 ? 위치에 값 설정
		         pstmt.setInt(1, pno);          
		         // 3-3 SQL문장 실행요청         
		         rs = pstmt.executeQuery();
		         
		         // 4sql 결과에 대한 처리
		         if(rs.next()) {
		            itemvo = new ProductVO();
		            itemvo.setPNO(rs.getInt("PNO"));
		            itemvo.setPNAME(rs.getString("PNAME"));
		            itemvo.setPRICE(rs.getInt("PRICE"));
		            itemvo.setPINFO(rs.getString("PINFO"));
		            itemvo.setPSTOCK(rs.getInt("PSTOCK"));
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(conn, pstmt, rs);
		      }
		      return itemvo;
		   }
	
	public int[] selectPno() {
		int[] item = null;		// 기본값이 null이라서 조회할 데이터가 없으면 null이 리턴이 됨
		int size = 0;
		// DB 연동작업 (SELECT)
		try {
			// 2. DB 연동하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//3. sql 실행을 위한 준비
			String sql = ""
					+ "SELECT count(*) "
					+ " FROM PRODUCT ";
			// 3-1 SQL을 전달하고 실행준비 요청
			pstmt = conn.prepareStatement(sql); // ->sql이 DB로 전달		
			// 3-3 SQL문장 실행요청			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				size = rs.getInt("count(*)");
				item = new int[size];
			}
			
			sql = ""
				+ "SELECT PNO "
				+ " FROM PRODUCT ";
			// 3-1 SQL을 전달하고 실행준비 요청
			pstmt = conn.prepareStatement(sql); // ->sql이 DB로 전달		
			// 3-3 SQL문장 실행요청			
			rs = pstmt.executeQuery();
			
			// 4sql 결과에 대한 처리
			int index = 0;
			while(rs.next()) {
				item[index] = rs.getInt("PNO");
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return item;
	}	
	
	private void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection conn, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateMember(IDVO son)throws Exception{
		boolean ok = false;
		int cnt =0;
		
		try{
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = "update MEM set PW=?, NAME=?, EMAIL=?, PHONE=?, ADDRESS=? "
					+ "where id=? ";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, son.getPW());
			pstmt.setString(++cnt, son.getNA());
			pstmt.setString(++cnt, son.getEM());
			pstmt.setString(++cnt, son.getPHONE());
			pstmt.setString(++cnt, son.getADDR());
			pstmt.setString(++cnt, son.getID());
		            
			pstmt.executeUpdate();
		             
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}
		return ok;
	}
	
	public boolean Charge(String id, int chargemem)throws Exception{
		boolean ok = false;
		int cnt =0;
		
		try{
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = "update MEM set MONEY=? "
					+ "where id=? ";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(++cnt, chargemem);
			pstmt.setString(++cnt, id);
		            
			pstmt.executeUpdate();
		             
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}
		return ok;
	}
	
	public Integer loadMoney(String id) {
		IDVO son = null;
		int money = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT MONEY "
					+ "  FROM MEM "
					+ " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//3-3. SQL문장 실행요청
			rs = pstmt.executeQuery();
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() ) {
				money = rs.getInt("MONEY");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return money;
	}
	
	public void insertReviewList(String id, int pno, int count){
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "INSERT INTO REVIEWLIST "
					+ "       (ID, PNO, AMOUNT) "
					+ "VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pno);
			pstmt.setInt(3, count);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void updateBalance(String id, int money, int point) {
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "UPDATE MEM "
					+ "   SET MONEY = ?, POINT = ?"
					+ " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setInt(2, point);
			pstmt.setString(3, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	public Integer reviewCount(String id) {
		IDVO son = null;
		int count = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT COUNT(*) "
					+ "  FROM REVIEWLIST "
					+ " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//3-3. SQL문장 실행요청
			rs = pstmt.executeQuery();
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() ) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}
	
	public Integer selectProduct(String id, int row) {
		IDVO son = null;
		int count = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT PNO "
					+ "  FROM (SELECT ROWNUM AS ROWNUMBER, b.* FROM REVIEWLIST b WHERE b.ID = ? )a "
					+ " WHERE a.ROWNUMBER = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, row);
			
			//3-3. SQL문장 실행요청
			rs = pstmt.executeQuery();
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() ) {
				count = rs.getInt("PNO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}
	
	public Integer loadamount(String id, int row) {
		IDVO son = null;
		int count = 0;
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT AMOUNT "
					+ "  FROM (SELECT ROWNUM AS ROWNUMBER, b.* FROM REVIEWLIST b WHERE b.ID = ? )a "
					+ " WHERE a.ROWNUMBER = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, row);
			
			//3-3. SQL문장 실행요청
			rs = pstmt.executeQuery();
			
			//4. SQL 실행결과에 대한 처리
			if (rs.next() ) {
				count = rs.getInt("AMOUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}
	   public ProductVO selectprod(String pNO) {
		      
		      ProductVO pro = null;
		      try {
		         conn = DriverManager.getConnection(URL,   USER, PASSWORD);
		         
		         String sql = ""
		               + "SELECT PNO, String PNAME, Integer PRICE, String PINFO, Integer PSTOCK, Integer REVIEWLIST "
		               + "  FROM PRODUCT "
		               + " WHERE PNO = ? ";
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, pNO);
		         
		         //3-3. SQL문장 실행요청
		         rs = pstmt.executeQuery();
		         
		         //4. SQL 실행결과에 대한 처리
		         if (rs.next() ) {
		            pro = new ProductVO();
		            pro.setPNO(rs.getInt("PNO"));
		            pro.setPNAME(rs.getString("PNAME"));
		            pro.setPRICE(rs.getInt("PRICE"));
		            pro.setPINFO(rs.getString("PINFO"));
		            pro.setPSTOCK(rs.getInt("PSTOCK"));
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close(conn, pstmt, rs);
		      }
		      
		      return pro;
		   }
	   
	   public void updatePstock(int pno, int pstock) {
		      try{
		         conn = DriverManager.getConnection(URL,   USER, PASSWORD);
		         
		         String sql = "update PRODUCT set PSTOCK = ? "
		               + "where PNO = ? ";
		         pstmt =conn.prepareStatement(sql);
		         
		         pstmt.setInt(1, pstock);
		         pstmt.setInt(2, pno);
		                  
		         pstmt.executeUpdate();
		                   
		      }catch(Exception e){
		         e.printStackTrace();
		      }finally{
		         close(conn, pstmt, rs);
		      }
		   }
	   
	      public String lastID() {
	          String result = null;
	          
	          try {
	             conn = DriverManager.getConnection(URL,   USER, PASSWORD);
	             
	             String sql = ""
	                   + "SELECT ID "
	                   + "  FROM REMEMBER ";
	             pstmt = conn.prepareStatement(sql);
	             rs = pstmt.executeQuery();
	             
	             String checker;
	             
	             if (rs.next() ) {
	                   result = rs.getString("ID");
	                }
	             
	          } catch (SQLException e) {
	             e.printStackTrace();
	          } finally {
	             close(conn, pstmt, rs);
	          }
	          
	          return result;
	       }
	       
	       public void UpdatelastID(String id) {
	          
	          try {
	             conn = DriverManager.getConnection(URL,   USER, PASSWORD);
	             
	             String sql = ""
	                   + "UPDATE REMEMBER "
	                   + "  SET ID = ? "
	                   + "WHERE ROWNUM = 1";
	                pstmt =conn.prepareStatement(sql);
	                
	                pstmt.setString(1, id);
	                         
	                pstmt.executeUpdate();
	          } catch (SQLException e) {
	             e.printStackTrace();
	          } finally {
	             close(conn, pstmt, rs);
	          }
	       }
}
