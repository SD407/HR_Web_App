package ro.sit.hrapp.imdao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ro.sit.hrapp.dao.BaseDAO;
import ro.sit.hrapp.domain.AbstractModel;

public class ImBaseDao<T extends AbstractModel> implements BaseDAO<T> {

	private Map<Long, T> companyModel = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

	@Override
	public T findByIdCompany(Long id) {

		return companyModel.get(id);
	}

	@Override
	public T updateCompany(T model) {

		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}

		companyModel.put(model.getId(), model);
		return model;
	}

	@Override
	public boolean deleteCompany(T model) {

		boolean result = companyModel.containsKey(model.getId());

		if (result)
			companyModel.remove(model.getId());
		return false;
	}

	@Override
	public T findByIdCandidate(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T updateCandidate(T model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCandidate(T model) {
		// TODO Auto-generated method stub
		return false;
	}

}
