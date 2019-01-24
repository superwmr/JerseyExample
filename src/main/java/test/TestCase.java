package test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class TestCase {

	/**
	 * 前一個己執行的command
	 */
	private static String preCommand = "";

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
	public static void putNextCommand() {
		String command = "";
		if (nonSendCommand.isEmpty())
			command = CommandList.getCommands();
		else {
			command = new String(nonSendCommand);
			nonSendCommand = "";
		}
//		strCommand = strCommand.replace("order1234", //
//				"" + System.currentTimeMillis()).replace("job5678", //
//						"" + System.currentTimeMillis());

		if (isFirst) {
			isFirst = false;
			transfer(command);
			preCommand = command;
		} else if (isSameAccount(command, preCommand)) {
			transfer(command);
			preCommand = command;
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
	private static boolean isSameAccount(String transferInfo, String preTransferInfo) {
		if (preTransferInfo.isEmpty())
			return false;

		String accountCmd = transferInfo.split(" ")[1] + transferInfo.split(" ")[2] + transferInfo.split(" ")[3];
		String preAccountCmd = preTransferInfo.split(" ")[1] + preTransferInfo.split(" ")[2]
				+ preTransferInfo.split(" ")[3];
		//
		return accountCmd.equals(preAccountCmd);
	}
}
