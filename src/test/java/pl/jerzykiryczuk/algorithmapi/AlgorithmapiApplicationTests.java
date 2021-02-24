package pl.jerzykiryczuk.algorithmapi;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.MockMvc;
import pl.jerzykiryczuk.algorithmapi.entities.LuhnRequest;
import pl.jerzykiryczuk.algorithmapi.exceptions.Messages;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlgorithmapiApplicationTests {

    @Autowired
    private AlgorithmController algorithmController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(algorithmController).isNotNull();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/algorithms/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void shouldCheckValidityTrue() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"292723200000000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/checkValidity").
                then().
                statusCode(200).
                body("valid", equalTo(true));

    }

    @Test
    public void shouldCheckValidityFalse() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"292723200100000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/checkValidity").
                then().
                statusCode(200).
                body("valid", equalTo(false));

    }

    @Test
    public void shouldReturn400AfterWrongCheckValidityLetters() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010a000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/checkValidity").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }

    @Test
    public void shouldReturn400AfterWrongCheckValidityWhiteSpace() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010 000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/checkValidity").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }


    @Test
    public void shouldReturn400AfterWrongCheckValiditySpecialSign() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010;000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/checkValidity").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }


    @Test
    public void shouldReturnNumber() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"92480\"}").
                contentType("application/json").
                when().
                get("/algorithms/getControlNumber").
                then().
                statusCode(200).
                body("number", equalTo("924803"));
    }

    @Test
    public void shouldReturn400AfterWrongRequestGetControlNumberSpecialSign() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010;000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/getControlNumber").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }

    @Test
    public void shouldReturn400AfterWrongRequestGetControlNumberWhiteSpace() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010 000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/getControlNumber").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }

    @Test
    public void shouldReturn400AfterWrongRequestGetControlLetters() throws Exception {
        given().
                standaloneSetup(algorithmController).
                body("{\"number\": \"29272320010a000021\"}").
                contentType("application/json").
                when().
                get("/algorithms/getControlNumber").
                then().
                statusCode(400).
                body("message", equalTo(Messages.WRONG_PATTERN_MESSAGE));

    }

}
