package test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class TestCase {

	/**
	 * 前一個己執行的command
	 */
	//private static String preCommand = "";

	/**
	 * 前一個應執行，但未執行的command
	 */
	private static String nonSendCommand = "";
	static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();

	public static String takeCommand() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 登出
	 */
	private static void logout() {
		queue.clear();
		queue.add("4");
	}

	static boolean isFirst = true;

	/**
	 * 送command
	 */
	public static void putIdelCommand(int orderId, int jobId) {
		String command = "";
		if (nonSendCommand.isEmpty()) {
			command = CommandList.getCommands();
			command = command.replace("order1234", //
					"" + orderId).replace("job5678", //
							"" + jobId);
		} else {
			command = new String(nonSendCommand);
			nonSendCommand = "";
		}
		
		transfer(command);
		return;
	}
	
	/**
	 * 送command
	 */
	public static void putNextCommand(RsSyncStatus rsSyncStatus, int orderId, int jobId) {
		String command = "";
		command = CommandList.getCommands();
		command = command.replace("order1234", //
				"" + orderId).replace("job5678", //
						"" + jobId);
		
		if (isSameAccount(command, rsSyncStatus.getBank(), rsSyncStatus.getAccount())) {
			transfer(command);
		
		} else {
			logout();
			nonSendCommand = command;
		}
	}

	/**
	 * 轉帳
	 */
	private static void transfer(String strCommand) {
		queue.clear();
		queue.add(strCommand);
		Report.transferStartTimer = Calendar.getInstance();
	}

	/**
	 * 檢查帳號是否相同
	 * 
	 * @param transferInfo
	 * @param preTransferInfo
	 * @return
	 */
	private static boolean isSameAccount(String transferInfo, String bankCode, String account) {
		if (account.equals("") || bankCode.equals(""))
			return false;

		String accountCmd = transferInfo.split(" ")[2];
		String bankCmd = transferInfo.split(" ")[1];
		//
		return accountCmd.equals(account) && bankCmd.equals(bankCode);
	}
}
