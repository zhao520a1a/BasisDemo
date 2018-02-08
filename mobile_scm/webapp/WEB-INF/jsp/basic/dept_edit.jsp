<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${sysname}</title>
    <%@ include file="/WEB-INF/jsp/include/css.jsp" %>
    <%@ include file="/WEB-INF/jsp/include/js.jsp" %>  
    <script type="text/javascript" src="${ctx}/js/system/dept.js"></script>
  </head>
  
  <body>
    <my:navigation text="基础设置 >>　部门管理 >> 编辑部门" percent="90"/>
    <form action="deptManage_editDept.do" method="post" onsubmit="return checkFrm();">
      <table width="500" align="center" style="margin-top:30px" class="inputTable">
        <tr>
          <th>部门名称：</th>
          <td><input class="text" name="dept.deptName" value="${dept.deptName}"/>
            <input type="hidden" name="dept.deptId" value="${dept.deptId}" />
          </td>
        </tr>
        <tr>
          <th>联系人：</th>
          <td><input class="text" name="dept.linkman" value="${dept.linkman}"/></td>
        </tr>
        <tr>
          <th>电话：</th>
          <td><input class="text" name="dept.tel" value="${dept.tel}"/></td>
        </tr>
        <tr>
          <th>地址：</th>
          <td><textarea class="text" name="dept.address" style="width:300px;height:46px"/>${dept.address}</textarea></td>
        </tr>
        <tr>
          <th>备注：</th>
          <td><textarea class="text" name="dept.remark" style="width:300px;height:46px"/>${dept.remark}</textarea></td>
        </tr>       
      </table>
      <table border="0" width="500" align="center" style="margin-top:20px">
        <tr><td align="center">
          <input type="submit" class="btn ok" value="确定" />
          <input type="button" class="btn cancel" value="取消" onclick="location='${ctx}/deptManage_listDept.do'"/>
        </td></tr>
      </table>
    </form>    
  </body>
</html>
