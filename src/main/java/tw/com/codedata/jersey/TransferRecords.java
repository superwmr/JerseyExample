package tw.com.codedata.jersey;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import test.Config;
import test.Report;
import test.TestCase;

@Path("/transfer_records")
public class TransferRecords {
	@PUT
	public Response getResponse() {

		if (Config.isRunTestCase) {
			//sendCommand.start();
			/*Report.transferCount++;
			Report.transferEndTimer = Calendar.getInstance();
			//
			long rsTime = Report.transferEndTimer.getTimeInMillis() - Report.transferStartTimer.getTimeInMillis();
			Report.totalTimer += rsTime;
			System.out.println("本次完成時間 = " + rsTime / 1000 + "秒");
			System.out.println("平均完成時間 = " + Report.totalTimer / Report.transferCount / 1000 + "秒");*/
		}
		//
		String result = "{\"code\": \"200\",\"message\": \"statusOK\", \"success\": true}";
		return Response.status(200).entity(result).build();
	}

	Thread sendCommand = new Thread(new Runnable() {
		public void run() {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//TestCase.putNextCommand();
		}
	});
}