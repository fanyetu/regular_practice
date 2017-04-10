package cn.fanyetu.design.structure.proxy.concrete;

/**
 * 真实的查询类
 *
 * Created by zhanghaonan on 2017/4/10.
 */
public class RealSearcher implements Searcher{

	@Override
	public String doSearch(String username, String keyword) {
		System.out.println("用户"+username+"使用"+keyword+"关键字查询商务信息");
		return "返回具体内容";
	}
}
