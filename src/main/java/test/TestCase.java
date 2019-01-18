package test;

import java.util.concurrent.LinkedBlockingQueue;

public class TestCase {

	/**
	 * 前一個執行的command
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

		if (isSameAccount(command, preCommand)) {
			trandfer(command);
		} else {
			logout();
			nonSendCommand = command;
		}

		preCommand = command;
	}

	/**
	 * 轉帳
	 */
	private static void trandfer(String strCommand) {
		queue.clear();
		queue.add(strCommand);
	}

	private static boolean isSameAccount(String transferInfo, String preTransferInfo) {
		if (preCommand.isEmpty())
			return false;

		String accountCmd = transferInfo.split(" ")[1] + transferInfo.split(" ")[2] + transferInfo.split(" ")[3];
		String preAccountCmd = preTransferInfo.split(" ")[1] + preTransferInfo.split(" ")[2] + preTransferInfo.split(" ")[3];
		//
		return accountCmd.equals(preAccountCmd);
	}
}
