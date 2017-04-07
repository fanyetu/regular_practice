package cn.fanyetu.design.structure.facade;

/**
 * 外观类
 * <p>
 * Created by zhanghaonan on 2017/4/7.
 */
public class SimpleFacade implements AbstractFacade{

	private FileReader fileReader;
	private CipherMachine cipherMachine;
	private FileWriter fileWriter;

	public SimpleFacade() {
		this.fileReader = new FileReader();
		this.cipherMachine = new CipherMachine();
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
