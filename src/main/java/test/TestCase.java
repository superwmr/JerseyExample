package test;

import java.util.concurrent.LinkedBlockingQueue;

public class TestCase {

	public static boolean isRunTestCase = true;;
	static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();
	static String[] command = new String[] {
			"2 302 6217732101295032 18114899095 明廷良 420222199312176733 aa343599 188188 1 309 622908353051261417 鲁利群 order1234 job5678",
			"2 302 6217710504155722 15851903041 王露 320482199009291804 aa343599 188188 1 309 622908353051261417 鲁利群 order1234 job5678",
			"2 309 622908353051261417 13107716836 鲁利群 330822198012163611 aa343599 343599 1 302 6217732101295032 明廷良 order1234 job5678",
//	"2 309 622908393123451414 18923351457 邝叶春 440783199008310620 aa343599 343599 1 302 6217732101295032 明廷良 order1234 job5678"
			// cat說有問題

//	static String registerSuccess = "";
	};

	static {
		new TestCase();	
	}
	
	public TestCase() {
		putNextCommand();
	}

	public static String takeCommand() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void putNextCommand() {
		queue.add(command[(int) (System.currentTimeMillis() % 3)]);
	}
}