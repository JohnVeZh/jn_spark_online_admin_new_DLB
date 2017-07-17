/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */


package com.easecom.common.util;

/**
 * <p>Title: 查询数据结构类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Merit</p>
 * @author Shark
 * @version 1.0
 */

public class QueryCond {
  private String fieldName;
  private String fieldVal;
  private String operate;
  private String fieldType;
  public QueryCond() {
  }

  public QueryCond(String fieldName,
                    String fieldType,
                    String operate,
                    String fieldVal
                    ) {
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.operate = operate;
    this.fieldVal = fieldVal;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldVal() {
    return fieldVal;
  }

  public void setFieldVal(String fieldVal) {
    this.fieldVal = fieldVal;
  }

  public String getOperate() {
    return operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }

}
