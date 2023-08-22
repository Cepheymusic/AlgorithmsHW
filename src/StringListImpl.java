import java.lang.reflect.Array;
import java.util.Arrays;

public class StringListImpl implements StringList{

    private final String [] items;
    private int size;

    public StringListImpl() {
        this.items = new String[10];
    }
    public StringListImpl(int initSize) {
        this.items = new String[initSize];
    }


    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        items[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        validateIndex(index);
        if(index == size){
            items[size++] = item;
            return item;
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        items[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if(index == -1){
            throw new NotFoundException();

        }
        if(index != size) {
            System.arraycopy(items, index + 1, items, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = items[index];
        if(index != size) {
            System.arraycopy(items, index + 1, items, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(item)) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(items, size);
    }
    public void validateItem(String item){
        if(item == null){
            throw new NullItemException();
        }
    }
    public void validateSize(){
        if(size == items.length){
            throw new ItemsIsFullException();
        }

    }
    public void validateIndex(int index){
        if(index >= size) {
            throw new InvalidIndexException();

        }
    }
}
