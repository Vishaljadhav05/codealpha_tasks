<%@ include file="fragments/header.jspf"%>
<h2>My Bookings</h2>
<form class="bar" method="get" action="<c:url value='/bookings'/>">
	<label>Guest email <input type="email" name="email" size="28"
		placeholder="leave empty for all"
		value="<c:out value='${param.email}'/>">
	</label>
	<button class="btn" type="submit">Find bookings</button>
</form>

<c:if test="${empty bookings}">
	<p class="empty">No bookings found.</p>
</c:if>
<div class="grid">
	<c:forEach var="b" items="${bookings}">
		<div class="card">
			<div>
				<span class="tag ${b.status}">${b.status}</span>
			</div>
			<h3>
				#${b.id} &middot; Room
				<c:out value="${b.room.roomNumber}" />
			</h3>
			<div class="muted">
				<c:out value="${b.guestName}" />
				<br>${b.checkIn} &rarr; ${b.checkOut}
			</div>
			<div class="price">
				&#8377;
				<fmt:formatNumber value="${b.totalAmount}" groupingUsed="true"
					maxFractionDigits="2" />
			</div>
			<div class="actions">
				<a class="btn ghost" href="<c:url value='/bookings/${b.id}'/>">Details</a>
				<c:if test="${b.status == 'PENDING'}">
					<a class="btn" href="<c:url value='/payment/${b.id}'/>">Pay</a>
				</c:if>
				<c:if test="${b.status != 'CANCELLED'}">
					<form method="post"
						action="<c:url value='/bookings/${b.id}/cancel'/>"
						onsubmit="return confirm('Cancel booking #${b.id}?')">
						<button class="btn danger" type="submit">Cancel</button>
					</form>
				</c:if>
			</div>
		</div>
	</c:forEach>
</div>
<%@ include file="fragments/footer.jspf"%>
