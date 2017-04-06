package cn.fanyetu.design.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹，容器节点
 *
 * Created by zhanghaonan on 2017/4/6.
 */
public class Folder extends AbstractFile {

	private List<AbstractFile> list = new ArrayList<AbstractFile>();
	private String name;

	public Folder(String name) {
		this.name = name;
	}

	@Override
	public void add(AbstractFile file) {
		list.add(file);
	}

	@Override
	public void remove(AbstractFile file) {
		list.remove(file);
	}

	@Override
	public AbstractFile getChild(int i) {
		return list.get(i);
	}

	@Override
	public void killVirus() {

		System.out.println("对文件夹"+name+"进行杀毒");

		//调用文件夹子节点的杀毒方法
		for (AbstractFile file : list){
			file.killVirus();
		}

	}
}
