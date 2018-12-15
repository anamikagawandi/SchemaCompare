package src;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class SchemaData extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null,stmt1=null;
	DatabaseMetaData dmd;	
	File f;
	
	JButton jb1,jb2;
	JTextField jt1,jt3,jt4,temp;
	JPasswordField jt2;
	JPanel jp;
	Container c;
	JFileChooser chooser;
	JTabbedPane jbp;
	
	SchemaData()
	{
		c=this.getContentPane();
		c.setLayout(new FlowLayout());
		chooser = new JFileChooser();
		chooser.setDialogTitle("Select File Location");

	    f=null;
	    
		jbp= new JTabbedPane();
		
		jt1 = new JTextField(50);
		jt2 = new JPasswordField(50);
		jt3 = new JTextField(50);
		jt4= new JTextField(50);
		temp=new JTextField(50);
		jb1=new JButton("Save Schema");
		jb2=new JButton("Browse Location");
		jp=new JPanel();
		
		jp.add(new JLabel("Enter Username"),JLabel.LEFT_ALIGNMENT);
		jp.add(jt1,JTextField.RIGHT_ALIGNMENT);
		jp.add(new JLabel("Enter Password"),JLabel.LEFT_ALIGNMENT);
		jp.add(jt2,JPasswordField.RIGHT_ALIGNMENT);
		jp.add(new JLabel("Enter Database Name"),JLabel.LEFT_ALIGNMENT);
		jp.add(jt3,JTextField.RIGHT_ALIGNMENT);
		jp.add(new JLabel("Enter Server Name"),JLabel.LEFT_ALIGNMENT);
		jp.add(jt4,JTextField.RIGHT_ALIGNMENT);
		jp.add(new JLabel("Select File Location with Name"),JLabel.LEFT_ALIGNMENT);
		jp.add(temp,JTextField.LEFT_ALIGNMENT);
		jp.add(jb2,JFileChooser.RIGHT_ALIGNMENT);
		jp.add(jb1);
		jbp.addTab("Create Schema",null,jp,"Used to create and store schema of DB");
		jbp.addTab("Compare Schema",null,new SchemaCompare(),"Used to compare and generate report");
		c.add(jbp,JTabbedPane.CENTER_ALIGNMENT);
		
		jb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				connection();	
				try {
					if (f==null)
						throw new Exception();
					else{
						setJsonFile(f,setTable(),setCol(),setKey(),setConstraint(),setIdx());
						JOptionPane.showMessageDialog (null, "JSON "+f.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog (null, "Select a File Location and Name first", "Exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});	
		
		jb2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				
				if (chooser.showSaveDialog(jb2) == JFileChooser.APPROVE_OPTION) {
				    f = new File(chooser.getSelectedFile().toString()+".json");
				    temp.setText(f.toString());
				    System.out.println(chooser.getSelectedFile().toString()+".json");
				    //JOptionPane.showMessageDialog (null, "File created at location"+f.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});	
		
	}
	
	@SuppressWarnings("deprecation")
	public void connection()
	{
	      try {
	    	 if(jt4.getText()==null || jt1.getText()==null || jt2.getText()==null || jt3.getText()==null)
					throw new Exception();
	    	String DB_URL = "jdbc:sqlserver://"+jt4.getText()+";user="+jt1.getText()+";password="+jt2.getText()+";database="+jt3.getText();
	  		//String DB_URL = "jdbc:sqlserver://192.168.2.69;user=ENJ_GALLO_NEW;password=ENJ_GALLO_NEW;database=ENJ_GALLO_NEW";
	    	String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	    	Class.forName(JDBC_DRIVER);
	    	System.out.println("Connecting...");
	    	conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			System.out.println("Connected");
		
			} catch (Exception e) {
				JOptionPane.showMessageDialog (null, "There is a connection error please check the details again", "Exception", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}			
	}
	
	public void setJsonFile(File f,List<TableDetail> Tablist,List<ColDetail> Collist,List<KeyDetail> Keylist,List<ConstraintDetail> Conlist,List<IdxDetail> Idxlist)
	{
		ObjectMapper mapper = new ObjectMapper();
		SchemaStruct s= new SchemaStruct();
		s.setTabCount(Tablist.size());
		s.setTableDetail(Tablist);
		s.setColDetail(Collist);
		s.setKeyDetail(Keylist);
		s.setConstraintDetail(Conlist);
		s.setIdxDetails(Idxlist);
		s.setSchemaName(jt3.getText());
		try {
			mapper.writeValue(f, s);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Written to JSON");
	}
	
	public List<TableDetail> setTable()
	{	
		List<TableDetail> Tablist = new ArrayList<TableDetail>();
		System.out.println("inside setTable");
		try {
			dmd=conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs=dmd.getTables(null,"dbo",null,types);
			while(rs.next() && rs.getString("TABLE_NAME") != "CCR_COMBINED_MAIN" )
			{
				
				String sql = "Select count(*) from "+rs.getString("TABLE_NAME") + " OPTION (MAXDOP 1)";
				System.out.println(sql);
				ResultSet rs1=stmt.executeQuery(sql);
				System.out.println("2");
				rs1.next();
				System.out.println("3");
				ResultSet rs2=stmt1.executeQuery("SELECT t.NAME AS TableName, (SUM(a.used_pages) * 8) AS UsedSpaceKB FROM sys.tables t INNER JOIN sys.indexes i ON t.OBJECT_ID=i.object_id INNER JOIN sys.partitions p ON i.object_id = p.OBJECT_ID AND i.index_id = p.index_id INNER JOIN sys.allocation_units a ON p.partition_id = a.container_id LEFT OUTER JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.NAME ='"+rs.getString("TABLE_NAME")+"' AND t.is_ms_shipped = 0 GROUP BY t.Name");
				System.out.println("4");
				rs2.next();
				System.out.println("5");
				Tablist.add(new TableDetail(rs.getString("TABLE_NAME"),rs2.getLong(2),rs1.getInt(1)));
				System.out.println("6");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}		
		System.out.println("Table List set");
		return Tablist;		
	}
	
	public List<ColDetail> setCol()
	{	
		List<ColDetail> Collist = new ArrayList<ColDetail>();
		try {
			dmd=conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs=dmd.getTables(null,"dbo",null,types);
			while(rs.next())
			{
				ResultSet rs1=dmd.getColumns(null,null,rs.getString("TABLE_NAME"),null);
				while(rs1.next())
				{
					Collist.add(new ColDetail(rs1.getString("TABLE_NAME"),rs1.getString("COLUMN_NAME"),rs1.getString("TYPE_NAME"),rs1.getString("COLUMN_SIZE")));
				}		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Column List set");
		return Collist;		
	}
	
	public List<KeyDetail> setKey()
	{	
		List<KeyDetail> Keylist = new ArrayList<KeyDetail>();
		try {
			dmd=conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs=dmd.getTables(null,"dbo",null,types);
			while(rs.next())
			{
				ResultSet rs1=dmd.getPrimaryKeys(null, null,rs.getString("TABLE_NAME"));
				while(rs1.next())
				{
					Keylist.add(new KeyDetail(rs1.getString("PK_NAME"),rs1.getString("COLUMN_NAME"),rs1.getString("TABLE_NAME")));
				}
				ResultSet rs2=dmd.getImportedKeys(null, null,rs.getString("TABLE_NAME"));
				while(rs2.next())
				{
					Keylist.add(new KeyDetail(rs2.getString("FK_NAME"),rs2.getString("FKCOLUMN_NAME"),rs2.getString("FKTABLE_NAME")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Key List set");
		return Keylist;		
	}
	
	public List<ConstraintDetail> setConstraint()
	{	
		List<ConstraintDetail> Conlist = new ArrayList<ConstraintDetail>();
		
		Statement stmtcount;
		try {
			stmtcount = conn.createStatement();
			ResultSet rs= stmtcount.executeQuery("SELECT SUBSTRING(obj1.name,1,(len(obj1.name)-(CHARINDEX ('_' ,REVERSE(obj1.name)))+1)) AS NameofConstraint,obj1.type_desc AS ConstraintType,obj2.name AS TableName FROM sys.objects obj1 inner join sys.tables obj2 on obj1.parent_object_id=obj2.object_id WHERE obj1.type_desc LIKE '%CONSTRAINT' and obj1.type_desc not in ('PRIMARY_KEY_CONSTRAINT','FOREIGN_KEY_CONSTRAINT')");
			
			while(rs.next())
			{
				Conlist.add(new ConstraintDetail(rs.getString(1),rs.getString(2),rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Constraint List set");
		return Conlist;		
	}
	
	
	public List<IdxDetail> setIdx()
	{	
		List<IdxDetail> Idxlist = new ArrayList<IdxDetail>();
		try {
			dmd=conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs=dmd.getTables(null,"dbo",null,types);
			while(rs.next())
			{
				ResultSet rs1=dmd.getIndexInfo(null,null,rs.getString("TABLE_NAME"),false,false);
				while(rs1.next())
				{
					if(rs1.getString("COLUMN_NAME")!=null)
						Idxlist.add(new IdxDetail(rs1.getString("INDEX_NAME"),rs1.getString("COLUMN_NAME"),rs1.getString("TABLE_NAME")));
				}		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Index List set");
		return Idxlist;		
	}
	
	public static void main (String arg[])
	{
		//File f = new File("\\..\\Documents\\tab123.json");
		/*File f2 = new File("C:\\Users\\anamika.gawandi\\Documents\\tabnew.json");
		SchemaData sd=new SchemaData();
		sd.connection();
		sd.setJsonFile(f2,sd.setTable(),sd.setCol(),sd.setKey(),sd.setConstraint(),sd.setIdx());	
	*/
		SchemaData f=new SchemaData();
		
		f.setLayout(new GridLayout());
		f.setVisible(true);
		//f.setSize(500,500);	
		f.setSize(600,500);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
}
