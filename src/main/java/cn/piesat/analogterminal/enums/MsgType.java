package cn.piesat.analogterminal.enums;

/**
 * @author zk
 * @date 2019/2/12 15:40
 */
public enum MsgType {
    ASCII("Ascii码",1),
    BINARY("二进制流",2);

    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    MsgType(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
