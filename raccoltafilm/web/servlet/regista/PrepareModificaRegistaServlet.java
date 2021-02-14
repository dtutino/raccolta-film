package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityParse;

@WebServlet("/PrepareModificaRegistaServlet")
public class PrepareModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRegistaParam = request.getParameter("idRegista");
		Long idRegista = UtilityParse.parseIdArrivoFromString(idRegistaParam);

		try {
			Regista registaDaInviare = MyServiceFactory.getRegistaServiceInstance()
					.caricaSingoloElemento(idRegista);
			request.setAttribute("modifica_regista_attr", registaDaInviare);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
	}

}
