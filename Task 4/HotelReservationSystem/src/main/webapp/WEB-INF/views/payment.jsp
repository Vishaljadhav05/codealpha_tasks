<%@ include file="fragments/header.jspf"%>
<h2>Pay for Booking #${booking.id}</h2>
<div class="panel">
	<div class="kv">
		<span>Room</span><span><c:out
				value="${booking.room.roomNumber}" /> (${booking.room.category})</span> <span>Dates</span><span>${booking.checkIn}
			&rarr; ${booking.checkOut}</span> <span>Amount</span><span class="price">&#8377;<fmt:formatNumber
				value="${booking.totalAmount}" groupingUsed="true"
				maxFractionDigits="2" /></span>
	</div>
</div>

<form class="panel" method="post"
	action="<c:url value='/payment/${booking.id}'/>">
	<label>Payment method <select name="method" id="method"
		onchange="toggleCard()">
			<option value="CARD">Card</option>
			<option value="UPI">UPI</option>
			<option value="CASH">Cash (pay at hotel)</option>
	</select>
	</label> <label id="cardBox">Card number <input type="text"
		name="cardNumber" placeholder="4111111111111111" maxlength="19">
		<span class="muted">Simulation: card numbers ending in 0 are
			declined.</span>
	</label>
	<div class="actions">
		<button class="btn" type="submit">Pay now</button>
		<a class="btn ghost" href="<c:url value='/bookings/${booking.id}'/>">Pay
			later</a>
	</div>
</form>
<script>
	function toggleCard() {
		document.getElementById('cardBox').style.display = document
				.getElementById('method').value === 'CARD' ? 'flex' : 'none';
	}
	toggleCard();
</script>
<%@ include file="fragments/footer.jspf"%>
