package tw.com.codedata.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/otps")
public class OTPs {

	public static String sms = "";

	@GET
	public Response getName() {
		String trueSms = new String(sms);
		sms = "";
		return Response.status(200)
				.entity("{\"code\": \"200\",\"message\": \"xxx\", \"success\": true, \"result\": \"" + trueSms + "\" }")
				.build();
	}
}