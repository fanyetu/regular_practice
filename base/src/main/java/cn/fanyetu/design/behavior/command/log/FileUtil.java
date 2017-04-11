package cn.fanyetu.design.behavior.command.log;

import java.io.*;
import java.util.List;

/**
 * 日志文件工具类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class FileUtil {

	/**
	 * 将命令集合写入到日志文件中
	 * @param commands
	 */
	public static void writeCommandsToFile(List<Command> commands){
		try {
			FileOutputStream fos = new FileOutputStream("config.log");
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));

			//将对象写入文件
			oos.writeObject(commands);

			oos.close();
		} catch (IOException e) {
			System.out.println("命令集合保存失败");
			e.printStackTrace();
		}
	}

	/**
	 * 从日志文件中读取命令集合
	 * @return
	 */
	public static List<Command> readCommandFromFile(){
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream("config.log")));

			List<Command> commands = (List<Command>) ois.readObject();

			ois.close();

			return commands;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("读取命令集合失败");
			e.printStackTrace();
			return null;
		}
	}
}
