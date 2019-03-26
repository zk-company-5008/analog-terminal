package cn.piesat.analogterminal.util;

import cn.piesat.analogterminal.enums.MsgType;

import java.io.UnsupportedEncodingException;

/**
 * @author zk
 * @date 2019/2/13 15:03
 */
public class DataDealUtil {
    public static byte[] addHead(byte[] segmentContent) {
        //数据总长度
        int len = 5 + 2 + 1 + 9 + segmentContent.length + 1;
        //完整数据包
        byte[] bytes = new byte[len];

        //同步头(长度5)
        byte[] head = new byte[5];
        try {
            head = "$ZDSC".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //数据长度(长度2)
        byte[] dataLen = new byte[2];
        dataLen = ExchangeUtil.int16ToByte(len - 1);

        //信息类型(长度1)
        byte[] msgType = new byte[1];
        int index = MsgType.ASCII.getIndex();
        msgType[0] = (byte) index;

        //终端编号(长度9)
        byte[] terminalId = "BJ01XH001".getBytes();

        //数据段
        byte[] segmentContents = segmentContent;

        //校验位(长度1)
        byte[] checkPosition = new byte[1];

        //数据包（除校验位）
        byte[] bytesTemp = new byte[len - 1];
        System.arraycopy(head,0,bytesTemp,0,5);
        System.arraycopy(dataLen,0,bytesTemp,5,2);
        System.arraycopy(msgType,0,bytesTemp,7,1);
        System.arraycopy(terminalId,0,bytesTemp,8,9);
        System.arraycopy(segmentContent,0,bytesTemp,17,segmentContent.length);

        //生成异或校验码
        checkPosition = CheckPositionUtil.GeneratingXOR(bytesTemp);

        //完整数据包
        System.arraycopy(bytesTemp,0,bytes,0,bytesTemp.length);
        System.arraycopy(checkPosition,0,bytes,bytesTemp.length,1);
        return bytes;
    }
}
