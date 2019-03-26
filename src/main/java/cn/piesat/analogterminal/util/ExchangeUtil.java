package cn.piesat.analogterminal.util;

import java.math.BigInteger;

/**
 * @author zk
 * @date 2019/2/13 11:14
 */
public class ExchangeUtil {

    /**
     * int16转byte
     * @param i
     * @return
     */
    public static byte[] int16ToByte(int i) {
        byte[] b = new byte[2];
        b[0] = (byte) (i >>> 8);
        b[1] = (byte) i;
        return b;
    }

    /**
     * byte转int
     * @param bytes
     * @return
     */
    public static int byteToInt(byte[] bytes) {
        int val = 0;
        for (int i=0;i<bytes.length;i++) {
            val += (bytes[i] & 0xFF) << (8 - (i*8));
        }
        return val;
    }
}
