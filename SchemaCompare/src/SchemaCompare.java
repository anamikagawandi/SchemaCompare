package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class SchemaCompare extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SchemaStruct oldA3, newA3;
	File f1,f2,f3;
	ObjectMapper mapper, mapper1, mapper2;
	JButton jb1,jb2,jb3,jb4;
	JTextField jt1,jt2,jt3;
	JFileChooser chooser,chooser2;

	public SchemaCompare() {
		f1=null;
		f2=null;
		f3=null;
		jt1 = new JTextField(50);
		jt2 = new JTextField(50);
		jt3 = new JTextField(50);
		chooser = new JFileChooser();
		chooser.setDialogTitle("Select File Location");
		chooser2 = new JFileChooser();
		chooser2.setDialogTitle("Select File Location");
		
		jb1=new JButton("Browse for old schema");
		jb2=new JButton("Browse for new schema");
		jb3=new JButton("Browse for result file location");
		
		// File(fname);
		mapper1 = new ObjectMapper();
		mapper2 = new ObjectMapper();
		
		this.setLayout(new FlowLayout());
		this.add(jt1,JTextField.RIGHT_ALIGNMENT);
		this.add(jb1,JButton.LEFT_ALIGNMENT);
		this.add(jt2,JTextField.RIGHT_ALIGNMENT);
		this.add(jb2,JButton.LEFT_ALIGNMENT);
		this.add(jt3,JTextField.RIGHT_ALIGNMENT);
		this.add(jb3,JButton.LEFT_ALIGNMENT);
		
		jb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				if (chooser.showOpenDialog(jb1) == JFileChooser.APPROVE_OPTION) {
					f1 = new File(chooser.getSelectedFile().toString());	
					jt1.setText(f1.toString());
					try {
						oldA3 = mapper1.readValue(f1, SchemaStruct.class);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		});	
		
		jb2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				if (chooser.showOpenDialog(jb2) == JFileChooser.APPROVE_OPTION) {
					f2 = new File(chooser.getSelectedFile().toString());	
					jt2.setText(f2.toString());
					try {
						newA3 = mapper2.readValue(f2, SchemaStruct.class);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});	
		
		
		jb3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if (oldA3==null || newA3==null)
					{
						System.out.println("Error");
						throw new Exception();
					}else
					{
						if (chooser2.showSaveDialog(jb3) == JFileChooser.APPROVE_OPTION) {
						    f3 = new File(chooser2.getSelectedFile().toString()+".json");
						    jt3.setText(f3.toString());
						    setJsonFile(f3,diffTabCount(),diffTable(),diffTableNameCase(),diffRowCount(),diffCol(),diffDatatype(),diffKey(),diffConstraint(),
							diffIndex());
						    System.out.println(chooser2.getSelectedFile().toString()+".json");
						    JOptionPane.showMessageDialog (null, "Report created at location"+f3.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);		
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog (null, "Select schema files for old and new runs", "Exception", JOptionPane.INFORMATION_MESSAGE);
				}	
			}	
		});	

	}

	// Differences in table count of 2 Schemas
	public TableCount diffTabCount() {
		TableCount tc = new TableCount(oldA3.getTableDetail().size(), newA3
				.getTableDetail().size());
		System.out.println("Table count set");
		return tc;
	}

	// Differences between tables of 2 Schemas
	public TableName diffTable() {
		TableName tn = new TableName();
		List<TablesNotInOld> t1 = new ArrayList<TablesNotInOld>();
		List<TablesNotInNew> t2 = new ArrayList<TablesNotInNew>();

		// Adding tables which are missing in NEW schema
		for (int i = 0; i < oldA3.getTableDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < newA3.getTableDetail().size(); j++) {
				if (newA3
						.getTableDetail()
						.get(j)
						.getTabName()
						.toLowerCase()
						.equals(((oldA3.getTableDetail().get(i).getTabName()
								.toLowerCase())))) {
					flag = true;
					break;
				}
			}
			if (!flag)
				t2.add(new TablesNotInNew(oldA3.getTableDetail().get(i)
						.getTabName()));
		}

		System.out.println("Missing tables in New are set");
		// Adding tables which are missing in OLD schema
		for (int i = 0; i < newA3.getTableDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < oldA3.getTableDetail().size(); j++) {
				if (oldA3
						.getTableDetail()
						.get(j)
						.getTabName()
						.toLowerCase()
						.equals(((newA3.getTableDetail().get(i).getTabName()
								.toLowerCase())))) {
					flag = true;
					break;
				}
			}
			if (!flag)
				t1.add(new TablesNotInOld(newA3.getTableDetail().get(i)
						.getTabName()));
		}

		System.out.println("Missing tables in Old are set");

		tn.setTablesNotInOld(t1);
		tn.setTablesNotInNew(t2);
		return tn;
	}

	// Differences in casing of tables names of 2 Schemas
	public List<TableWithDiffCase> diffTableNameCase() {
		List<TableWithDiffCase> tnc = new ArrayList<TableWithDiffCase>();

		TableName tn = diffTable();
		// Adding tables for which casing is different
		for (int i = 0; i < tn.getTablesNotInOld().size(); i++) {
			for (int j = 0; j < tn.getTablesNotInNew().size(); j++) {
				if (tn.getTablesNotInOld()
						.get(i)
						.getTableName()
						.replaceAll("[^a-zA-Z]+", "")
						.toLowerCase()
						.equals(tn.getTablesNotInNew().get(j).getTableName()
								.replaceAll("[^a-zA-Z]+", "").toLowerCase())) {
					tnc.add(new TableWithDiffCase(tn.getTablesNotInNew().get(i)
							.getTableName(), tn.getTablesNotInOld().get(j)
							.getTableName()));
				}
			}
		}

		System.out.println("Tables with different casing set");
		return tnc;
	}

	// Differences in row count of tables of 2 Schemas
	public List<RowCount> diffRowCount() {
		List<RowCount> tnc = new ArrayList<RowCount>();

		// Adding tables and row count for which row count is different
		for (int i = 0; i < oldA3.getTableDetail().size(); i++) {
			for (int j = 0; j < newA3.getTableDetail().size(); j++) {
				if ((newA3.getTableDetail().get(j).getTabName().equals(((oldA3
						.getTableDetail().get(i).getTabName()))))
						&& (newA3.getTableDetail().get(j).getTabRow() != (((oldA3
								.getTableDetail().get(i).getTabRow()))))) {
					tnc.add(new RowCount(oldA3.getTableDetail().get(i)
							.getTabName(), oldA3.getTableDetail().get(i)
							.getTabRow(), newA3.getTableDetail().get(j)
							.getTabRow()));
					break;
				}
			}
		}

		System.out.println("Row count for tables set");
		return tnc;
	}

	// Difference in columns for 2 schemas
	public ColName diffCol() {
		ColName cn = new ColName();
		List<ColNotInOld> t1 = new ArrayList<ColNotInOld>();
		List<ColNotInNew> t2 = new ArrayList<ColNotInNew>();

		// Adding tables which are missing in NEW schema
		for (int i = 0; i < oldA3.getColDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < newA3.getColDetail().size(); j++) {
				if ((newA3.getColDetail().get(j).getColName().equals(((oldA3
						.getColDetail().get(i).getColName()))))
						&& (newA3.getColDetail().get(j).getTabName()
								.equals(oldA3.getColDetail().get(i)
										.getTabName()))) {
					flag = true;
					break;
				}
			}
			if (!flag)
				t2.add(new ColNotInNew(
						oldA3.getColDetail().get(i).getColName(), oldA3
								.getColDetail().get(i).getTabName()));
		}

		System.out.println("Missing columns in New are set");
		// Adding tables which are missing in OLD schema
		for (int i = 0; i < newA3.getColDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < oldA3.getColDetail().size(); j++) {
				if ((oldA3.getColDetail().get(j).getColName()
						.equals(newA3.getColDetail().get(i).getColName()))
						&& (oldA3.getColDetail().get(j).getTabName()
								.equals(newA3.getColDetail().get(i)
										.getTabName()))) {
					flag = true;
					break;
				}
			}
			if (!flag)
				t1.add(new ColNotInOld(
						newA3.getColDetail().get(i).getColName(), newA3
								.getColDetail().get(i).getTabName()));
		}
		System.out.println("Missing columns in Old are set");

		cn.setColNotInOld(t1);
		cn.setColNotInNew(t2);
		return cn;
	}

	// Difference in data types of columns for 2 schemas
	public List<Datatype> diffDatatype() {
		List<Datatype> dt = new ArrayList<Datatype>();

		// Adding tables_col_data type for which data type is not matching
		for (int i = 0; i < oldA3.getColDetail().size(); i++) {
			for (int j = 0; j < newA3.getColDetail().size(); j++) {
				if ((newA3.getColDetail().get(j).getColName().equals(((oldA3
						.getColDetail().get(i).getColName()))))
						&& (newA3.getColDetail().get(j).getTabName()
								.equals(((oldA3.getColDetail().get(i)
										.getTabName()))))
						&& (!(oldA3.getColDetail().get(i).getDatatype() + oldA3
								.getColDetail().get(i).getColSize())
								.equals(newA3.getColDetail().get(j)
										.getDatatype()
										+ newA3.getColDetail().get(j)
												.getColSize()))) {
					dt.add(new Datatype(oldA3.getColDetail().get(i)
							.getTabName(), oldA3.getColDetail().get(i)
							.getColName(), (oldA3.getColDetail().get(i)
							.getDatatype() + oldA3.getColDetail().get(i)
							.getColSize()), (newA3.getColDetail().get(j)
							.getDatatype() + newA3.getColDetail().get(j)
							.getColSize())));
					break;
				}
			}
		}

		System.out.println("Columns with different datatypes are set");
		return dt;
	}

	// Difference in keys of 2 schemas
	public Keys diffKey() {
		Keys key = new Keys();

		List<KeyNotInOld> t1 = new ArrayList<KeyNotInOld>();
		List<KeyNotInNew> t2 = new ArrayList<KeyNotInNew>();

		// Adding index which are missing in NEW schema
		for (int i = 0; i < oldA3.getKeyDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < newA3.getKeyDetail().size(); j++) {
				if ((newA3.getKeyDetail().get(j).getKeyCol().equals(((oldA3
						.getKeyDetail().get(i).getKeyCol()))))
						&& (newA3.getKeyDetail().get(j).getKeyTab()
								.equals(((oldA3.getKeyDetail().get(i)
										.getKeyTab()))))
						&& (newA3
								.getKeyDetail()
								.get(j)
								.getKeyName()
								.substring(
										0,
										newA3.getKeyDetail().get(j)
												.getKeyName().lastIndexOf("_") + 1)
								.equals(oldA3
										.getKeyDetail()
										.get(i)
										.getKeyName()
										.substring(
												0,
												oldA3.getKeyDetail().get(i)
														.getKeyName()
														.lastIndexOf("_") + 1)))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t2.add(new KeyNotInNew(oldA3
						.getKeyDetail()
						.get(i)
						.getKeyName()
						.substring(
								0,
								oldA3.getKeyDetail().get(i).getKeyName()
										.lastIndexOf("_") + 1), oldA3
						.getKeyDetail().get(i).getKeyCol(), oldA3
						.getKeyDetail().get(i).getKeyTab()));
		}

		System.out.println("Missing keys in New are set");

		// Adding index which are missing in OLD schema
		for (int i = 0; i < newA3.getKeyDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < oldA3.getKeyDetail().size(); j++) {
				if ((oldA3.getKeyDetail().get(j).getKeyCol().equals(((newA3
						.getKeyDetail().get(i).getKeyCol()))))
						&& (oldA3.getKeyDetail().get(j).getKeyTab()
								.equals(((newA3.getKeyDetail().get(i)
										.getKeyTab()))))
						&& (oldA3
								.getKeyDetail()
								.get(j)
								.getKeyName()
								.substring(
										0,
										oldA3.getKeyDetail().get(j)
												.getKeyName().lastIndexOf("_") + 1)
								.equals(newA3
										.getKeyDetail()
										.get(i)
										.getKeyName()
										.substring(
												0,
												newA3.getKeyDetail().get(i)
														.getKeyName()
														.lastIndexOf("_") + 1)))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t1.add(new KeyNotInOld(newA3
						.getKeyDetail()
						.get(i)
						.getKeyName()
						.substring(
								0,
								newA3.getKeyDetail().get(i).getKeyName()
										.lastIndexOf("_") + 1), newA3
						.getKeyDetail().get(i).getKeyCol(), newA3
						.getKeyDetail().get(i).getKeyTab()));
		}
		System.out.println("Missing keys in Old are set");

		key.setKeyNotInOld(t1);
		key.setKeyNotInNew(t2);
		return key;
	}

	// Difference in Constraint of 2 schemas
	public Constraints diffConstraint() {
		Constraints con = new Constraints();

		List<ConstraintNotInOld> t1 = new ArrayList<ConstraintNotInOld>();
		List<ConstraintNotInNew> t2 = new ArrayList<ConstraintNotInNew>();

		// Adding index which are missing in NEW schema
		for (int i = 0; i < oldA3.getConstraintDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < newA3.getConstraintDetail().size(); j++) {
				if ((newA3.getConstraintDetail().get(j).getConstraintType()
						.equals(((oldA3.getConstraintDetail().get(i)
								.getConstraintType()))))
						&& (newA3.getConstraintDetail().get(j)
								.getConstraintTab().equals(((oldA3
								.getConstraintDetail().get(i)
								.getConstraintTab()))))
						&& (newA3.getConstraintDetail().get(j)
								.getConstraintName().equals(oldA3
								.getConstraintDetail().get(i)
								.getConstraintName()))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t2.add(new ConstraintNotInNew(oldA3.getConstraintDetail()
						.get(i).getConstraintName(), oldA3
						.getConstraintDetail().get(i).getConstraintType(),
						oldA3.getConstraintDetail().get(i).getConstraintTab()));
		}

		System.out.println("Missing constraints in New are set");

		// Adding index which are missing in OLD schema
		for (int i = 0; i < newA3.getConstraintDetail().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < oldA3.getConstraintDetail().size(); j++) {
				if ((oldA3.getConstraintDetail().get(j).getConstraintType()
						.equals(((newA3.getConstraintDetail().get(i)
								.getConstraintType()))))
						&& (oldA3.getConstraintDetail().get(j)
								.getConstraintTab().equals(((newA3
								.getConstraintDetail().get(i)
								.getConstraintTab()))))
						&& (oldA3.getConstraintDetail().get(j)
								.getConstraintName().equals(newA3
								.getConstraintDetail().get(i)
								.getConstraintName()))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t1.add(new ConstraintNotInOld(newA3.getConstraintDetail()
						.get(i).getConstraintName(), newA3
						.getConstraintDetail().get(i).getConstraintType(),
						newA3.getConstraintDetail().get(i).getConstraintTab()));
		}
		System.out.println("Missing constraints in Old are set");

		con.setConstraintNotInOld(t1);
		con.setConstraintNotInNew(t2);
		return con;
	}

	// Difference in index of 2 schemas
	public Index diffIndex() {
		Index idx = new Index();

		List<IndexNotInOld> t1 = new ArrayList<IndexNotInOld>();
		List<IndexNotInNew> t2 = new ArrayList<IndexNotInNew>();

		// Adding index which are missing in NEW schema
		for (int i = 0; i < oldA3.getIdxDetails().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < newA3.getIdxDetails().size(); j++) {
				if ((newA3.getIdxDetails().get(j).getIdxCol().equals(((oldA3
						.getIdxDetails().get(i).getIdxCol()))))
						&& (newA3.getIdxDetails().get(j).getIdxTab()
								.equals(((oldA3.getIdxDetails().get(i)
										.getIdxTab()))))
						&& (newA3
								.getIdxDetails()
								.get(j)
								.getIdxName()
								.substring(
										0,
										newA3.getIdxDetails().get(j)
												.getIdxName().lastIndexOf("_") + 1)
								.equals(oldA3
										.getIdxDetails()
										.get(i)
										.getIdxName()
										.substring(
												0,
												oldA3.getIdxDetails().get(i)
														.getIdxName()
														.lastIndexOf("_") + 1)))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t2.add(new IndexNotInNew(oldA3
						.getIdxDetails()
						.get(i)
						.getIdxName()
						.substring(
								0,
								oldA3.getIdxDetails().get(i).getIdxName()
										.lastIndexOf("_") + 1), oldA3
						.getIdxDetails().get(i).getIdxCol(), oldA3
						.getIdxDetails().get(i).getIdxTab()));
		}

		System.out.println("Missing index in New are set");

		// Adding index which are missing in OLD schema
		for (int i = 0; i < newA3.getIdxDetails().size(); i++) {
			boolean flag = false;
			for (int j = 0; j < oldA3.getIdxDetails().size(); j++) {
				if ((oldA3.getIdxDetails().get(j).getIdxCol().equals(((newA3
						.getIdxDetails().get(i).getIdxCol()))))
						&& (oldA3.getIdxDetails().get(j).getIdxTab()
								.equals(((newA3.getIdxDetails().get(i)
										.getIdxTab()))))
						&& (oldA3
								.getIdxDetails()
								.get(j)
								.getIdxName()
								.substring(
										0,
										oldA3.getIdxDetails().get(j)
												.getIdxName().lastIndexOf("_") + 1)
								.equals(newA3
										.getIdxDetails()
										.get(i)
										.getIdxName()
										.substring(
												0,
												newA3.getIdxDetails().get(i)
														.getIdxName()
														.lastIndexOf("_") + 1)))) {

					flag = true;
					break;
				}
			}
			if (!flag)
				t1.add(new IndexNotInOld(newA3
						.getIdxDetails()
						.get(i)
						.getIdxName()
						.substring(
								0,
								newA3.getIdxDetails().get(i).getIdxName()
										.lastIndexOf("_") + 1), newA3
						.getIdxDetails().get(i).getIdxCol(), newA3
						.getIdxDetails().get(i).getIdxTab()));
		}
		System.out.println("Missing index in Old are set");

		idx.setIndexNotInOld(t1);
		idx.setIndexNotInNew(t2);
		return idx;
	}

	// Writing to JSON file
	public void setJsonFile(File f, TableCount tabCount, TableName tableName,
			List<TableWithDiffCase> tnc, List<RowCount> rc, ColName colName,
			List<Datatype> dt, Keys key, Constraints con, Index idx) {
		mapper = new ObjectMapper();
		Difference d = new Difference();
		d.setTableCount(tabCount);
		d.setTableName(tableName);
		d.setTableWithDiffCase(tnc);
		d.setRowCount(rc);
		d.setColName(colName);
		d.setDatatype(dt);
		d.setKeys(key);
		d.setConstraints(con);
		d.setIndex(idx);
		ReportStruct r = new ReportStruct(d);
		try {
			mapper.writeValue(f, r);
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

}
