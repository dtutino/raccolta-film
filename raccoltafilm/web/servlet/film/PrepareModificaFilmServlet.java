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

@WebServlet("/PrepareModificaFilmServlet")
public class PrepareModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PrepareModificaFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idFilmParam = request.getParameter("idFilm");
		Long idFilm = UtilityParse.parseIdArrivoFromString(idFilmParam);

		try {
			Film filmDaInviare = MyServiceFactory.getFilmServiceInstance()
					.caricaSingoloElementoEager(idFilm);
			request.setAttribute("modifica_film_attr", filmDaInviare);
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
	}

}
