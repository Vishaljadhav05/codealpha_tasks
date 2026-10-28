<%@ include file="fragments/header.jspf"%>
<h2>
	Book Room
	<c:out value="${room.roomNumber}" />
	<span class="tag ${room.category}">${room.category}</span>
</h2>
<p class="muted">
	&#8377;
	<fmt:formatNumber value="${room.pricePerNight}" groupingUsed="true"
		maxFractionDigits="0" />
	per night &middot; up to ${room.capacity} guests
</p>

<form class="panel" method="post" action="<c:url value='/book'/>">
	<input type="hidden" name="roomId" value="${room.id}"> <label>Full
		name <input type="text" name="guestName"
		value="<c:out value='${guestName}'/>" required>
	</label> <label>Email <input type="email" name="guestEmail"
		value="<c:out value='${guestEmail}'/>" required></label>
	<div class="row">
		<label>Check-in <input type="date" name="checkIn"
			min="${today}" value="${checkIn}" required></label> <label>Check-out
			<input type="date" name="checkOut" min="${today}" value="${checkOut}"
			required>
		</label>
	</div>
	<label>Guests <input type="number" name="guests" min="1"
		max="${room.capacity}" value="${guests}" required></label>
	<div class="actions">
		<button class="btn" type="submit">Reserve</button>
		<a class="btn ghost" href="<c:url value='/rooms'/>">Back</a>
	</div>
</form>
<%@ include file="fragments/footer.jspf"%>
