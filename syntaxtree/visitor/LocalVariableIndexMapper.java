package syntaxtree.visitor;

import java.util.HashMap;

import syntaxtree.BooleanType;
import syntaxtree.Identifier;
import syntaxtree.IdentifierType;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerType;
import syntaxtree.Type;

public class LocalVariableIndexMapper {
    private int nextId;
    private HashMap<String, Integer> indexMap;
    private TypeMapping typeMapping;

    public LocalVariableIndexMapper(TypeMapping typeMapping) {
        nextId = 1;
        this.indexMap = new HashMap<String, Integer>();
        this.typeMapping = typeMapping;
    }

    public int getIndex(Identifier id) {
        Integer index = indexMap.get(id);
        if (index == null) { // add new index if it doesn't exist
            index = nextId++; // Assumes all our types have size 1 (int, bool
                              // and references to objects or arrays)
        }
        return index;
    }
}
