package cn.fanyetu.design.structure.composite;

/**
 * 抽象文件类，是容器节点和叶子节点共同的父类
 *
 * Created by zhanghaonan on 2017/4/6.
 */
public abstract class AbstractFile {

	public abstract void add(AbstractFile file);
	public abstract void remove(AbstractFile file);
	public abstract AbstractFile getChild(int i);
	public abstract void killVirus();

}
