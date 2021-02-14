package it.prova.raccoltafilm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class UtilityForm {

	public static boolean validateRegistaFormInput(String nomeInputParam, String cognomeInputParam,
			String nickNameInputParam, String dataDiNascitaStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(nomeInputParam) || StringUtils.isBlank(cognomeInputParam)
				|| StringUtils.isBlank(nickNameInputParam) || StringUtils.isBlank(dataDiNascitaStringParam)) {
			return false;
		}
		return true;
	}

	public static boolean validateFilmFormInput(String titoloInputParam, String genereInputParam,
			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(titoloInputParam) || StringUtils.isBlank(genereInputParam)
				|| !NumberUtils.isCreatable(minutiDurataInputParam) || StringUtils.isBlank(dataPubblicazioneStringParam)
				|| !NumberUtils.isCreatable(registaIdStringParam)) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
