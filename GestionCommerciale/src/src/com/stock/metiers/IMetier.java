package src.com.stock.metiers;

import java.util.List;

public interface IMetier<T> {

    boolean create(T o);
	
	boolean update(T o);
	
	boolean delete(T o);
	
	T findById(int id);
	
	List<T> findAll();
}
