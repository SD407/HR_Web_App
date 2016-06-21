package ro.sit.hrapp.imdao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ro.sit.hrapp.dao.BaseDAO;
import ro.sit.hrapp.domain.AbstractModel;

public class ImBaseDao<T extends AbstractModel> implements BaseDAO<T> {

	private Map<Long, T> companyModel = new HashMap<Long, T>();
	private Map<Long, T> candidateModel = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

	@Override
	public Collection<T> getAllCompanies() {

		return companyModel.values();
	}

	@Override
	public Collection<T> getAllCandidates() {

		return candidateModel.values();
	}

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
		return candidateModel.get(id);
	}

	@Override
	public T updateCandidate(T model) {
		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}

		candidateModel.put(model.getId(), model);
		return model;
	}

	@Override
	public boolean deleteCandidate(T model) {
		boolean result = candidateModel.containsKey(model.getId());

		if (result)
			candidateModel.remove(model.getId());
		return false;
	}

}
