package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Load word constants onto stack.
 */
public class Ldc extends Instruction {

    /**
     * Holds the string value if constant is a quoted string. 
     */
    private String stringConstant = null;
    
    /**
     * Holds the int value if constant is an integer.
     */
    private Integer intConstant = null;
    
    /**
     * Holds the float value if constant is a float.
     */
    private Float floatConstant = null;
    
    public Ldc(String constant) {
        stringConstant = new String(constant);
    }
    
    public Ldc(int constant) {
        intConstant = new Integer(constant);
    }
    
    public Ldc(float constant) {
        floatConstant = new Float(constant);
    }
    
    @Override
    public int getOperandStackSizeChange() {
        // => value
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("ldc ");
        
        if (stringConstant != null) {
            str.append(stringConstant);
        } else if (intConstant != null) {
            str.append(intConstant);
        } else if (floatConstant != null) {
            str.append(floatConstant);
        } else {
            System.out.println("Constant not initialized.");
            return null;
        }
        
        str.append(comment());
        
        return str.toString();
    }

}
