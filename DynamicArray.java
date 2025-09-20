import java.lang.reflect.Array;
import java.lang.reflect.Type;

import static java.lang.System.out;

//In Java, every class has a corresponding Class<T> object
// that contains metadata about that class (like its name, methods, fields, etc.).
public class DynamicArray {

    Integer len;
    Object[] staticArray;
    Integer index = -1;
    Integer arrayCapacity;

    public DynamicArray(int len, Class<?> type) {
        this.len = 0;
        out.println(type);
        //Even we cast At runtime it remembers type
        this.staticArray = (Object[]) Array.newInstance(type, len);
        this.arrayCapacity = len;
    }

    public void checkingElementType(Object o) {
        if (!staticArray.getClass().getComponentType().isAssignableFrom(o.getClass())) {
            throw new RuntimeException(String.format("Type mismatch: expecting %s got %s", staticArray.getClass().getComponentType(), o.getClass()));
        }
    }

    void movingAndGrowArray(Object[] thisArray) {
        Integer newCapacity = this.arrayCapacity * 2;
        Object[] newArray = (Object[]) Array.newInstance(
                staticArray.getClass().getComponentType(),
                newCapacity
        );

        for (int i = 0; i < len; ++i) {
            newArray[i] = staticArray[i];
        }

        staticArray = newArray;
        arrayCapacity = newCapacity;
    }

    public void adding(Object object) {
        checkingElementType(object);
        if (arrayCapacity - 1 < len)
//            throw new RuntimeException("You exceed List length");
            movingAndGrowArray(this.staticArray);

        staticArray[++this.index] = object;
        ++len;
    }

    public Integer len() {
        return this.len;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
            boolean first = true;
        for (int i = 0; i < staticArray.length; i++) {
            Object o = staticArray[i];
            if (o == null) continue;
            if (!first) sb.append(", ");
            sb.append(o);
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
