<%@ include file="fragments/header.jspf" %>
<h2>Search Rooms</h2>
<form class="bar" method="get" action="<c:url value='/rooms'/>">
  <label>Category
    <select name="category">
      <option value="">All</option>
      <c:forEach var="c" items="${categories}">
        <option value="${c}" ${param.category == c.name() ? 'selected' : ''}>${c}</option>
      </c:forEach>
    </select>
  </label>
  <label>Check-in <input type="date" name="checkIn" min="${today}" value="<c:out value='${param.checkIn}'/>"></label>
  <label>Check-out <input type="date" name="checkOut" min="${today}" value="<c:out value='${param.checkOut}'/>"></label>
  <button class="btn" type="submit">Search</button>
</form>

<c:if test="${empty rooms}"><p class="empty">No rooms available for these filters.</p></c:if>
<div class="grid">
  <c:forEach var="r" items="${rooms}">
    <div class="card">
      <span class="tag ${r.category}">${r.category}</span>
      <h3>Room <c:out value="${r.roomNumber}"/></h3>
      <div class="muted">Up to ${r.capacity} guests</div>
      <div class="price">&#8377;<fmt:formatNumber value="${r.pricePerNight}" groupingUsed="true" maxFractionDigits="0"/> <span class="muted">/ night</span></div>
      <div class="actions">
        <a class="btn" href="<c:url value='/book/${r.id}'><c:param name='checkIn' value='${param.checkIn}'/><c:param name='checkOut' value='${param.checkOut}'/></c:url>">Book</a>
      </div>
    </div>
  </c:forEach>
</div>
<%@ include file="fragments/footer.jspf" %>
