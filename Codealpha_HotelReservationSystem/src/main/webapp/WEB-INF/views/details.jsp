<%@ include file="fragments/header.jspf"%>
<h2>
	Booking #${booking.id} <span class="tag ${booking.status}">${booking.status}</span>
</h2>
<div class="panel">
	<div class="kv">
		<span>Guest</span><span><c:out value="${booking.guestName}" />
			(<c:out value="${booking.guestEmail}" />)</span> <span>Room</span><span><c:out
				value="${booking.room.roomNumber}" /> &middot;
			${booking.room.category}</span> <span>Guests</span><span>${booking.guests}</span>
		<span>Check-in</span><span>${booking.checkIn}</span> <span>Check-out</span><span>${booking.checkOut}</span>
		<span>Total</span><span><b>&#8377;<fmt:formatNumber
					value="${booking.totalAmount}" groupingUsed="true"
					maxFractionDigits="2" /></b></span> <span>Booked on</span><span>${booking.createdAt}</span>
		<span>Payment</span> <span> <c:choose>
				<c:when test="${empty booking.payment}">Not paid yet</c:when>
				<c:otherwise>
					<span class="tag ${booking.payment.status}">${booking.payment.status}</span> via ${booking.payment.method}</c:otherwise>
			</c:choose>
		</span>
		<c:if test="${not empty booking.payment}">
			<span>Transaction ID</span>
			<span><c:out value="${booking.payment.transactionId}" /></span>
		</c:if>
	</div>
</div>
<div class="actions">
	<a class="btn ghost" href="<c:url value='/bookings'/>">&larr; All
		bookings</a>
	<c:if test="${booking.status == 'PENDING'}">
		<a class="btn" href="<c:url value='/payment/${booking.id}'/>">Pay
			now</a>
	</c:if>
	<c:if test="${booking.status != 'CANCELLED'}">
		<form method="post"
			action="<c:url value='/bookings/${booking.id}/cancel'/>"
			onsubmit="return confirm('Cancel this booking?')">
			<button class="btn danger" type="submit">Cancel booking</button>
		</form>
	</c:if>
</div>
<%@ include file="fragments/footer.jspf"%>
