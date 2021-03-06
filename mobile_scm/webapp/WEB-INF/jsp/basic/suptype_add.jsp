<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.beifeng.mobile_scm.system.entity.SysRole"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${sysname}</title>
    <%@ include file="/WEB-INF/jsp/include/css.jsp" %>
    <%@ include file="/WEB-INF/jsp/include/js.jsp" %>  
    <script type="text/javascript" src="${ctx}/js/basic/supType.js"></script>
  </head>
  
  <body>
    <my:navigation text="基础设置 >>　供应商类别管理 >> 添加类别" percent="90"/>
    <form action="supType_addType.do" method="post" onsubmit="return checkFrm();">
      <table width="500" align="center" style="margin-top:30px" class="inputTable">
        <tr>
          <th>供应商类别编号：</th>
          <td><input class="text" name="supType.suptypeid" value="${supType.suptypeid}"/><my:errInfo key="typeIdDup"/></td>
        </tr>
        <tr>
          <th>类别名称：</th>
          <td><input class="text" name="supType.suptypename"  value="${supType.suptypename}"/></td>
        </tr>        
        <tr>
          <th>备注：</th>
          <td><textarea class="text" name="supType.remark" style="width:300px;height:46px"/>${supType.remark}</textarea></td>
        </tr>       
      </table>
      <table border="0" width="500" align="center" style="margin-top:20px">
        <tr><td align="center">
          <input type="submit" class="btn ok" value="确定" />
          <input type="button" class="btn cancel" value="取消" onclick="location='${ctx}/supType_list.do'"/>
        </td></tr>
      </table>
    </form>    
  </body>
</html>
