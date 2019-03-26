package cn.piesat.analogterminal.service;

/**
 * @author zk
 * @date 2019/2/15 16:20
 */
public interface ISendService {
    /**
     * 发送pos
     * @param count
     * @param second
     */
    void sendPos(long count,long second);

    void sendGPGSV();
}
