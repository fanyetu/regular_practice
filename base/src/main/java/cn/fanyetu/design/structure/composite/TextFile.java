package cn.fanyetu.design.structure.composite;

/**
 * 文本文件，叶子节点
 *
 * Created by zhanghaonan on 2017/4/6.
 */
public class TextFile  extends AbstractFile {

	private String name;

	public TextFile(String name) {
		this.name = name;
	}

	@Override
	public void add(AbstractFile file) {
		System.out.println("对不起，不支持本方法");
	}

	@Override
	public void remove(AbstractFile file) {
		System.out.println("对不起，不支持本方法");
	}

	@Override
	public AbstractFile getChild(int i) {
		System.out.println("对不起，不支持本方法");
		return null;
	}

	@Override
	public void killVirus() {
		//模拟杀毒
		System.out.println("对文本文件" + name + "进行杀毒");
	}
}
