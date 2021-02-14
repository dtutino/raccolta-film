<!doctype html>
<%@page import="it.prova.raccoltafilm.model.Regista"%>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica regista</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteModificaRegistaServlet?idRegista=${modifica_regista_attr.id}" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${modifica_regista_attr.nome}">
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${modifica_regista_attr.cognome}">
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Nickname</label>
								<input type="text" class="form-control" name="nickName" id="nickName" placeholder="Inserire il nickname" value="${modifica_regista_attr.nickName}">
							</div>
							<div class="form-group col-md-3">
								<label>Data di Nascita</label>
                        		<input class="form-control" id="dataDiNascita" type="date" name="dataDiNascita" value="${modifica_regista_attr.dataDiNascita}">
							</div>
							<div class="form-group col-md-3">
								<label for="sesso">Sesso</label>
							    <select class="form-control" id="sesso" name="sesso">
							    	<option value="${modifica_regista_attr.sesso.abbreviazione}"></option>
							      	<option value="MASCHIO">M</option>
							      	<option value="FEMMINA">F</option>
							    </select>
							</div>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>