package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//-----------------------------------Compare.ReportStruct.java-----------------------------------

public class ReportStruct implements Serializable {

	private Difference difference;
	private final static long serialVersionUID = 3253701446252378082L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ReportStruct() {
	}

	/**
	 * 
	 * @param difference
	 */
	public ReportStruct(Difference difference) {
		super();
		this.difference = difference;
	}

	public Difference getDifference() {
		return difference;
	}

	public void setDifference(Difference difference) {
		this.difference = difference;
	}

}

// -----------------------------------Compare.Difference.java-----------------------------------
class Difference implements Serializable {

	private TableCount tableCount;
	private TableName tableName;
	private List<TableWithDiffCase> tableWithDiffCase = new ArrayList<TableWithDiffCase>();
	private List<RowCount> rowCount = new ArrayList<RowCount>();
	private ColName colName;
	private List<Datatype> datatype = new ArrayList<Datatype>();
	private Keys keys;
	private Constraints constraints;
	private Index index;
	private final static long serialVersionUID = -1703922322974882114L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Difference() {
	}

	/**
	 * 
	 * @param index
	 * @param tableCount
	 * @param keys
	 * @param tableWithDiffCase
	 * @param tableName
	 * @param constraints
	 * @param datatype
	 * @param rowCount
	 * @param colName
	 */
	public Difference(TableCount tableCount, TableName tableName,
			List<TableWithDiffCase> tableWithDiffCase, List<RowCount> rowCount,
			ColName colName, List<Datatype> datatype, Keys keys,
			Constraints constraints, Index index) {
		super();
		this.tableCount = tableCount;
		this.tableName = tableName;
		this.tableWithDiffCase = tableWithDiffCase;
		this.rowCount = rowCount;
		this.colName = colName;
		this.datatype = datatype;
		this.keys = keys;
		this.constraints = constraints;
		this.index = index;
	}

	public TableCount getTableCount() {
		return tableCount;
	}

	public void setTableCount(TableCount tableCount) {
		this.tableCount = tableCount;
	}

	public TableName getTableName() {
		return tableName;
	}

	public void setTableName(TableName tableName) {
		this.tableName = tableName;
	}

	public List<TableWithDiffCase> getTableWithDiffCase() {
		return tableWithDiffCase;
	}

	public void setTableWithDiffCase(List<TableWithDiffCase> tableWithDiffCase) {
		this.tableWithDiffCase = tableWithDiffCase;
	}

	public List<RowCount> getRowCount() {
		return rowCount;
	}

	public void setRowCount(List<RowCount> rowCount) {
		this.rowCount = rowCount;
	}

	public ColName getColName() {
		return colName;
	}

	public void setColName(ColName colName) {
		this.colName = colName;
	}

	public List<Datatype> getDatatype() {
		return datatype;
	}

	public void setDatatype(List<Datatype> datatype) {
		this.datatype = datatype;
	}

	public Keys getKeys() {
		return keys;
	}

	public void setKeys(Keys keys) {
		this.keys = keys;
	}

	public Constraints getConstraints() {
		return constraints;
	}

	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

}

// -----------------------------------Compare.ColName.java-----------------------------------

class ColName implements Serializable {

	private List<ColNotInOld> colNotInOld = new ArrayList<ColNotInOld>();
	private List<ColNotInNew> colNotInNew = new ArrayList<ColNotInNew>();
	private final static long serialVersionUID = 3792523796808416966L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ColName() {
	}

	/**
	 * 
	 * @param colNotInOld
	 * @param colNotInNew
	 */
	public ColName(List<ColNotInOld> colNotInOld, List<ColNotInNew> colNotInNew) {
		super();
		this.colNotInOld = colNotInOld;
		this.colNotInNew = colNotInNew;
	}

	public List<ColNotInOld> getColNotInOld() {
		return colNotInOld;
	}

	public void setColNotInOld(List<ColNotInOld> colNotInOld) {
		this.colNotInOld = colNotInOld;
	}

	public List<ColNotInNew> getColNotInNew() {
		return colNotInNew;
	}

	public void setColNotInNew(List<ColNotInNew> colNotInNew) {
		this.colNotInNew = colNotInNew;
	}

}

// -----------------------------------Compare.ColNotInNew.java-----------------------------------

class ColNotInNew implements Serializable {

	private String colName;
	private String tableName;
	private final static long serialVersionUID = 5675168333160335691L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ColNotInNew() {
	}

	/**
	 * 
	 * @param tableName
	 * @param colName
	 */
	public ColNotInNew(String colName, String tableName) {
		super();
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.ColNotInOld.java-----------------------------------

class ColNotInOld implements Serializable {

	private String colName;
	private String tableName;
	private final static long serialVersionUID = 8695348229473904056L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ColNotInOld() {
	}

	/**
	 * 
	 * @param tableName
	 * @param colName
	 */
	public ColNotInOld(String colName, String tableName) {
		super();
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.ConstraintNotInNew.java-----------------------------------

class ConstraintNotInNew implements Serializable {

	private String constraintName;
	private String constraintType;
	private String tableName;
	private final static long serialVersionUID = 3349058275305128789L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ConstraintNotInNew() {
	}

	/**
	 * 
	 * @param constraintName
	 * @param tableName
	 * @param constraintType
	 */
	public ConstraintNotInNew(String constraintName, String constraintType,
			String tableName) {
		super();
		this.constraintName = constraintName;
		this.constraintType = constraintType;
		this.tableName = tableName;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public String getConstraintType() {
		return constraintType;
	}

	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.ConstraintNotInOld.java-----------------------------------

class ConstraintNotInOld implements Serializable {

	private String constraintName;
	private String constraintType;
	private String tableName;
	private final static long serialVersionUID = 7048103733122555704L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ConstraintNotInOld() {
	}

	/**
	 * 
	 * @param constraintName
	 * @param tableName
	 * @param constraintType
	 */
	public ConstraintNotInOld(String constraintName, String constraintType,
			String tableName) {
		super();
		this.constraintName = constraintName;
		this.constraintType = constraintType;
		this.tableName = tableName;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public String getConstraintType() {
		return constraintType;
	}

	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.Constraints.java-----------------------------------

class Constraints implements Serializable {

	private List<ConstraintNotInOld> constraintNotInOld = new ArrayList<ConstraintNotInOld>();
	private List<ConstraintNotInNew> constraintNotInNew = new ArrayList<ConstraintNotInNew>();
	private final static long serialVersionUID = -7093488132149333333L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Constraints() {
	}

	/**
	 * 
	 * @param constraintNotInOld
	 * @param constraintNotInNew
	 */
	public Constraints(List<ConstraintNotInOld> constraintNotInOld,
			List<ConstraintNotInNew> constraintNotInNew) {
		super();
		this.constraintNotInOld = constraintNotInOld;
		this.constraintNotInNew = constraintNotInNew;
	}

	public List<ConstraintNotInOld> getConstraintNotInOld() {
		return constraintNotInOld;
	}

	public void setConstraintNotInOld(
			List<ConstraintNotInOld> constraintNotInOld) {
		this.constraintNotInOld = constraintNotInOld;
	}

	public List<ConstraintNotInNew> getConstraintNotInNew() {
		return constraintNotInNew;
	}

	public void setConstraintNotInNew(
			List<ConstraintNotInNew> constraintNotInNew) {
		this.constraintNotInNew = constraintNotInNew;
	}

}

// -----------------------------------Compare.Datatype.java-----------------------------------

class Datatype implements Serializable {

	private String tableName;
	private String colName;
	private String datatypeInOld;
	private String datatypeInNew;
	private final static long serialVersionUID = 1761106030772188812L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Datatype() {
	}

	/**
	 * 
	 * @param datatypeInOld
	 * @param tableName
	 * @param datatypeInNew
	 * @param colName
	 */
	public Datatype(String tableName, String colName, String datatypeInOld,
			String datatypeInNew) {
		super();
		this.tableName = tableName;
		this.colName = colName;
		this.datatypeInOld = datatypeInOld;
		this.datatypeInNew = datatypeInNew;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getDatatypeInOld() {
		return datatypeInOld;
	}

	public void setDatatypeInOld(String datatypeInOld) {
		this.datatypeInOld = datatypeInOld;
	}

	public String getDatatypeInNew() {
		return datatypeInNew;
	}

	public void setDatatypeInNew(String datatypeInNew) {
		this.datatypeInNew = datatypeInNew;
	}

}

// -----------------------------------Compare.Index.java-----------------------------------

class Index implements Serializable {

	private List<IndexNotInOld> indexNotInOld = new ArrayList<IndexNotInOld>();
	private List<IndexNotInNew> indexNotInNew = new ArrayList<IndexNotInNew>();
	private final static long serialVersionUID = 2929709803126632088L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Index() {
	}

	/**
	 * 
	 * @param indexNotInNew
	 * @param indexNotInOld
	 */
	public Index(List<IndexNotInOld> indexNotInOld,
			List<IndexNotInNew> indexNotInNew) {
		super();
		this.indexNotInOld = indexNotInOld;
		this.indexNotInNew = indexNotInNew;
	}

	public List<IndexNotInOld> getIndexNotInOld() {
		return indexNotInOld;
	}

	public void setIndexNotInOld(List<IndexNotInOld> indexNotInOld) {
		this.indexNotInOld = indexNotInOld;
	}

	public List<IndexNotInNew> getIndexNotInNew() {
		return indexNotInNew;
	}

	public void setIndexNotInNew(List<IndexNotInNew> indexNotInNew) {
		this.indexNotInNew = indexNotInNew;
	}

}

// -----------------------------------Compare.IndexNotInNew.java-----------------------------------

class IndexNotInNew implements Serializable {

	private String indexName;
	private String colName;
	private String tableName;
	private final static long serialVersionUID = -3257786493827303463L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public IndexNotInNew() {
	}

	/**
	 * 
	 * @param tableName
	 * @param indexName
	 * @param colName
	 */
	public IndexNotInNew(String indexName, String colName, String tableName) {
		super();
		this.indexName = indexName;
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.IndexNotInOld.java-----------------------------------

class IndexNotInOld implements Serializable {

	private String indexName;
	private String colName;
	private String tableName;
	private final static long serialVersionUID = 2425647193175355043L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public IndexNotInOld() {
	}

	/**
	 * 
	 * @param tableName
	 * @param indexName
	 * @param colName
	 */
	public IndexNotInOld(String indexName, String colName, String tableName) {
		super();
		this.indexName = indexName;
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.KeyNotInNew.java-----------------------------------

class KeyNotInNew implements Serializable {

	private String keyName;
	private String colName;
	private String tableName;
	private final static long serialVersionUID = 4960867322228697021L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public KeyNotInNew() {
	}

	/**
	 * 
	 * @param keyName
	 * @param tableName
	 * @param colName
	 */
	public KeyNotInNew(String keyName, String colName, String tableName) {
		super();
		this.keyName = keyName;
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.KeyNotInOld.java-----------------------------------

class KeyNotInOld implements Serializable {

	private String keyName;
	private String colName;
	private String tableName;
	private final static long serialVersionUID = -8235531506277642822L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public KeyNotInOld() {
	}

	/**
	 * 
	 * @param keyName
	 * @param tableName
	 * @param colName
	 */
	public KeyNotInOld(String keyName, String colName, String tableName) {
		super();
		this.keyName = keyName;
		this.colName = colName;
		this.tableName = tableName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.Keys.java-----------------------------------

class Keys implements Serializable {

	private List<KeyNotInOld> keyNotInOld = new ArrayList<KeyNotInOld>();
	private List<KeyNotInNew> keyNotInNew = new ArrayList<KeyNotInNew>();
	private final static long serialVersionUID = 5381669431282445495L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Keys() {
	}

	/**
	 * 
	 * @param keyNotInNew
	 * @param keyNotInOld
	 */
	public Keys(List<KeyNotInOld> keyNotInOld, List<KeyNotInNew> keyNotInNew) {
		super();
		this.keyNotInOld = keyNotInOld;
		this.keyNotInNew = keyNotInNew;
	}

	public List<KeyNotInOld> getKeyNotInOld() {
		return keyNotInOld;
	}

	public void setKeyNotInOld(List<KeyNotInOld> keyNotInOld) {
		this.keyNotInOld = keyNotInOld;
	}

	public List<KeyNotInNew> getKeyNotInNew() {
		return keyNotInNew;
	}

	public void setKeyNotInNew(List<KeyNotInNew> keyNotInNew) {
		this.keyNotInNew = keyNotInNew;
	}

}

// -----------------------------------Compare.RowCount.java-----------------------------------

class RowCount implements Serializable {

	private String tableName;
	private long rowCountOld;
	private long rowCountNew;
	private final static long serialVersionUID = -5107110482036947976L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public RowCount() {
	}

	/**
	 * 
	 * @param rowCountNew
	 * @param tableName
	 * @param rowCountOld
	 */
	public RowCount(String tableName, long rowCountOld, long rowCountNew) {
		super();
		this.tableName = tableName;
		this.rowCountOld = rowCountOld;
		this.rowCountNew = rowCountNew;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getRowCountOld() {
		return rowCountOld;
	}

	public void setRowCountOld(long rowCountOld) {
		this.rowCountOld = rowCountOld;
	}

	public long getRowCountNew() {
		return rowCountNew;
	}

	public void setRowCountNew(long rowCountNew) {
		this.rowCountNew = rowCountNew;
	}

}

// -----------------------------------Compare.TableCount.java-----------------------------------

class TableCount implements Serializable {

	private long countOfTablesinOld;
	private long countOfTablesinNew;
	private final static long serialVersionUID = -4399907981626255838L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TableCount() {
	}

	/**
	 * 
	 * @param countOfTablesinNew
	 * @param countOfTablesinOld
	 */
	public TableCount(long countOfTablesinOld, long countOfTablesinNew) {
		super();
		this.countOfTablesinOld = countOfTablesinOld;
		this.countOfTablesinNew = countOfTablesinNew;
	}

	public long getCountOfTablesinOld() {
		return countOfTablesinOld;
	}

	public void setCountOfTablesinOld(long countOfTablesinOld) {
		this.countOfTablesinOld = countOfTablesinOld;
	}

	public long getCountOfTablesinNew() {
		return countOfTablesinNew;
	}

	public void setCountOfTablesinNew(long countOfTablesinNew) {
		this.countOfTablesinNew = countOfTablesinNew;
	}

}

// -----------------------------------Compare.TableName.java-----------------------------------

class TableName implements Serializable {

	private List<TablesNotInOld> tablesNotInOld = new ArrayList<TablesNotInOld>();
	private List<TablesNotInNew> tablesNotInNew = new ArrayList<TablesNotInNew>();
	private final static long serialVersionUID = 2416729259257540715L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TableName() {
	}

	/**
	 * 
	 * @param tablesNotInNew
	 * @param tablesNotInOld
	 */
	public TableName(List<TablesNotInOld> tablesNotInOld,
			List<TablesNotInNew> tablesNotInNew) {
		super();
		this.tablesNotInOld = tablesNotInOld;
		this.tablesNotInNew = tablesNotInNew;
	}

	public List<TablesNotInOld> getTablesNotInOld() {
		return tablesNotInOld;
	}

	public void setTablesNotInOld(List<TablesNotInOld> tablesNotInOld) {
		this.tablesNotInOld = tablesNotInOld;
	}

	public List<TablesNotInNew> getTablesNotInNew() {
		return tablesNotInNew;
	}

	public void setTablesNotInNew(List<TablesNotInNew> tablesNotInNew) {
		this.tablesNotInNew = tablesNotInNew;
	}

}

// -----------------------------------Compare.TableWithDiffCase.java-----------------------------------

class TableWithDiffCase implements Serializable {

	private String tableNameOld;
	private String tableNameNew;
	private final static long serialVersionUID = 1414900676399308238L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TableWithDiffCase() {
	}

	/**
	 * 
	 * @param tableNameOld
	 * @param tableNameNew
	 */
	public TableWithDiffCase(String tableNameOld, String tableNameNew) {
		super();
		this.tableNameOld = tableNameOld;
		this.tableNameNew = tableNameNew;
	}

	public String getTableNameOld() {
		return tableNameOld;
	}

	public void setTableNameOld(String tableNameOld) {
		this.tableNameOld = tableNameOld;
	}

	public String getTableNameNew() {
		return tableNameNew;
	}

	public void setTableNameNew(String tableNameNew) {
		this.tableNameNew = tableNameNew;
	}

}

// -----------------------------------Compare.TablesNotInNew.java-----------------------------------

class TablesNotInNew implements Serializable {

	private String tableName;
	private final static long serialVersionUID = 1730815182723017104L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TablesNotInNew() {
	}

	/**
	 * 
	 * @param tableName
	 */
	public TablesNotInNew(String tableName) {
		super();
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

// -----------------------------------Compare.TablesNotInOld.java-----------------------------------

class TablesNotInOld implements Serializable {

	private String tableName;
	private final static long serialVersionUID = -4043652332194883097L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TablesNotInOld() {
	}

	/**
	 * 
	 * @param tableName
	 */
	public TablesNotInOld(String tableName) {
		super();
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
