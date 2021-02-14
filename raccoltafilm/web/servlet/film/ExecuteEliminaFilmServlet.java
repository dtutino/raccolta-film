package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityParse;

@WebServlet("/ExecuteEliminaFilmServlet")
public class ExecuteEliminaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteEliminaFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArrivoStringParam = request.getParameter("idFilm");

		Long idArrivoParsed = UtilityParse.parseIdArrivoFromString(idArrivoStringParam);

		try {
			Film filmInstance = MyServiceFactory.getFilmServiceInstance()
					.caricaSingoloElemento(idArrivoParsed);
			MyServiceFactory.getFilmServiceInstance().rimuovi(filmInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/delete.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
