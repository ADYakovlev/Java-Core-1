import java.util.ArrayList;

/*
 *@author Yakovlev Alexandr
 */
public class Box<Type extends Fruit> {
    ArrayList<Type> arrayList;

    public Box() {
        arrayList = new ArrayList<Type>();
    }

    public void union(Box<Type> targetBox) {
        for (int i = 0; i < arrayList.size() ; i++) {
            targetBox.add(arrayList.get(i));
        }
        arrayList.clear();
    }

    public void add(Type element) {
        arrayList.add(element);
    }

    public Boolean compare(Box element) {
        if (this.getWeight() == element.getWeight()) return true;
        return false;
    }

    public Float getWeight() {
        Float weightBox = arrayList.size() * arrayList.get(0).getWeight();
        return weightBox;
    }
}
