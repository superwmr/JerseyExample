package tw.com.codedata.jersey;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import test.Config;
import test.TestCase;

@Path("/transfer_records")
public class TransferRecords {
	@POST
	public Response getResponse() {

		if (Config.isRunTestCase)
			sendCommand.start();

		String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
		return Response.status(200).entity(result).build();
	}

	Thread sendCommand = new Thread(new Runnable() {
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			TestCase.putNextCommand();
		}
	});
}