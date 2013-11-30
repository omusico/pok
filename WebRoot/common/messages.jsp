		<logic:messagesPresent message="true">
			<div id="sMessage">
				<ul>
				<html:messages id="message" message="true">
					<li><font color="#FF0000"><c:out value="${message}"/></font></li>
				</html:messages>
				</ul>
			</div>
			<script type="text/javascript">
				$j(function() {
					$j("#sMessage").effect("highlight", {}, 10000, function() {
						$j(this).fadeOut(1000);
					});
				});
			</script>
		</logic:messagesPresent>
		
		<c:if test="${not empty customMessages}">
			<div id="cMessage">
				<ul>
				<c:forEach var="cMessage" items="${customMessages}">
					<li><font color="#FF0000"><c:out value="${cMessage}"/></font></li>
				</c:forEach>
				</ul>
			</div>
			<script type="text/javascript">
				$j(function() {
					$j("#cMessage").effect("highlight", {}, 10000, function() {
						$j(this).fadeOut(1000);
					});
				});
			</script>
		</c:if>