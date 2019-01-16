package tw.com.codedata.jersey;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/otps")
public class OTPs {

//	public static String sms = "";

	@GET
	public Response getResponse(@QueryParam("p") String p, @QueryParam("b") String b, @QueryParam("t") String t) {

//    	http://35.194.242.29/api/v1.0/otp?p=%2B8618114899095&b=中信银行
//		System.out.println("p = " + p + ", b = " + b);

		String sms = callAPIs(p, b, t);
		System.out.println("sms = " + sms);
		//
//		String trueSms = new String(sms);
//		sms = "";
		return Response.status(200)
//				.entity("{\"code\": \"200\",\"message\": \"xxx\", \"success\": true, \"result\": \"" + trueSms + "\" }")
				.entity(sms).build();
	}

	private synchronized String callAPIs(String p, String b, String t) {
		String strResult = "";
		try {
			URL url = new URL("http://35.194.242.29/api/v1.0/otp?p=" + p.replace("+", "%2B") + "&b=" + b + "&t=" + t);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = "";
			while ((output = br.readLine()) != null) {
//				System.out.println("output = " + output);
				strResult += output;
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
	}

}