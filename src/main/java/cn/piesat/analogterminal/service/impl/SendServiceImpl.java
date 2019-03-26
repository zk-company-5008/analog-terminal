package cn.piesat.analogterminal.service.impl;

import cn.piesat.analogterminal.netty.client.NettyClient;
import cn.piesat.analogterminal.service.ISendService;
import cn.piesat.analogterminal.util.DataDealUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @date 2019/2/15 16:22
 */
@Service
public class SendServiceImpl implements ISendService {
    @Autowired
    private NettyClient nettyClient;

    @Value("${netty.sendNum}")
    private int sendNum;

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void sendPos(long count, long second) {
        for (int i=0;i<sendNum;i++){
            //数据段
            String content = "#PSRPOS;"+ count +","+ second +"," + (i+1) + ",,,40.0873,116.233,42.3565,,WGS84,3.01285e-05,1.99104e-05,4.85762,56,12*DF3D4DC9\r\n";
            byte[] segmentContent = content.getBytes();

            byte[] bytes = DataDealUtil.addHead(segmentContent);

            nettyClient.sendMsg(bytes);
        }
        atomicInteger.addAndGet(sendNum);
        System.out.println("已发送【"+ atomicInteger.get() +"】条");
    }

    @Override
    public void sendGPGSV() {

    }


}
