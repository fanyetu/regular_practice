package cn.fanyetu.http.core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 一个简单的http服务器实现
 * <p>
 * Created by zhanghaonan on 2017/4/10.
 */
public class SimpleHttpServer implements Runnable {

	//服务器sorcket
	ServerSocket serverSocket;

	//服务器端口
	public static int PORT = 8080;

	/**
	 * 开始服务器socket线程
	 */
	public SimpleHttpServer() {
		try {
			this.serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("启动服务器失败:" + e.getLocalizedMessage());
		}
		if (this.serverSocket == null) {
			System.exit(1);//退出
		}
		new Thread(this).start();
		System.out.println("服务器启动成功，端口：" + PORT);
	}

	/**
	 * 运行服务器主线程，监听客户端请求并响应
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Socket client = null;//客户socket
				client = this.serverSocket.accept();
				if (client != null) {
					System.out.println("连接到服务器的用户:" + client);
					try {
						//第一阶段 打开输入流
						BufferedReader in = new BufferedReader(new InputStreamReader
								(client.getInputStream()));

						System.out.println("客户端发送的请求信息: ***************");

						//读取第一行，请求地址
						String line = in.readLine();
						System.out.println(line);

						//获取请求的资源的地址
						String resource = line.substring(line.indexOf('/'),
								line.lastIndexOf('/') - 5);

						resource = URLDecoder.decode(resource, "UTF-8");
						System.out.println(resource);

						//获取请求的方法，GET或者POST
						String method = new StringTokenizer(line).nextElement().toString();

						//读取浏览器发送过来的请求头部信息
						while ((line = in.readLine()) != null) {
							System.out.println(line);

							if ("".equals(line)) {
								break;
							}
						}

						//如果是POST请求，打印出表单提交的内容
						if ("POST".equalsIgnoreCase(method)) {
							System.out.println(in.readLine());
						}

						System.out.println("请求信息结束 ***************");
						System.out.println("用户请求的资源是:" + resource);
						System.out.println("请求的类型是: " + method);

						// GIF 图片就读取一个真实的图片数据并返回给客户端
						if (resource.endsWith(".gif")) {
							fileServer("images/test.gif", client);
							closeSocket(client);
							continue;
						}

						//如果是jpg文件，就直接报404
						if (resource.endsWith(".jpg")) {
							PrintWriter out = new PrintWriter(client.getOutputStream(),
									true);
							out.println("HTTP/1.0 404 Not found");//返回应答消息，并结束应答
							out.println();//根据http协议，空行将结束消息头

							out.close();
							closeSocket(client);
							continue;
						} else {
							//用printWriter向客户端输出一段html代码
							PrintWriter out = new PrintWriter(
									client.getOutputStream(), true);
							out.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
							out.println("Content-Type:text/html;charset=UTF-8");
							out.println();// 根据 HTTP 协议, 空行将结束头信息

							out.println("<h1> Hello Http Server</h1>");
							out.println("你好, 这是一个 Java HTTP 服务器 demo 应用.<br>");
							out.println("您请求的路径是: " + resource + "<br>");
							out.println("这是一个支持虚拟路径的图片:<img src='abc.gif'><br>"
									+ "<a href='abc.gif'>点击打开abc.gif, 是个服务器虚拟路径的图片文件.</a>");
							out.println("<br>这是个会反馈 404 错误的的图片:<img src='test" +
									".jpg'><br><a href='test.jpg'>点击打开test.jpg</a><br>");
							out.println("<form method=post action='/'>POST 表单 <input name=username" +
									" value='用户'> <input name=submit type=submit " +
									"value=submit></form>");
							out.close();

							closeSocket(client);
						}

					} catch (Exception e) {
						System.out.println("服务器错误:" + e.getLocalizedMessage());
					}
				}
			} catch (Exception e) {
				System.out.println("服务器错误:" + e.getLocalizedMessage());
			}
		}
	}

	private void closeSocket(Socket client) {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(client + "离开了HTTP服务器");
	}

	/**
	 * 读取一个文件内容并返回给客户端
	 *
	 * @param fileName 文件名
	 * @param client   客户端socket
	 */
	private void fileServer(String fileName, Socket client) {
		try {
			PrintStream out = new PrintStream(client.getOutputStream(), true);
//			File fileToSend = new File(fileName);
			File fileToSend = new File("F:/test.png");
			if (fileToSend.exists() && !fileToSend.isDirectory()) {
				out.println("HTTP/1.0 200 OK");//返回应答消息，并结束应答
				out.println("Content-Type:application/binary");
				out.println("Content-Length:" + fileToSend.length());//返回内容的字节数
				out.println();//根据http协议，空行将结束头信息

				FileInputStream fis = new FileInputStream(fileToSend);
				byte[] bytes = new byte[fis.available()];
				fis.read(bytes);
				out.write(bytes);

				out.close();
				fis.close();
			}
		} catch (Exception e) {
			System.out.println("传送文件时出错:" + e.getLocalizedMessage());
		}
	}

	/**
	 * 打印用途说明.
	 */
	private static void usage() {
		System.out.println("Usage: java HTTPServer <port> Default port is " + PORT + ".");
	}

	/**
	 * 启动http服务器
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				usage();
			} else if (args.length == 1) {
				PORT = Integer.parseInt(args[0]);
			}
		} catch (Exception ex) {
			System.err
					.println("Invalid port arguments. It must be a integer that greater than 0");
		}

		new SimpleHttpServer();//启动
	}
}
