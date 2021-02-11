package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteInsertFilmServlet")
public class ExecuteInsertFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String registaIdParam = request.getParameter("regista.id");

		// questa variabile mi serve in quanto sfrutto in un colpo la validazione
		// della data ed il suo parsing che non posso fare senza un try catch
		// a questo punto lo incapsulo in un metodo apposito
		Date dataPubblicazioneParsed = UtilityForm.parseDateArrivoFromString(dataPubblicazioneParam);

		// valido input tramite apposito metodo e se la validazione fallisce torno in
		// pagina
		if (!UtilityForm.validateFilmFormInput(titoloParam, genereParam, minutiDurataParam, dataPubblicazioneParam,
				registaIdParam) || dataPubblicazioneParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/insert.jsp").forward(request, response);
			return;
		}

		Film filmInstance = new Film(titoloParam, genereParam, dataPubblicazioneParsed,
				Integer.parseInt(minutiDurataParam), new Regista(Long.parseLong(registaIdParam)));

		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getFilmServiceInstance().inserisciNuovo(filmInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/insert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirecto con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");

	}

}
