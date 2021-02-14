package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;
import it.prova.raccoltafilm.utility.UtilityParse;

@WebServlet("/ExecuteModificaRegistaServlet")
public class ExecuteModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaRegistaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String idArrivoStringParam = request.getParameter("idRegista");
		String nomeInputStringParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String nickNameInputStringParam = request.getParameter("nickName");
		String dataDiNascitaInputStringParam = request.getParameter("dataDiNascita");
		String sessoInputStringParam = request.getParameter("sesso");

		Long idArrivoParsed = UtilityParse.parseIdArrivoFromString(idArrivoStringParam);
		Date dataDiNascitaParsed = UtilityForm.parseDateArrivoFromString(dataDiNascitaInputStringParam);

		if (!UtilityForm.validateRegistaFormInput(nomeInputStringParam, cognomeInputParam,
				nickNameInputStringParam, dataDiNascitaInputStringParam)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
			return;
		}

		
		try {
			Regista registaInstance = MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(idArrivoParsed);
			registaInstance.setNome(nomeInputStringParam);
			registaInstance.setCognome(cognomeInputParam);
			registaInstance.setNickName(nickNameInputStringParam);
			registaInstance.setDataDiNascita(dataDiNascitaParsed);
			registaInstance.setSesso(Sesso.valueOf(sessoInputStringParam));
			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}

}
