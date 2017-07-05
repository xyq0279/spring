<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户新增</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="save"><a href="#" onclick="formSubmit('save','_self');this.blur();">保存</a></li>
	<li id="back"><a href="#" onclick="window.history.go(-1);">后退</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   用户新增
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>用户名</td>
		<td><input type="text" name="username"></td>
		<td>密码</td>
		<td><input type="text" name="password"></td>
	</tr>
	<tr class="odd">
		<td>真实姓名</td>
		<td><input type="text" name="userInfo.name"></td>
		<td>身份证号</td>
		<td><input type="text" name="userInfo.cardNo"></td>
	</tr>
	<tr class="odd">
		<td>生日</td>
		<td><input type="text" style="width:90px;" name="userInfo.birthday"
	   		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
		<td>性别</td>
		<td><input type="radio" name="userInfo.gender">男
			<input type="radio" name="userInfo.gender">女
		</td>
	</tr>
	<tr class="odd">
		<td>入职日期</td>
		<td><input type="text" style="width:90px;" name="userInfo.joinDay"
	   		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
		<td>电话</td>
		<td><input type="text" name="userInfo.telephone"></td>
	</tr>
	<tr class="odd">
		<td>所属部门</td>
		<td>
			<select style="width: 120px" name="dept.deptId">
				<option value="">---请选择部门---</option>
				<c:forEach items="${parentList}" var="p">
					<option value="${p.deptId }">${p.deptName}</option>
				</c:forEach>
			</select>
		</td>
		<td>直属领导</td>
		<td>
			<select style="width: 120px" name="userInfo.manager.userInfoId">
				<option value="">---无上级---</option>
				<c:forEach items="${userList}" var="u">
					<option value="${u.userInfoId }">${u.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr class="odd">
		<td>薪酬</td>
		<td><input type="text" name="userInfo.salary"></td>
		<td>职务</td>
		<td><input type="text" name="userInfo.station"></td>
	</tr>
	
	<tr class="odd">
		<td>用户级别</td>
		<td><input type="" name="userInfo.userLevel">
			<select style="width: 120px" name="userInfo.userLevel">
				<option value="1" selected="selected">总经理</option>
				<option value="4">普通用户</option>
				<option value="3">部门经理</option>
				<option value="2">副总</option>
			</select>
		</td>
		<td>状态</td>
		<td><input type="radio" name="state" value="0">停用
		<input type="radio" name="state" value="1">启用</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

