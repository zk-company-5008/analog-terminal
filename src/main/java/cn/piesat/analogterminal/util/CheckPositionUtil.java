package cn.piesat.analogterminal.util;

/**
 * @author zk
 * @date 2019/2/12 17:57
 */
public class CheckPositionUtil {

    /**
     * 生成异或校验码
     * @param bytes
     * @return
     */
    public static byte[] GeneratingXOR(byte[] bytes) {
        byte[] XOR = new byte[1];
        byte temp = 0;
        for (byte aByte : bytes) {
            temp ^= aByte;
        }
        XOR[0] = temp;
        return XOR;
    }
}
