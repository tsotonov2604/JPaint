package model.interfaces;

public interface ICollection<T> {

    boolean contains(T item);

    void clear();

}