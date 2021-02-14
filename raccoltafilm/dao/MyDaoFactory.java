package it.prova.raccoltafilm.dao;

public class MyDaoFactory {
	
	private static RegistaDAO registaDaoInstance = null;
	private static FilmDAO filmDaoInstance = null;

	public static RegistaDAO getRegistaDAOInstance() {
		if (registaDaoInstance == null)
			registaDaoInstance = new RegistaDAOImpl();

		return registaDaoInstance;
	}
	
	public static FilmDAO getFilmDAOInstance() {
		if (filmDaoInstance == null)
			filmDaoInstance = new FilmDAOImpl();

		return filmDaoInstance;
	}
	
}
