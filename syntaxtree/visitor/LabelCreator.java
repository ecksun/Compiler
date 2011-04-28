package syntaxtree.visitor;

public class LabelCreator {
    private static int labelId = 0;
    public static String getLabel() {
        return "L" + labelId++;
    }
    public static void resetLabel() {
        labelId = 0;
    }
}
