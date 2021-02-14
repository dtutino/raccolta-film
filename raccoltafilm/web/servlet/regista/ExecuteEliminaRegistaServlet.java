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

@WebServlet("/ExecuteEliminaRegistaServlet")
public class ExecuteEliminaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteEliminaRegistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArrivoStringParam = request.getParameter("idRegista");

		Long idArrivoParsed = UtilityParse.parseIdArrivoFromString(idArrivoStringParam);

		try {
			Regista registaInstance = MyServiceFactory.getRegistaServiceInstance()
					.caricaSingoloElemento(idArrivoParsed);
			MyServiceFactory.getRegistaServiceInstance().rimuovi(registaInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}

}
