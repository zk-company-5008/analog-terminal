package cn.piesat.analogterminal.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import ch.qos.logback.core.net.SyslogOutputStream;
import cn.piesat.analogterminal.enums.MsgType;
import cn.piesat.analogterminal.netty.client.NettyClient;
import cn.piesat.analogterminal.scheduled.ScheduledTask;
import cn.piesat.analogterminal.service.ISendService;
import cn.piesat.analogterminal.util.CalculationTimeUtil;
import cn.piesat.analogterminal.util.CheckPositionUtil;
import cn.piesat.analogterminal.util.DataDealUtil;
import cn.piesat.analogterminal.util.ExchangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author zk
 * @date 2019/2/12 15:28
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private ISendService iSend;

    @RequestMapping("/sendPos")
    public String sendPos() {
        ScheduledTask.getInstance().scheduleAtFixedRate(()->{
            //周计数
            long count = CalculationTimeUtil.countOfWeek();
            //周内秒
            long second = CalculationTimeUtil.secondOfWeek();

            iSend.sendPos(count,second);
        },0,1000,TimeUnit.MILLISECONDS);
        return "开始发送。。。";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] segmentContent = "670".getBytes("UTF-8");
        for (byte b : segmentContent) {
            System.out.println(b);
        }

    }
}