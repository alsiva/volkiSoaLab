package volki.soalab.dragonManipulator.mapper.fieldMapper;

public interface Mapper<T> {
    Comparable<?> map(DragonFields dragonFields, T t);
}
