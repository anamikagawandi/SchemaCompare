package src;

import java.io.*;
import java.util.*;

//-----------------------------------Compare.SchemaStruct.java-----------------------------------
public class SchemaStruct implements Serializable
{

private String schemaName;
private long tabCount;
private List<TableDetail> tableDetail = new ArrayList<TableDetail>();
private List<ColDetail> colDetail = new ArrayList<ColDetail>();
private List<ConstraintDetail> constraintDetail = new ArrayList<ConstraintDetail>();
private List<KeyDetail> keyDetail = new ArrayList<KeyDetail>();
private List<IdxDetail> idxDetails = new ArrayList<IdxDetail>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = 5782323587918836261L;

/**
* No args constructor for use in serialization
*
*/
public SchemaStruct() {
}

/**
*
* @param colDetail
* @param keyDetail
* @param schemaName
* @param tableDetail
* @param idxDetails
* @param constraintDetail
*/
public SchemaStruct(String schemaName,long tabCount,List<TableDetail> tableDetail, List<ColDetail> colDetail, List<ConstraintDetail> constraintDetail, List<KeyDetail> keyDetail, List<IdxDetail> idxDetails) {
super();
this.schemaName = schemaName;
this.tabCount = tabCount;
this.tableDetail = tableDetail;
this.colDetail = colDetail;
this.constraintDetail = constraintDetail;
this.keyDetail = keyDetail;
this.idxDetails = idxDetails;
}

public String getSchemaName() {
return schemaName;
}

public void setSchemaName(String schemaName) {
this.schemaName = schemaName;
}

public long getTabCount() {
return tabCount;
}

public void setTabCount(long tabCount) {
this.tabCount = tabCount;
}

public List<TableDetail> getTableDetail() {
return tableDetail;
}

public void setTableDetail(List<TableDetail> tableDetail) {
this.tableDetail = tableDetail;
}

public List<ColDetail> getColDetail() {
return colDetail;
}

public void setColDetail(List<ColDetail> colDetail) {
this.colDetail = colDetail;
}

public List<ConstraintDetail> getConstraintDetail() {
return constraintDetail;
}

public void setConstraintDetail(List<ConstraintDetail> constraintDetail) {
this.constraintDetail = constraintDetail;
}

public List<KeyDetail> getKeyDetail() {
return keyDetail;
}

public void setKeyDetail(List<KeyDetail> keyDetail) {
this.keyDetail = keyDetail;
}

public List<IdxDetail> getIdxDetails() {
return idxDetails;
}

public void setIdxDetails(List<IdxDetail> idxDetails) {
this.idxDetails = idxDetails;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}


//-----------------------------------Compare.ColDetail.java-----------------------------------
class ColDetail implements Serializable
{

private String tabName;
private String colName;
private String datatype;
private String colSize;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = 4348328290511955826L;

/**
* No args constructor for use in serialization
*
*/
public ColDetail() {
}

/**
*
* @param tabName
* @param colSize
* @param datatype
* @param colName
*/
public ColDetail(String tabName, String colName, String datatype, String colSize) {
super();
this.tabName = tabName;
this.colName = colName;
this.datatype = datatype;
this.colSize = colSize;
}

public String getTabName() {
return tabName;
}

public void setTabName(String tabName) {
this.tabName = tabName;
}

public String getColName() {
return colName;
}

public void setColName(String colName) {
this.colName = colName;
}

public String getDatatype() {
return datatype;
}

public void setDatatype(String datatype) {
this.datatype = datatype;
}

public String getColSize() {
return colSize;
}

public void setColSize(String colSize) {
this.colSize = colSize;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
//-----------------------------------Compare.ConstraintDetail.java-----------------------------------


class ConstraintDetail implements Serializable
{
private String constraintName;
private String constraintType;
private String constraintTab;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -8607554026301691563L;

/**
* No args constructor for use in serialization
*
*/
public ConstraintDetail() {
}

/**
*
* @param constraintName
* @param constraintTab
* @param constraintType
*/
public ConstraintDetail(String constraintName, String constraintType, String constraintTab) {
super();
this.constraintName = constraintName;
this.constraintType = constraintType;
this.constraintTab = constraintTab;
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

public String getConstraintTab() {
return constraintTab;
}

public void setConstraintTab(String constraintTab) {
this.constraintTab = constraintTab;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
//-----------------------------------Compare.IdxDetail.java-----------------------------------


class IdxDetail implements Serializable
{

private String idxName;
private String idxCol;
private String idxTab;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -8678143115215220376L;

/**
* No args constructor for use in serialization
*
*/
public IdxDetail() {
}

/**
*
* @param idxName
* @param idxTab
* @param idxCol
*/
public IdxDetail(String idxName, String idxCol, String idxTab) {
super();
this.idxName = idxName;
this.idxCol = idxCol;
this.idxTab = idxTab;
}

public String getIdxName() {
return idxName;
}

public void setIdxName(String idxName) {
this.idxName = idxName;
}

public String getIdxCol() {
return idxCol;
}

public void setIdxCol(String idxCol) {
this.idxCol = idxCol;
}

public String getIdxTab() {
return idxTab;
}

public void setIdxTab(String idxTab) {
this.idxTab = idxTab;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
//-----------------------------------Compare.KeyDetail.java-----------------------------------



class KeyDetail implements Serializable
{

private String keyName;
private String keyCol;
private String keyTab;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -9159915122736806454L;

/**
* No args constructor for use in serialization
*
*/
public KeyDetail() {
}

/**
*
* @param keyName
* @param keyTab
* @param keyCol
*/
public KeyDetail(String keyName, String keyCol, String keyTab) {
super();
this.keyName = keyName;
this.keyCol = keyCol;
this.keyTab = keyTab;
}

public String getKeyName() {
return keyName;
}

public void setKeyName(String keyName) {
this.keyName = keyName;
}

public String getKeyCol() {
return keyCol;
}

public void setKeyCol(String keyCol) {
this.keyCol = keyCol;
}

public String getKeyTab() {
return keyTab;
}

public void setKeyTab(String keyTab) {
this.keyTab = keyTab;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}



//-----------------------------------Compare.TableDetail.java-----------------------------------


class TableDetail implements Serializable
{

private String tabName;
private long tabSize;
private long tabRow;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -6714899176956933550L;

/**
* No args constructor for use in serialization
*
*/
public TableDetail() {
}

public TableDetail(String tabName,long tabRow) {
	super();
	this.tabName = tabName;
	this.tabRow = tabRow;
}
/**
*
* @param tabRow
* @param tabName
* @param tabSize
*/
public TableDetail(String tabName, long tabSize, long tabRow) {
super();
this.tabName = tabName;
this.tabSize = tabSize;
this.tabRow = tabRow;
}

public String getTabName() {
return tabName;
}

public void setTabName(String tabName) {
this.tabName = tabName;
}

public long getTabSize() {
return tabSize;
}

public void setTabSize(long tabSize) {
this.tabSize = tabSize;
}

public long getTabRow() {
return tabRow;
}

public void setTabRow(long tabRow) {
this.tabRow = tabRow;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}

