package br.edu.unoesc.tcc.queryProcessor;

public abstract class QueryUtils {

	private static final String[] reservedWords = { "ABS", "ALL", "AND", "ANY",
			"AS", "ASC", "AVG", "BETWEEN", "BIT_LENGTH", "BOTH", "BY", "CASE",
			"CHAR_LENGTH", "CHARACTER_LENGTH", "CLASS", "COALESCE", "CONCAT",
			"COUNT", "CURRENT_DATE", "CURRENT_TIME", "CURRENT_TIMESTAMP",
			"DELETE", "DESC", "DISTINCT", "ELSE", "EMPTY", "END", "ENTRY",
			"ESCAPE", "EXISTS", "FALSE", "FETCH,FROM", "GROUP", "HAVING", "IN",
			"INDEX", "INNER", "IS", "JOIN", "KEY", "LEADING", "LEFT", "LENGTH",
			"LIKE", "LOCATE", "LOWER", "MAX", "MEMBER", "MIN", "MOD", "NEW",
			"NOT", "NULL", "NULLIF", "OBJECT", "OF", "OR", "ORDER", "OUTER",
			"POSITION", "SELECT", "SET", "SIZE", "SOME", "SQRT", "SUBSTRING",
			"SUM", "THEN", "TRAILING", "TRIM", "TRUE", "TYPE", "UNKNOWN",
			"UPDATE", "UPPER", "VALUE", "WHEN", "WHERE" };

	/**
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isReservedWord(String word) {
		for (int i = 0; i < reservedWords.length; i++) {
			if (word.trim().equalsIgnoreCase(reservedWords[i].trim())) {
				return true;
			}
		}
		return false;
	}

}
