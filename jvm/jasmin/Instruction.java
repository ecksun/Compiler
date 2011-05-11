package jvm.jasmin;

/**
 * Statement represents a Jasmin statement.
 */
abstract public class Instruction extends Statement {

    /**
     * Returns the operand stack size change caused by this instruction.
     * 
     * @return The operand stack size change.
     */
    public abstract int getOperandStackSizeChange();

}
