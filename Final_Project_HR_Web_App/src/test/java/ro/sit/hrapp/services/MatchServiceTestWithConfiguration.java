package ro.sit.hrapp.services;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sit.hrapp.TestApplicationConfiguration;
import ro.sit.hrapp.service.CandidateService;
import ro.sit.hrapp.service.CompanyService;
import ro.sit.hrapp.service.MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestApplicationConfiguration.class })
public class MatchServiceTestWithConfiguration extends BaseMatchServiceTest {

	@Autowired
	private MatchService mService;

	@Autowired
	private CompanyService compService;

	@Autowired
	private CandidateService candService;

	@Override
	protected MatchService getMatchService() {

		return mService;
	}

	@Override
	protected CompanyService getCompanyService() {

		return compService;
	}

	@Override
	protected CandidateService getCandidateService() {

		return candService;
	}

}
