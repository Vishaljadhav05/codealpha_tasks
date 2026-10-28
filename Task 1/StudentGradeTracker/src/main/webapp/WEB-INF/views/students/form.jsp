<%@ include file="/WEB-INF/views/fragments/header.jspf"%>

<h1>
	<c:choose>
		<c:when test="${not empty student.id}">Edit student</c:when>
		<c:otherwise>Add student</c:otherwise>
	</c:choose>
</h1>

<form:form modelAttribute="student"
	action="${pageContext.request.contextPath}${action}" method="post"
	cssClass="card form">
	<div class="field">
		<label for="rollNumber">Roll number</label>
		<form:input path="rollNumber" id="rollNumber" maxlength="20"
			autofocus="autofocus" />
		<form:errors path="rollNumber" cssClass="error" element="span" />
	</div>
	<div class="field">
		<label for="name">Full name</label>
		<form:input path="name" id="name" maxlength="100" />
		<form:errors path="name" cssClass="error" element="span" />
	</div>
	<div class="form-actions">
		<button class="btn btn-primary" type="submit">Save student</button>
		<a class="link" href="${pageContext.request.contextPath}/students">Cancel</a>
	</div>
</form:form>

<%@ include file="/WEB-INF/views/fragments/footer.jspf"%>
