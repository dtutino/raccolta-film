<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>Insert title here</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="card">
			<h5 class="card-header">Elimina</h5>
			<div class="card-body">
				<form method="post"
					action="ExecuteEliminaRegistaServlet?idRegista=${elimina_regista_attr.id}">
					<h5 class="card-title">
						Stai per eliminare il regista con id
						${elimina_regista_attr.id}</h5>
					<p class="card-text">Sei sicuro di procedere?</p>
					<button type="submit" name="submit" id="submit"
						class="btn btn-danger">Elimina</button>
				</form>
			</div>
		</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>