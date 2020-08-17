package com.mc.springboot.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mc.springboot.rest.CityNavigationController;
import com.mc.springboot.service.ICityNavigationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class MastercardTestApplicationTests {

	@LocalServerPort
	int randomServerPort;
	@Autowired
	private CityNavigationController controller;
	@Autowired
	private ICityNavigationService navigationService;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void initialLoadFailure() {
		boolean isLoadFailure = false;
		try {
			navigationService.load();
		}catch (Exception ex) {
			isLoadFailure = true;
		}
		Assert.assertFalse("initial data load failure", isLoadFailure);
	}


	@Test
	public void sameCity() throws Exception {
		Assert.assertTrue(navigationService.isConnected("Boston", "Boston"));
	}

	@Test
	public void connectedCity() throws Exception {
		Assert.assertTrue(navigationService.isConnected("Albany", "Trenton"));
	}

	@Test
	public void testBadRequest() throws URISyntaxException 
	{
		final String baseUrl = "http://localhost:"+randomServerPort+"/connected";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		assertThat(result.getStatusCodeValue()).isEqualTo(400); 
	}

	@Test
	public void testLowerCase() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "boston");
		queryParams.put("destination", "newark");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.equals("yes")); 
	}

	@Test
	public void testZigzagCase() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "PHILAdelphIA");
		queryParams.put("destination", "NEWark");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.equals("yes")); 
	}

	@Test
	public void testConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Newark");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.equals("yes")); 
	}
	@Test
	public void testUnConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Albany");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.equals("no")); 
	}

	@Test
	public void testInvalidCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "AA");
		queryParams.put("destination", "BB");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.equals("no")); 
	}

}