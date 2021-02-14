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

@WebServlet("/ExecuteInsertRegistaServlet")
public class ExecuteInsertRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String nickNameParam = request.getParameter("nickName");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");

		// questa variabile mi serve in quanto sfrutto in un colpo la validazione
		// della data ed il suo parsing che non posso fare senza un try catch
		// a questo punto lo incapsulo in un metodo apposito
		Date dataDiNascitaParsed = UtilityForm.parseDateArrivoFromString(dataDiNascitaParam);

		// valido input tramite apposito metodo e se la validazione fallisce torno in
		// pagina
		if (!UtilityForm.validateRegistaFormInput(nomeParam, cognomeParam, nickNameParam, dataDiNascitaParam)
				|| dataDiNascitaParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		Regista registaInstance = new Regista(nomeParam, cognomeParam, nickNameParam, dataDiNascitaParsed,
				Sesso.valueOf(sessoParam));
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getRegistaServiceInstance().inserisciNuovo(registaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		//uso il sendRedirecto con parametro per evitare il problema del double save on refresh
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");

	}

}
