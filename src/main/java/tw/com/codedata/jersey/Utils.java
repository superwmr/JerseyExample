package tw.com.codedata.jersey;

import java.io.IOException;

public class Utils {

	private static void clearConsole()
	{
	    try
	    {
	        String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (Exception exception)
	    {
	        //  Handle exception.
	    }
	}
	
	public synchronized static void printUserMenu() {
		
		clearConsole();
		
		System.out.println("============================================================");
		System.out.println("1:回傳註冊結果, 2:轉帳, 3:取得餘額");
		System.out.println("formet:CMD 銀行 帳號 電話 名字 密碼 轉帳密碼 金額 對方銀行 對方帳號 對方名字");
		System.out.println("ex:2 302 6217732101295032 18114899095 明廷良 aa343599 188188 1 101 6217234301008178893 王露");
		//
		System.out.println();
		System.out.println("4:otp");
		System.out.println("format:CMD sms");
		System.out.println("ex:4 123456");
		System.out.println("============================================================");
	}
}