package com.app;

import com.app.model.Result;
import com.app.model.Triangle;
import com.app.model.TriangleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TriangleChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private final String URL = "/api/triangleType";

	@Test
	public void checkSidesWithNullValue() throws Exception {
		String triangle = createTriangle(null, null, null);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(3, errorList.size());
	}

	@Test
	public void checkSidesWithLessThanOrEqualToZero() throws Exception {
		String triangle = createTriangle(-1, 0, -2);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(3, errorList.size());
	}

	//Equilateral
	@Test
	public void checkForEquilateral() throws Exception {
		String triangle = createTriangle(1, 1, 1);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.OK.value());

		Result result = objectMapper.readValue(response.getContentAsString(), Result.class);
		assertEquals(result.getData(), TriangleType.EQUILATERAL.getDisplayName());
	}

	//Condition => sideA = sideB+sideC
	@Test
	public void checkForInvalidTypes1() throws Exception {
		String triangle = createTriangle(2, 1, 1);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => sideA > sideB+sideC
	@Test
	public void checkForInvalidTypes2() throws Exception {
		String triangle = createTriangle(4, 1, 2);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => sideC = sideB+sideA
	@Test
	public void checkForInvalidTypes3() throws Exception {
		String triangle = createTriangle(1, 2, 3);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => sideC > sideB+sideA
	@Test
	public void checkForInvalidTypes4() throws Exception {
		String triangle = createTriangle(1, 3, 5);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => sideB = sideA+sideC
	@Test
	public void checkForInvalidTypes5() throws Exception {
		String triangle = createTriangle(3, 4, 1);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => sideB > sideA+sideC
	@Test
	public void checkForInvalidTypes6() throws Exception {
		String triangle = createTriangle(2, 6, 2);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());

		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> errorList = objectMapper.readValue(response.getContentAsString(), type);
		assertEquals(1, errorList.size());
	}

	//Condition => SideA == SideB
	@Test
	public void checkForIsosceles1() throws Exception {
		String triangle = createTriangle(2, 2, 3);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.OK.value());

		Result result = objectMapper.readValue(response.getContentAsString(), Result.class);
		assertEquals(TriangleType.ISOSCELES.getDisplayName(), result.getData());
	}

	//Condition => SideB == SideC
	@Test
	public void checkForIsosceles2() throws Exception {
		String triangle = createTriangle(3, 4, 4);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.OK.value());

		Result result = objectMapper.readValue(response.getContentAsString(), Result.class);
		assertEquals(TriangleType.ISOSCELES.getDisplayName(), result.getData());
	}

	//Condition => SideA == SideC
	@Test
	public void checkForIsosceles3() throws Exception {
		String triangle = createTriangle(5, 2, 5);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.OK.value());

		Result result = objectMapper.readValue(response.getContentAsString(), Result.class);
		assertEquals(TriangleType.ISOSCELES.getDisplayName(), result.getData());
	}

	//Scalene
	@Test
	public void checkForScalene() throws Exception {
		String triangle = createTriangle(6, 2, 5);
		MockHttpServletResponse response = getResult(triangle);

		assertEquals(response.getStatus(), HttpStatus.OK.value());

		Result result = objectMapper.readValue(response.getContentAsString(), Result.class);
		assertEquals(TriangleType.SCALENE.getDisplayName(), result.getData());
	}

	private MockHttpServletResponse getResult(String triangle) throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URL)
				.accept(MediaType.APPLICATION_JSON).content(triangle)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		return result.getResponse();
	}

	private String createTriangle(Integer sideA, Integer sideB, Integer sideC) throws JsonProcessingException {
		Triangle triangle = new Triangle();
		triangle.setSideA(sideA);
		triangle.setSideB(sideB);
		triangle.setSideC(sideC);

		return objectMapper.writeValueAsString(triangle);
	}

}
