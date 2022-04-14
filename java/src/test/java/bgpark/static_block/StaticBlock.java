package bgpark.static_block;

import java.util.Vector;

public class StaticBlock {

    private static Vector v = new Vector();
    private static StringBuilder sb;

    static {
        sb = new StringBuilder("1234567890");
        for(int i = 0; i < 25; i++) {
            sb.append(sb);
        }
    }

    public StaticBlock() {
        v.add(sb.toString());
    }
}
