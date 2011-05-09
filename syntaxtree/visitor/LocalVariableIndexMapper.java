package syntaxtree.visitor;

import java.util.HashMap;

import syntaxtree.Identifier;

public class LocalVariableIndexMapper {
    private int nextId;
    private HashMap<String, Integer> indexMap;

    public LocalVariableIndexMapper() {
        nextId = 1;
        this.indexMap = new HashMap<String, Integer>();
    }

    public int getIndex(Identifier id) {
        Integer index = indexMap.get(id.name);
        if (index == null) { // add new index if it doesn't exist
            index = nextId++; // Assumes all our types have size 1 (int, bool
                              // and references to objects or arrays)
            indexMap.put(id.name, index);
        }
        return index;
    }
}
