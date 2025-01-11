public class Utils {
     /**
     * inicio de Ax antes do final de Bx -> ax < de bx + bw <p>
     * final de Ax depois de Bx -> ax + aw > bx
     * <p>
     * inicio de Ay acima do final de By -> ay acima < de by + bh <p>
     * final de Ay abaixo de Bx -> a.posY + a.height > b.posY
     */
    public static boolean hasCollision(final GameObject a, final GameObject b) {
        return a.posX < b.posX + b.width
                && a.posX + a.width > b.posX
                && a.posY < b.posY + b.height
                && a.posY + a.height > b.posY;
    }
}
