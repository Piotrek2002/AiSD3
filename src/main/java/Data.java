import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Data {
    private List<Integer> list;

    public Data(){
        list=new ArrayList<>();
    }

    public void addElement(Integer e){
        list.add(e);
    }
    public void sortList(){
        Collections.sort(list);
    }
    public void removeElement(Integer e){
        list.remove(e);
    }

    public List<Integer> getList() {
        return list;
    }
}
