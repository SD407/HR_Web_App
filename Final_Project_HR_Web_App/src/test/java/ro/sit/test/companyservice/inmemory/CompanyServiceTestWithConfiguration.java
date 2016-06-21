package ro.sit.test.companyservice.inmemory;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sit.hrapp.TestApplicationConfiguration;
import ro.sit.hrapp.service.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestApplicationConfiguration.class })
public class CompanyServiceTestWithConfiguration extends BaseCompanyServiceTest {

	@Autowired
	private CompanyService service;

	@Override
	protected CompanyService getCompanyService() {
		return service;
	}

}
