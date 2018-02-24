package cn.fanyetu.datasource.pool.simple;

import net.sf.cglib.proxy.Proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接池主体实现
 *
 * @author zhanghaonan
 * @date 2018/2/24
 */
public class ConnectionPool {

    /**
     * 连接池栈
     */
    private ConnectionStack connectionStack;

    /**
     * 连接池运行标志
     */
    private volatile boolean isRun = true;

    private final String url, username, password;

    public ConnectionPool(String url, String username, String password, int capacity, int timeout) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connectionStack = new ConnectionStack(timeout, capacity);
    }

    /**
     * 关闭数据库连接池
     */
    public void close() {
        isRun = false;
        connectionStack.close();
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (!isRun) {
            throw new SQLException("pool closed!");
        }

        // 从栈中获取一个连接
        Connection conn = connectionStack.pop();

        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
        }

        final Connection myConn = conn;

        // 使用动态代理，改变connection close时的行为，当调用Connection.close()的时候自动将连接放回连接池中
        return (Connection) Proxy.newProxyInstance(
                ConnectionPool.class.getClassLoader(),
                conn.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    if ("close".equals(method.getName()) && isRun) {
                        connectionStack.push(myConn); // //当为调用close函数并且连接池正在运行，则将连接放入stack中，否则直接调用相关函数
                    } else {
                        return method.invoke(myConn, args);
                    }
                    return null;
                });
    }
}
