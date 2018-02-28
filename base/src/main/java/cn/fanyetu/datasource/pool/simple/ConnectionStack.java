package cn.fanyetu.datasource.pool.simple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 存放数据库连接的栈
 *
 * @author zhanghaonan
 * @date 2018/2/24
 */
public class ConnectionStack {

    private final int timeout; // 超时时间，毫秒
    private final int capacity; // 连接池的最大容量

    /**
     * 使用链表实现栈
     */
    private LinkedList<ConnectionWithTime> stack;

    public ConnectionStack(int timeout, int capacity) {
        this.timeout = timeout;
        this.capacity = capacity;
    }

    /**
     * 向栈中添加连接
     *
     * @param conn
     */
    public synchronized void push(Connection conn) {
        // 首先，循环遍历整个栈，将超时的连接销毁
        clearTimeoutConnection();

        if (stack.size() < capacity) {
            stack.addFirst(new ConnectionWithTime(conn));
        } else {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除栈中超时的连接
     */
    private void clearTimeoutConnection() {
        //接下来从栈底部开始遍历，把所有超时的连接关闭并且丢弃，直到未超时的连接为止
        Iterator<ConnectionWithTime> iterator = stack.descendingIterator(); // 获取从后向前的遍历器
        while (iterator.hasNext()) {
            ConnectionWithTime conn = iterator.next();
            if (conn.isTimeout()) {
                try {
                    conn.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                iterator.remove(); // 关闭之后将其从集合中移除
            } else {
                break;
            }
        }
    }

    /**
     * 从栈中获取连接(获取并且从栈中移除)
     *
     * @return
     */
    public synchronized Connection pop() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.removeFirst().getConnection();
    }

    /**
     * 关闭所有的连接
     */
    public synchronized void close() {
        Iterator<ConnectionWithTime> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            ConnectionWithTime conn = iterator.next();
            try {
                conn.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            iterator.remove(); // 遍历后将连接从集合中移除
        }
    }

    /**
     * 带有超时标志的Connection
     *
     * @author zhanghaonan
     */
    private class ConnectionWithTime {

        private final long time = System.currentTimeMillis();
        private Connection connection;

        public ConnectionWithTime(Connection connection) {
            this.connection = connection;
        }

        /**
         * 判断connection是否已经超时
         *
         * @return
         */
        public boolean isTimeout() {
            return System.currentTimeMillis() - time > timeout;
        }

        public long getTime() {
            return time;
        }

        public Connection getConnection() {
            return connection;
        }
    }


}
