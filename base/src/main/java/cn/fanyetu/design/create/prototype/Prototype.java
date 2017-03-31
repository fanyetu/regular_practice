package cn.fanyetu.design.create.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public abstract class Prototype implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;

	private String name = null;
	
	private List<String> nickNames = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(List<String> nickNames) {
		this.nickNames = nickNames;
	}
	
	/**
	 * ǳ����
	 */
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	/**
	 * ���
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object deepClone() throws IOException, ClassNotFoundException{
		/* д�뵱ǰ����Ķ������� */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		
		os.writeObject(this);
		
		/* �������������������¶��� */
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		return ois.readObject();
	}

	@Override
	public String toString() {
		return "Prototype [name=" + name + ", nickNames=" + nickNames + "]";
	}
	
	
	
	
}
