package lv.adz.magic;

import java.util.Objects;

public class Entity {
    private Columns columns;
    private String number;
    private boolean status;

    public  Entity(Columns columns,String number){
        this.columns = columns;
        this.number = number;
        if (number.isEmpty()){
            status = false;
        }else {
            status = true;
        }
    }

    public boolean checkStatus(){
        return status;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)return true;
        if (o==null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return columns == entity.columns && number.equals(entity.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns,number);
    }

    @Override
    public String toString(){
        return "\""+number+ "\"";

    }

}
