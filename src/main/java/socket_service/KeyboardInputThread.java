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

				int index = 0;
				String cmd = split(msg, index++);
//				if (cmd.equals("4")) {
//					OTPs.sms = split(msg, index++);
//					continue;
//				}
				//
				String bankCode = split(msg, index++);
				String account = split(msg, index++);
				String phone = split(msg, index++);
				String name = split(msg, index++);
				String idCard = split(msg, index++);
				String password = split(msg, index++);
				String tranPwd = split(msg, index++);
				String amount = split(msg, index++);
				//
				String targetBankcode = split(msg, index++);
				String targetAccount = split(msg, index++);
				String targetName = split(msg, index++);
				String orderID = split(msg, index++);
				String jobID = split(msg, index++);
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
					bank.setIdCard(idCard);
					//
					transferData.setAmount(amount);
					transferData.setPayeeAccount(targetAccount);
					transferData.setPayeeName(targetName);
					transferData.setOrderId(orderID);
					transferData.setJobId(jobID);
					transferData.setPayeeBankCode(targetBankcode);
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
				
				System.out.println("cmdInstance = "+cmdBuild.toString());

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