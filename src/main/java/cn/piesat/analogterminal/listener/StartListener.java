package cn.piesat.analogterminal.listener;

import cn.piesat.analogterminal.netty.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zk
 * @date 2019/2/11 21:58
 */
@WebListener
public class StartListener implements ServletContextListener {

    @Autowired
    private NettyClient nettyClient;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new Thread(()->{
            nettyClient.connect();
        }).start();
    }
}
