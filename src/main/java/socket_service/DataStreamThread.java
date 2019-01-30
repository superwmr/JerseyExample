package socket_service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.RowFilter.ComparisonType;

import test.Config;
import test.TestCase;
import transfer_protobuf.Commands;
import transfer_protobuf.Commands.Command;

public class DataStreamThread implements IKeyboardInput {

	private DataInputStream in;
	private DataOutputStream out;
	private Socket socket;
	BufferedReader buf;

	public DataStreamThread(Socket socket) {
		this.socket = socket;
	}

	Thread outputThread = new Thread(new Runnable() {
		public void run() {
			try {
				out = new DataOutputStream(socket.getOutputStream());
//				readRead.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// out
			while (true) {
				try {

					out.write(SocketCommand.queue.take());
					out.flush();
					if (outputThread.isInterrupted())
						break;

				} catch (Exception e) {
					e.printStackTrace();
					outputThread.interrupt();
					break;
				}
			}
			inputThread.interrupt();
			System.out.println("DataStreamThread finish");
		}
	});

	// input
	Thread inputThread = new Thread(new Runnable() {

		public void run() {
			try {
				in = new DataInputStream(socket.getInputStream());
				
				while (!inputThread.isInterrupted()) {
//					System.out.println("input");

					byte[] lenBytes = new byte[4];
					in.readFully(lenBytes);
					int len = ByteBuffer.wrap(lenBytes).getInt();
					System.out.println("len = " + len);
					//
					byte[] dataBytes = new byte[len];
					in.readFully(dataBytes);
					Command cmd = Commands.Command.parseFrom(dataBytes);
					System.out.println("cmd = " + cmd.toString());
					
					if(Config.isRunTestCase && cmd.getType() == Command.Type.REGISTER)
					{
						//TestCase.putNextCommand();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	public void onInput(byte[] input) {
		try {
			SocketCommand.queue.put(input);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		outputThread.start();
		inputThread.start();
	}

	public synchronized void interrupt() {
		try {
			outputThread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		try {
			inputThread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}