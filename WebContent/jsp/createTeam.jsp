<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.vaile.nerva.createTeam.CreateTeamInfoPageDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../js/jquery-ui/jquery-ui.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/list.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<title>チーム作成</title>
</head>
<body>
<%
String companyName =  (String) session.getAttribute("companyName");
String companyCode =  (String) session.getAttribute("companyCode");

%>


<a href="./ShowSearchTeamAction"> <input type="button" value="戻る"></a>
	<div class="center">
		<div class="title">チーム作成</div>
		<br>
		<%
			CreateTeamInfoPageDTO createTeamInfoPageDTO = (CreateTeamInfoPageDTO) request
					.getAttribute("createTeamInfoPageDTO");

			List<String> errorList = (List<String>) request.getAttribute("errorList");
			if (errorList != null) {
		%>
		<div class="errorMessage"> <%
 	for (int i = 0; i < errorList.size(); i++) {
 			out.println(errorList.get(i));
 			out.print("<br>");
 		}
 %>
		</div>
		<%
			}
		%>
		<form method="Post" action="CreateTeamAction" name="createTeamAction">
			<p class="partition-around">
				<span>チームID <input required  class="required" type="text" name="teamId"  placeholder="T0000<%=companyCode%>0000"value="<%if (createTeamInfoPageDTO != null) {%><%=createTeamInfoPageDTO.getTeamId()%><%}%>"></span>
				<span>チーム名<input required class="required" type="text" name="teamName" value="<%if (createTeamInfoPageDTO != null) {%><%=createTeamInfoPageDTO.getTeamName()%><%}%>">
				</span>
			</p>
			<br>
			<p class="partition-around">
				<span>所属会社 <input type="text"
					value= "<%=companyName%>" name="companyId" readonly style="background-color: #e9e9e9;"></span>
				<span>所属部長 <input type="text"
					value="<%=session.getAttribute("userName")%>" name="belongManager"
					readonly style="background-color: #e9e9e9;"></span>
			</p>
			<br>
			<p class="partition-center">
				<span><input type="button" id="btnOpenDialog2" value="作成" /></span>
			</p>
		</form>
	</div>
	<div id="dialog-confirm" style="display:none;">この内容で作成しますか？</div>
	<script type="text/javascript" src="../js/createTeam.js"></script>
</body>
</html>
