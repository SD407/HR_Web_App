package ro.sit.hrapp.dao.inmemory;

/*
 * Changes done @DraganSorin
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ro.sit.hrapp.dao.BaseDAO;
import ro.sit.hrapp.domain.AbstractModel;

/**
 * 
 * @author stefan
 *
 * @param <T>
 */
public class IMBaseDAO<T extends AbstractModel> implements BaseDAO<T> {

	private Map<Long, T> baseModel = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());
	
	@Override
	public Collection<T> getAll() {
		return baseModel.values();
	}

	@Override
	public T findById(Long id) {
		return baseModel.get(id);
	}

	@Override
	public T update(T model) {

		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}

		baseModel.put(model.getId(), model);
		return model;
	}

	@Override
	public boolean delete(T model) {

		boolean result = baseModel.containsKey(model.getId());

		if (result)
			baseModel.remove(model.getId());
		return false;
	}

}
