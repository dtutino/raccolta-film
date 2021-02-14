package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;
import it.prova.raccoltafilm.utility.UtilityParse;

@WebServlet("/ExecuteModificaFilmServlet")
public class ExecuteModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String idArrivoStringParam = request.getParameter("idFilm");
		String titoloInputStringParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String dataPubblicazioneInputStringParam = request.getParameter("dataPubblicazione");
		String minutiDurataInputStringParam = request.getParameter("minutiDurata");
		String registaIdStringParam = request.getParameter("regista.id");

		Long idArrivoParsed = UtilityParse.parseIdArrivoFromString(idArrivoStringParam);
		Date dataPubblicazioneParsed = UtilityForm.parseDateArrivoFromString(dataPubblicazioneInputStringParam);
		int minutiDurataParsed = UtilityParse.parseMinutiDurataFromString(minutiDurataInputStringParam);
		Long registaIdParsed = UtilityParse.parseIdArrivoFromString(registaIdStringParam);

		if (!UtilityForm.validateFilmFormInput(titoloInputStringParam, genereInputParam,
				minutiDurataInputStringParam, dataPubblicazioneInputStringParam, registaIdStringParam)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
			return;
		}

		try {
			Film filmInstance = MyServiceFactory.getFilmServiceInstance()
					.caricaSingoloElemento(idArrivoParsed);
			filmInstance.setTitolo(titoloInputStringParam);
			filmInstance.setGenere(genereInputParam);
			filmInstance.setDataPubblicazione(dataPubblicazioneParsed);
			filmInstance.setMinutiDurata(minutiDurataParsed);
			filmInstance.setRegista(MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(registaIdParsed));
			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
