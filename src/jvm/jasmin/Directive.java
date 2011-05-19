package jvm.jasmin;

/**
 * Directive is a common super class for all Jasmin directives.
 */
abstract public class Directive extends Statement {

    @Override
    public int getOperandStackSizeChange() {
        return 0;
    }

}
