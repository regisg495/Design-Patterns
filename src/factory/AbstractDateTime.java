package factory;

public abstract class AbstractDateTime implements DateTime {
	protected String regex;
	// protected List<String> regexs = new ArrayList<String>();

	public final boolean matches(String valor) {
		return valor.matches(this.regex);

//		for (String regex : this.regexs) {
//			if (valor.matches(regex)) {
//				return (true);
//			}
//		}
//		return (false);

	}

}
