<%@ include file="/WEB-INF/views/fragments/header.jspf"%>

<p>
	<a class="link" href="${pageContext.request.contextPath}/students">Back
		to class summary</a>
</p>

<div class="title-row">
	<div>
		<h1>
			<c:out value="${student.name}" />
		</h1>
		<p class="muted">
			Roll no.
			<c:out value="${student.rollNumber}" />
		</p>
	</div>
	<div class="title-actions">
		<a class="btn"
			href="${pageContext.request.contextPath}/students/${student.id}/edit">Edit
			details</a>
		<form method="post"
			action="${pageContext.request.contextPath}/students/${student.id}/delete"
			onsubmit="return confirm('Delete this student and all of their grades?');">
			<button class="btn btn-danger" type="submit">Delete student</button>
		</form>
	</div>
</div>

<c:if test="${summary.graded}">
	<section class="ledger" aria-label="Student statistics">
		<div>
			<span class="ledger-value"><fmt:formatNumber
					value="${summary.average}" minFractionDigits="1"
					maxFractionDigits="1" /></span> <span class="ledger-label">Average</span>
		</div>
		<div>
			<span class="ledger-value"><fmt:formatNumber
					value="${summary.highest}" maxFractionDigits="1" /></span> <span
				class="ledger-label">Highest</span>
		</div>
		<div>
			<span class="ledger-value"><fmt:formatNumber
					value="${summary.lowest}" maxFractionDigits="1" /></span> <span
				class="ledger-label">Lowest</span>
		</div>
		<div>
			<span class="ledger-value"><span
				class="letter letter-${summary.letter}">${summary.letter}</span></span> <span
				class="ledger-label">Overall grade</span>
		</div>
	</section>
</c:if>

<div class="two-col">
	<section>
		<h2>Grades</h2>
		<c:choose>
			<c:when test="${empty student.grades}">
				<div class="empty">
					<p>No grades recorded. Use the form to add the first subject
						score.</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="table-wrap">
					<table class="ruled">
						<thead>
							<tr>
								<th>Subject</th>
								<th class="num">Score</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="g" items="${student.grades}">
								<tr>
									<td><c:out value="${g.subject}" /></td>
									<td class="num"><fmt:formatNumber value="${g.score}"
											maxFractionDigits="1" /></td>
									<td class="actions">
										<form method="post"
											action="${pageContext.request.contextPath}/students/${student.id}/grades/${g.id}/delete">
											<button class="link-btn" type="submit">Remove</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</section>

	<section>
		<h2>Add a grade</h2>
		<form:form modelAttribute="grade"
			action="${pageContext.request.contextPath}/students/${student.id}/grades"
			method="post" cssClass="card form">
			<div class="field">
				<label for="subject">Subject</label>
				<form:input path="subject" id="subject" maxlength="60" />
				<form:errors path="subject" cssClass="error" element="span" />
			</div>
			<div class="field">
				<label for="score">Score (0–100)</label>
				<form:input path="score" id="score" type="number" step="0.1" min="0"
					max="100" />
				<form:errors path="score" cssClass="error" element="span" />
			</div>
			<div class="form-actions">
				<button class="btn btn-primary" type="submit">Add grade</button>
			</div>
		</form:form>
	</section>
</div>

<%@ include file="/WEB-INF/views/fragments/footer.jspf"%>
