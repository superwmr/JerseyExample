package socket_service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import transfer_protobuf.Commands;
import transfer_protobuf.Commands.Bank;
import transfer_protobuf.Commands.Command;
import transfer_protobuf.Commands.Command.Response;
import transfer_protobuf.Commands.Command.Type;
import transfer_protobuf.Commands.SyncBalanceData;
import transfer_protobuf.Commands.TransferData;
import tw.com.codedata.jersey.OTPs;
import tw.com.codedata.jersey.Utils;

public class KeyboardInputThread extends Thread {

	private IKeyboardInput inputData;

	public void setKeyboardInput(IKeyboardInput inputData) {
		this.inputData = inputData;
	}

	String split(String msg, int index) {
		try {
			return msg.split(" ")[index];
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void run() {
		super.run();

		while (true) {
			try {
				Utils.printUserMenu();
				BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

				String msg = buf.readLine();
				System.out.println("ouput = " + msg);
				//
				if (msg == null || msg.isEmpty())
					continue;

				String cmd = split(msg, 0);
				if (cmd.equals("4")) {
					OTPs.sms = split(msg, 1);
					continue;
				}
				//
				String bankCode = split(msg, 1);
				String account = split(msg, 2);
				String phone = split(msg, 3);
				String name = split(msg, 4);
				String password = split(msg, 5);
				String tranPwd = split(msg, 6);
				String amount = split(msg, 7);
				//
				String targetBankcode = split(msg, 8);
				String targetAccount = split(msg, 9);
				String targetName = split(msg, 10);
				String orderID = split(msg, 11);
				String jobID = split(msg, 12);
				//
				Commands.Command.Builder cmdInstance = Commands.Command.newBuilder();
				Commands.Device.Builder device = Commands.Device.newBuilder();
				device.setId("" + System.currentTimeMillis());
				cmdInstance.setDevice(device);
				cmdInstance.setResponse(Response.OK);

				if (cmd.equals("" + Type.REGISTER_VALUE)) {// 1
					System.out.println("REGISTER_VALUE");
					cmdInstance.setType(Type.REGISTER);
				} else if (cmd.equals("" + Type.TRANSFER_VALUE)) {// 2
					System.out.println("TRANSFER_VALUE");
					cmdInstance.setType(Type.TRANSFER);
					//
					TransferData.Builder transferData = TransferData.newBuilder();
					Bank.Builder bank = Bank.newBuilder();
					bank.setCode(bankCode);
					bank.setName(name);
					bank.setPhone(phone);
					bank.setAccount(account);
					bank.setPassword(password);
					bank.setTransPwd(tranPwd);
					//
					Bank.Builder targetBank = Bank.newBuilder();
					targetBank.setCode(targetBankcode);
					transferData.setAmount(amount);
					transferData.setBank(targetBank);
					transferData.setPayeeAccount(targetAccount);
					transferData.setPayeeName(targetName);
					transferData.setOrderId(orderID);
					transferData.setJobId(jobID);
					//
					transferData.setBank(bank);
					cmdInstance.setTransferData(transferData);
				} else if (cmd.equals("" + Type.SYNC_BALANCE_VALUE)) {// 3
					System.out.println("SYNC_BALANCE_VALUE");
					cmdInstance.setType(Type.SYNC_BALANCE);
					//
					SyncBalanceData.Builder syncBalanceData = SyncBalanceData.newBuilder();
					Bank.Builder bank = Bank.newBuilder();
					bank.setCode(bankCode);
					bank.setAccount(account);
					bank.setPassword(password);
					bank.setTransPwd(tranPwd);
					//
					syncBalanceData.setBank(bank);
					cmdInstance.setSyncBalanceData(syncBalanceData);
				} else {
					continue;
				}

				Command cmdBuild = cmdInstance.build();
				byte[] cmdBuildArray = cmdBuild.toByteArray();
				int cmdLen = cmdBuildArray.length;
				System.out.println("cmdLen = " + cmdLen);
				//
				ByteBuffer len = ByteBuffer.allocate(4);
				len.putInt(cmdLen);

				inputData.onInput(len.array());
				inputData.onInput(cmdBuildArray);

				if (isInterrupted())
					break;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private int getLen(DataInputStream in) throws Exception {
		ByteBuffer bytes = ByteBuffer.allocate(4);
		in.readFully(bytes.array());
		return bytes.getInt();
	}
}