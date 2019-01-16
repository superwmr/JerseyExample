package tw.com.codedata.jersey;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import test.TestCase;

@Path("/transfer_records")
public class TransferRecords {
	@POST
	public Response getResponse() {
		
		if(TestCase.isRunTestCase)
			TestCase.putNextCommand();
		
		String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
		return Response.status(200).entity(result).build();
	}
}