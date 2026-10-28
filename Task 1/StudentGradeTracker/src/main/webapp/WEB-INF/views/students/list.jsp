<%@ include file="/WEB-INF/views/fragments/header.jspf"%>

<h1>Class summary</h1>

<c:choose>
	<c:when test="${stats.graded}">
		<section class="ledger" aria-label="Class statistics">
			<div>
				<span class="ledger-value"><fmt:formatNumber
						value="${stats.average}" minFractionDigits="1"
						maxFractionDigits="1" /></span> <span class="ledger-label">Class
					average</span>
			</div>
			<div>
				<span class="ledger-value"><fmt:formatNumber
						value="${stats.highest}" maxFractionDigits="1" /></span> <span
					class="ledger-label">Highest score</span>
			</div>
			<div>
				<span class="ledger-value"><fmt:formatNumber
						value="${stats.lowest}" maxFractionDigits="1" /></span> <span
					class="ledger-label">Lowest score</span>
			</div>
			<div>
				<span class="ledger-value small"><c:out
						value="${stats.topStudent}" /></span> <span class="ledger-label">Highest
					average</span>
			</div>
			<div>
				<span class="ledger-value small"><c:out
						value="${stats.lowestStudent}" /></span> <span class="ledger-label">Lowest
					average</span>
			</div>
		</section>
	</c:when>
</c:choose>

<form class="search" method="get"
	action="${pageContext.request.contextPath}/students">
	<label for="q" class="sr-only">Search students</label> <input id="q"
		type="search" name="q" value="<c:out value='${q}'/>"
		placeholder="Search by name or roll number">
	<button class="btn" type="submit">Search</button>
	<c:if test="${not empty q}">
		<a class="link" href="${pageContext.request.contextPath}/students">Clear</a>
	</c:if>
</form>

<c:choose>
	<c:when test="${empty students}">
		<div class="empty">
			<c:choose>
				<c:when test="${empty q}">
					<p>No students yet. Add your first student to start recording
						grades.</p>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/students/new">Add
						student</a>
				</c:when>
				<c:otherwise>
					<p>
						No students match “
						<c:out value="${q}" />
						”. Try a different name or roll number.
					</p>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<div class="table-wrap">
			<table class="ruled">
				<thead>
					<tr>
						<th>Roll no.</th>
						<th>Student</th>
						<th class="num">Subjects</th>
						<th class="num">Average</th>
						<th class="num">Highest</th>
						<th class="num">Lowest</th>
						<th>Grade</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${students}">
						<tr>
							<td><c:out value="${s.rollNumber}" /></td>
							<td><a class="link strong"
								href="${pageContext.request.contextPath}/students/${s.id}"><c:out
										value="${s.name}" /></a></td>
							<td class="num">${s.subjectCount}</td>
							<c:choose>
								<c:when test="${s.graded}">
									<td class="num"><fmt:formatNumber value="${s.average}"
											minFractionDigits="1" maxFractionDigits="1" /></td>
									<td class="num"><fmt:formatNumber value="${s.highest}"
											maxFractionDigits="1" /></td>
									<td class="num"><fmt:formatNumber value="${s.lowest}"
											maxFractionDigits="1" /></td>
									<td><span class="letter letter-${s.letter}">${s.letter}</span></td>
								</c:when>
								<c:otherwise>
									<td class="num muted" colspan="3">No grades yet</td>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td class="actions"><a class="link"
								href="${pageContext.request.contextPath}/students/${s.id}">Grades</a>
								<a class="link"
								href="${pageContext.request.contextPath}/students/${s.id}/edit">Edit</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/views/fragments/footer.jspf"%>
