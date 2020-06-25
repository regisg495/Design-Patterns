package activerecord_pool.services;

import java.util.List;

import activerecord_pool.exception.InvalidValueException;


public abstract class ActiveRecord<T> {

	public abstract List<T> where(String coluna, String valor);

	public abstract List<T> loadAll();

	public abstract void saveIt();

	public abstract boolean containsField(String coluna);

	public abstract void set(String coluna, Object valor)
			throws InvalidValueException, NoSuchFieldException, SecurityException;

	public abstract void createIt(T object);

	public abstract void delete(T object);

	public abstract void edit(T object);

}
