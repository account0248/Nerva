<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="jp.co.vaile.nerva.transferApplication.TransferApplicationToStringDTO"%>
<%@ page
	import="jp.co.vaile.nerva.transferApplication.ErrorTransferApplicationDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<title>移管申請</title>
</head>

<%
	ArrayList<TransferApplicationToStringDTO> transferApplicationArr = (ArrayList<TransferApplicationToStringDTO>) request
			.getAttribute("transferApplicationArr");
	ArrayList<ErrorTransferApplicationDTO> errorTransferApplicationArr = (ArrayList<ErrorTransferApplicationDTO>) request
			.getAttribute("errorTransferApplicationArr");
%>
<body>

	<input type="button" value="戻る" onclick="location.href='./menu.jsp'">
	<div class="center">
		<div class="title">移管承認</div>
		<br> <br> <font color="red"><b id="errorList"></b></font>
	</div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th></th>
					<th>申請番号</th>
					<th>申請者</th>
					<th>申請者所属会社</th>
					<th>配属変更従業員名</th>
					<th>現在の所属チーム</th>
					<th>配属申請先チーム</th>
					<th>配属先変更希望日</th>

				</tr>
			</thead>
			<tbody>
				<form name="application" action="TransferApplicationAction"
					method="get">
					<input type="hidden" name="applicationButton"
						id="trasferApplication" value="null"> <input type="hidden"
						name="transferEmpName" id="trasferApplications" value="">
				</form>
				<%
					for (int i = 0; i < transferApplicationArr.size(); i++) {
						TransferApplicationToStringDTO transferApplication = transferApplicationArr.get(i);
				%>

				<tr>
					<td><span><input type="button"
							onclick="approveBtn('approve<%=transferApplication.getApplicationNum()%>')"
							value="承認" id="承認<%=transferApplication.getApplicationNum()%>">
							<input type="button"
							onclick="rejectBtn('reject<%=transferApplication.getApplicationNum()%>')"
							value="却下" name="<%=transferApplication.getApplicationNum()%>"
							id="reject"> </span></td>
					<td><%=transferApplication.getApplicationNum()%></td>
					<td><%=transferApplication.getApplicant()%></td>
					<td><%=transferApplication.getApplicationBelongCompany()%></td>
					<td><%=transferApplication.getTransferEmp()%></td>
					<td><%=transferApplication.getNowApplicationTeam()%></td>
					<td><%=transferApplication.getTransferApplicationTeam()%></td>
					<td><%=transferApplication.getTransferPreferredDate()%></td>
				</tr>
			</tbody>
			</form>
			<%
				}
			%>
		</table>
	</div>



	<script src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css"
		href="../js/jquery-ui/jquery-ui.css">
	<script src="../js/transferApplication.js" charset="UTF-8"></script>

	<div id="dialog-confirm" style="display: none;">
		<%
			for (int i = 0; i < errorTransferApplicationArr.size(); i++) {
				ErrorTransferApplicationDTO errorTransferApplicationDTO = new ErrorTransferApplicationDTO();
				errorTransferApplicationDTO = errorTransferApplicationArr.get(i);
				String errorMsg = errorTransferApplicationDTO.getErrorMsg();
				TransferApplicationToStringDTO errorTransferApplication = new TransferApplicationToStringDTO();
				errorTransferApplication = errorTransferApplicationDTO.getTransferApplicationErrorDTO();
		%>
		<input type="hidden" id="errorTransferApplication"
			value="<%=errorMsg%>">
		<div>
			<font color="red"><%=errorMsg%></font>
			<table border="1">
				<thead>
					<tr>
						<th>申請番号</th>
						<th>申請者</th>
						<th>申請者所属会社</th>
						<th>配属変更従業員名</th>
						<th>現在の所属チーム</th>
						<th>配属申請先チーム</th>
						<th>配属先変更希望日</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=errorTransferApplication.getApplicationNum()%></td>
						<td><%=errorTransferApplication.getApplicant()%></td>
						<td><%=errorTransferApplication.getApplicationBelongCompany()%></td>
						<td><%=errorTransferApplication.getTransferEmp()%></td>
						<td><%=errorTransferApplication.getNowApplicationTeam()%></td>
						<td><%=errorTransferApplication.getTransferApplicationTeam()%></td>
						<td><%=errorTransferApplication.getTransferPreferredDate()%></td>
					</tr>
				</tbody>
			</table>
		</div>
		<%
			}
		%>

	</div>
</body>
</html>