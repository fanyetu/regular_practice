package cn.fanyetu.redis;

import redis.clients.jedis.Jedis;

/**
 * ≤‚ ‘Jedis¡¥Ω”Redis
 * @author Administrator
 *
 */
public class JediTest {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");//new Jedis("127.0.0.1","6379");
		jedis.set("jedis:001", "jedis-client");
		String string = jedis.get("jedis:001");
		System.out.println(string);
		jedis.close();
	}

}
