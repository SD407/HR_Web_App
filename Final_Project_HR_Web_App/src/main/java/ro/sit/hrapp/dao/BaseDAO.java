package ro.sit.hrapp.dao;
/*
 * Changes done @DraganSorin
 */
import java.util.Collection;

import ro.sit.hrapp.domain.AbstractModel;

/**
 * 
 * @author stefan
 *
 * @param <T>
 */
public interface BaseDAO<T extends AbstractModel> {
	
	Collection<T> getAll(); //added

	T findById(Long id);
	
	T update(T model);

	boolean delete(T model);

}
