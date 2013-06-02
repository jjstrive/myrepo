import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Demo {

  public static void main(String[] asd) throws Exception {

		download();
	}


	public static void download() throws Exception {

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet("http://www.163.com/");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String temp = EntityUtils.toString(entity);
				System.out.println("Response content: " + temp);
				select(temp);
			}
		}
		finally {
			httpclient.getConnectionManager().shutdown();
		}
	}


	public static void select(String temp) {

		HashSet<String> has = new HashSet<String>();

		String regex = "[a-z0-9]+\\.163\\.com";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(temp);

		while (m.find()) {
			has.add(m.group());
		}

		ArrayList<String> list = new ArrayList<String>();
		list.addAll(has);
		Collections.sort(list);

		int i = 1;
		for (String asd : list) {
			System.out.println(i++ + " : " + asd);
		}
	}
}
