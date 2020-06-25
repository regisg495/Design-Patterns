package builder;

public class Main {

	public static void main(String[] args) {

		URL url = URLBuilder.newInstance()
				.withProtocol("http")
				.withSite("www.mystore.com")
				.withPath("version2/promotions")
				.withQueryParam("promoid", "26354a")
				.build();
		
		System.out.println(url.toString());
		
	}

}
