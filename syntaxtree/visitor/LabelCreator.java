package syntaxtree.visitor;

public class LabelCreator {
    private static int labelId = 0;
    public static String getlabel() {
        return "L" + labelId++;
    }
    public static void resetLabel() {
        labelId = 0;
    }
}
