package restaurant.repositories.interfaces;

import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table>{

    private List<Table> entities;

    public TableRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }
    @Override
    public void add(Table entity) {
        this.entities.add(entity);
    }
    @Override
    public Table byNumber(int number) {
        return entities.stream().filter(t -> t.getTableNumber() == number).findFirst().orElse(null);
        //return this.entities.get(number);
    }
}
