package com.example.componentB;

import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.componentB.ProviderContractTests.PROVIDER_NAME;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRestPactRunner.class)
@Provider(PROVIDER_NAME)
@PactBroker
@IgnoreNoPactsToVerify
public class ProviderContractTests {

	public static final String PROVIDER_NAME = "componentB";

	@TestTarget
	public final SpringBootHttpTarget target = new SpringBootHttpTarget();

	@MockBean
	private AlligatorRepository repository;

	@State("there is an alligator named Mary")
	public void getAlligatorMaryOk() {
		Alligator alligator = new Alligator("Mary");
		Mockito.when(this.repository.findByName("Mary")).thenReturn(alligator);
	}
}
