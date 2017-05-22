package test;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {

	public static void main(String[] args) {
		String title = "<span>aaa&nbsp;</span>";
		System.out.println(title);
		System.out.println(clearHtml(title));
	}

	public static String clearHtml(String str) {
		String id = "id_" + UUID.generateUUID();
		String html = "<div id='" + id + "'>" + str + "</div>";
		Document doc = Jsoup.parse(html);
		Element el = doc.getElementById(id);
		return el.text();
	}

}
