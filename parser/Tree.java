// Tree.java

abstract class Node
{
    abstract public double eval();
    abstract public String toString();
}

class Leaf extends Node
{
    public Leaf(double d)
    {
        val = d;
    }

    public Leaf(Double d)
    {
        val = d.doubleValue();
    }

    public double eval()
    {
        return val;
    }

    public String toString()
    {
        return "" + val;
    }

    private double val;
}

class Op extends Node
{
    public Op(int op, Node l, Node r)
    {
        operation = op;
        left = l;
        right = r;
    }

    public Op(int op, Node l)
    {
        operation = op;
        left = l;
        right = null;
    }

    public double eval()
    {
        switch(operation)
        {
            case sym.PLUS:
                return left.eval() + right.eval();
            case sym.MINUS:
                return left.eval() - right.eval();
            case sym.MULT:
                return left.eval() * right.eval();
            default:
                return 1.0;
        }
    }

    public String toString()
    {
        switch(operation)
        {
            case sym.PLUS:
                return left + " " + right + " PLUS";
            case sym.MINUS:
                return left + " " + right + " MINUS";
            case sym.MULT:
                return left + " " + right + " MULT";
            default:
                return "<error>";
        }
    }

    private int operation;
    private Node left, right;
}

class Tree
{
    public static void main(String args[]) throws Exception
    {
        new parser(new Lexer(System.in)).parse();
    }
}
