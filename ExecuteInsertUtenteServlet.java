package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.model.StatoUtente;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteInsertUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// estraggo input
				String usernameParam = request.getParameter("username");
				String passwordParam = request.getParameter("password");
				String nomeParam = request.getParameter("nome");
				String cognomeParam = request.getParameter("cognome");
				String dataCreazioneParam = request.getParameter("dataDiCreazione");

				// questa variabile mi serve in quanto sfrutto in un colpo la validazione
				// della data ed il suo parsing che non posso fare senza un try catch
				// a questo punto lo incapsulo in un metodo apposito
				Date dataCreazioneParsed = UtilityForm.parseDateArrivoFromString(dataCreazioneParam);

				// valido input tramite apposito metodo e se la validazione fallisce torno in
				// pagina
				if (!UtilityForm.validateRegistaFormInput(usernameParam, passwordParam, nomeParam, cognomeParam, dataCreazioneParam)
						|| dataCreazioneParam == null) {
					request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
					request.getRequestDispatcher("/users/insert.jsp").forward(request, response);
					return;
				}

				// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
				Utente utenteInstance = new Utente(usernameParam, passwordParam, nomeParam, cognomeParam, dataCreazioneParsed);
				utenteInstance.setStato(StatoUtente.CREATO);
				// occupiamoci delle operazioni di business
				try {
					MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
					request.getRequestDispatcher("/users/insert.jsp").forward(request, response);
					return;
				}

				// andiamo ai risultati
				//uso il sendRedirecto con parametro per evitare il problema del double save on refresh
				response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");

	}

}
