package lv.adz.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ManyEnt {

    private List<Entity> entities = new ArrayList<>();

    public ManyEnt(String ... strings){
        for(Columns columns : Columns.values()){
            entities.add(new Entity(columns, strings[columns.getValue()]));
        }
    }
    public Entity getEntity(Columns columns){
        return entities.get(columns.getValue());
    }
    public List<Entity> getStatusEntities(){
        return entities.stream().filter(Entity::checkStatus).collect(Collectors.toList());
    }
    public boolean checkStatus(){
        for (Entity entity : entities){
            if(entity.checkStatus()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(entities);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ManyEnt that = (ManyEnt) obj;
        return entities.equals(that.entities);
    }
    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(";");
        for (Entity entity : entities)
            joiner.add(entity.toString());
        return joiner.toString();
    }
}
