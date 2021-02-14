package it.prova.raccoltafilm.utility;

import org.apache.commons.lang3.StringUtils;

public class UtilityParse {
	
	public static Long parseIdArrivoFromString(String idArrivoStringParam) {
		if (StringUtils.isBlank(idArrivoStringParam))
			return null;

		try {
			return Long.parseLong(idArrivoStringParam);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int parseMinutiDurataFromString(String minutiDurataStringParam) {
		if (StringUtils.isBlank(minutiDurataStringParam))
			return 0;

		try {
			return Integer.parseInt(minutiDurataStringParam);
		} catch (Exception e) {
			return 0;
		}
	}

}
