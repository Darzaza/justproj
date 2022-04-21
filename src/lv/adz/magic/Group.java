package lv.adz.magic;

import javax.naming.spi.ObjectFactory;
import java.util.List;
import java.util.Objects;

public class Group {

    private List<ManyEnt> group;

    public Group(List<ManyEnt> group){
        this.group = group;
    }
    public List<ManyEnt> getGroup(){
        return group;
    }
    public int size(){
        return group.size();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Group group1 = (Group) obj;
        return group.equals(group1.group);
    }
    @Override
    public int hashCode(){
        return Objects.hash(group);
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(ManyEnt manyEnt : group){
            str.append(manyEnt + "\n");
        }
        return str.toString();
    }

    }

