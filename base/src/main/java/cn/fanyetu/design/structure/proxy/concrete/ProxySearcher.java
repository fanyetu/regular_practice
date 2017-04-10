package cn.fanyetu.design.structure.proxy.concrete;

/**
 * 代理类也是Searcher的实现类
 *
 * Created by zhanghaonan on 2017/4/10.
 */
public class ProxySearcher implements Searcher {

	private RealSearcher realSearcher = new RealSearcher();//维持一个真实查询的引用
	private AccessValidator accessValidator;
	private Logger logger;

	@Override
	public String doSearch(String username, String keyword) {
		if (this.validate(username)){
			String s = this.realSearcher.doSearch(username, keyword);
			this.log(username);
			return s;
		}else{
			return null;
		}
	}

	public boolean validate(String username){
		this.accessValidator = new AccessValidator();
		return this.accessValidator.validate(username);
	}

	public void log(String username){
		this.logger = new Logger();
		this.logger.log(username);
	}
}
