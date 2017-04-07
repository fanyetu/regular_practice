package cn.fanyetu.design.structure.facade;

import javax.enterprise.inject.New;

/**
 * 新的外观类
 *
 * Created by zhanghaonan on 2017/4/7.
 */
public class NewSimpleFacade implements AbstractFacade {
	private FileReader fileReader;
	private NewCiphreMachine cipherMachine;
	private FileWriter fileWriter;

	public NewSimpleFacade() {
		this.fileReader = new FileReader();
		this.cipherMachine = new NewCiphreMachine();
		this.fileWriter = new FileWriter();
	}

	/**
	 * 文件加密
	 * @param fileNameSrc
	 * @param fileNameDes
	 */
	public void fileEncrypt(String fileNameSrc, String fileNameDes) {
		fileWriter.write(cipherMachine.encrypt(fileReader.read(fileNameSrc)), fileNameDes);
	}
}
