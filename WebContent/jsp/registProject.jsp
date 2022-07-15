<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.OrderSourceDTO"%>
<%@ page import="jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/general.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<!-- JavaScript -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/list.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/jquery-ui/jquery-ui.css">
<%
//sessionから受け取り
String userName = (String) session.getAttribute("userName");
String belongCompanyId = (String) session.getAttribute("companyId");
// 表示処理から受け取り
String belongCompanyName = (String) request.getAttribute("companyName");
List<OrderSourceDTO> orderSourceDTOList = (List<OrderSourceDTO>) request.getAttribute("orderSourceDTOList");
List<FetchAnyMasterTableDTO> industryDTOList = (List<FetchAnyMasterTableDTO>) request.getAttribute("industryDTOList");

// エラー＆初期値からの受け取り
List<String> errorMsgList = (List<String>) request.getAttribute("errorMsgList");
String defaultProjectId = (String) request.getAttribute("defaultProjectId");
String defaultProjectName = (String) request.getAttribute("defaultProjectName");
String defaultOrderSource = (String) request.getAttribute("defaultOrderSource");
String defaultIndustryId = (String) request.getAttribute("defaultIndustryId");
String defaultProjectStartDate = (String) request.getAttribute("defaultProjectStartDate");
String defaultProjectCompleteDate = (String) request.getAttribute("defaultProjectCompleteDate");
%>
<title>案件登録</title>
</head>
<body>

	<input type="button" value="戻る"
		onclick="location.href='./ShowProjectAction'">
	<form action="RegistProjectAction" method="post" name="form">
		<div class="center">
			<div class="title">案件登録</div>
			
			<br>
			<%
			if (errorMsgList != null) {
				for (int i = 0; i < errorMsgList.size(); i++) {
			%>
			<div class="errorMessage">
				<%=errorMsgList.get(i)%>
			</div>
			<%
				}
			}
			%>
			<br>
			
			<p class="partition-around">
				<span> 案件ID <input required class="required" type="text"
					name="projectId"
					value="<%if (defaultProjectId != null)%><%=defaultProjectId%>">
				</span> <span> 案件名<input required class="required" type="text"
					name="projectName"
					value="<%if (defaultProjectName != null)%><%=defaultProjectName%>">
				</span> <span> 責任者<input type="text" value="<%=userName%>"
					disabled="disabled">
				</span>
			</p>
			<p class="partition-around">
				<span> 受注元<input type="text" value="<%=belongCompanyName%>"
					disabled="disabled">
				</span> <span> 発注元<input required class="required" type="text"
					name="orderSource" list="orderSourceList"
					value="<%if (defaultOrderSource != null)%><%=defaultOrderSource%>"
					autocomplete="off"> <datalist id="orderSourceList">
						<option value=""></option>
						<%
						List<String> orderSourceList = new ArrayList<>();

						for (int i = 0; i < orderSourceDTOList.size(); i++) {
							String orderSource = orderSourceDTOList.get(i).getOrderSourceName();
							orderSourceList.add(orderSource);
						}

						List<String> dedupOrderSourceList = new ArrayList<>(new HashSet<>(orderSourceList));

						for (int i = 0; i < dedupOrderSourceList.size(); i++) {
						%>
						<option value="<%=dedupOrderSourceList.get(i)%>"></option>
						<%
						}
						%>
					</datalist>
				</span> <span> 業種 <select required class="required"
					name="industryId">
						<option
							value="<%if (defaultIndustryId != null)%><%=defaultIndustryId%>">
							<%
							for (int i = 0; i < industryDTOList.size(); i++) {
							%>
							<%
							if (industryDTOList.get(i).getMasterDataId().equals(defaultIndustryId))
							%><%=industryDTOList.get(i).getMasterData()%>
							<%
							}
							%>
						</option>
						<%
						for (int i = 0; i < industryDTOList.size(); i++) {
						%>
						<option value="<%=industryDTOList.get(i).getMasterDataId()%>"><%=industryDTOList.get(i).getMasterData()%></option>
						<%
						}
						%>
				</select>
				</span>
			</p>
			<br>
			<p class="partition-around">
				<span> 案件開始日<input required class="required" type="date"
					name="projectStartDate"
					value="<%if (defaultProjectStartDate != null)%><%=defaultProjectStartDate%>">
				</span> <span> 案件完了日<input type="date" name="projectCompleteDate"
					value="<%if (defaultProjectCompleteDate != null)%><%=defaultProjectCompleteDate%>">
				</span>
			</p>
		</div>
		<p class="partition-center">
			<span> <input type="button" id="btnOpenDialog2" value="登録" />
			</span>
		</p>
	</form>
	<div id="dialog-confirm" style="display: none;">この内容で登録しますか？</div>
	<script type="text/javascript" src="../js/registProject.js"></script>
</body>
</html>
