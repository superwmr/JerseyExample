
package test;

public class CommandList {
	private static String[] commands = new String[] {
			//中信
			//"2 302 6217714301063493 13828023057 关健鹏 440783198806172416 aa343599 111183 1 302 6217732101295032 明廷良 order1234 job5678",
			//"2 302 6217732101295032 18114899095 明廷良 420222199312176733 aa343599 188188 1 309 6217712010243554 孙苏阳 order1234 job5678",
			//"2 302 6217712010243554 17766257631 孙苏阳 321322199510088630 aa343599 118118 1 302 6217714301063493 关健鹏 order1234 job5678"
			
			//興業
			//"2 309 622908393123451414 18923351457 邝叶春 440783199008310620 aa343599 343599 1 309 622908393132201115 潘和胜 order1234 job5678",
			//"2 309 622908393132201115 13286610821 潘和胜 440783198508250316 aa343599 343599 1 309 622908353053123417 吕亚茗 order1234 job5678",
			//"2 309 622908353053123417 13777498055 吕亚茗 342522198204025624 aa343599 343599 1 309 622908393123451414 邝叶春 order1234 job5678"
			
			//Sync balance 中信
			"3 302 6217714301063493 13828023057 关健鹏 440783198806172416 aa343599 111183 1 302 6217732101295032 明廷良 order1234 job5678",
			"3 302 6217732101295032 18114899095 明廷良 420222199312176733 aa343599 188188 1 309 6217712010243554 孙苏阳 order1234 job5678",
			"3 302 6217712010243554 17766257631 孙苏阳 321322199510088630 aa343599 118118 1 302 6217714301063493 关健鹏 order1234 job5678"
			
			//Sync balance 興業
			//"3 309 622908393123451414 18923351457 邝叶春 440783199008310620 aa343599 343599 1 309 622908393132201115 潘和胜 order1234 job5678",
			//"3 309 622908393132201115 13286610821 潘和胜 440783198508250316 aa343599 343599 1 309 622908353053123417 吕亚茗 order1234 job5678",
			//"3 309 622908353053123417 13777498055 吕亚茗 342522198204025624 aa343599 343599 1 309 622908393123451414 邝叶春 order1234 job5678"
	};

	public static String getCommands() {
		return commands[(int) (System.currentTimeMillis() % commands.length)];
	}
	
	public static String[] getTotalCommands() {
		return commands;
	}
}
