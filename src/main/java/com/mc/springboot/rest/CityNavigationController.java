/**
 * 
 */
package com.mc.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.springboot.rest.error.CityNotFoundException;
import com.mc.springboot.service.ICityNavigationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author praveen
 *
 */


@Api(tags = "City Navigation API", value = "cityConnectionsAPI", description = "Controller for evaluating if two cities are connected")
@RestController
@EnableSwagger2
@RequestMapping("/")
public class CityNavigationController {

	@Autowired
	ICityNavigationService navigationService;
	
	@ApiOperation(value = "Find if two cities are conencted", notes = "Returns YES if they are connected else NO ", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success. the cities are connected", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request, Either Origin or Destination is invalid", response = CityNotFoundException.class),
			@ApiResponse(code = 401, message = "UnAuthenticated", response = CityNotFoundException.class),
			@ApiResponse(code = 500, message = "Internal server Error occured while finding the cities are connected", response = Exception.class)
	})
	@GetMapping(value = "/connected", produces = MediaType.TEXT_PLAIN_VALUE)
	public String findIfCitiesAreConnected(@ApiParam(name = "origin", value = "Starting City", required = true) @RequestParam(name = "origin", defaultValue="") String start,
			@ApiParam(name = "destination", value = "Destination City", required = true) @RequestParam(name = "destination", defaultValue = "") String end) throws Exception{
		boolean areTwoCitiesConnected = false; 
		try {
			if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
				throw new CityNotFoundException(HttpStatus.BAD_REQUEST, "Bad Request: Provide valid Origin and Destination");
			}
			areTwoCitiesConnected =  navigationService.isConnected(start, end);
		} catch (Exception e) {
			throw e;
		}
		return areTwoCitiesConnected ? "yes" :"no";
	}
}
