package com.goit.rest.client;

import com.goit.dto.UserDto;
import com.goit.rest.client.test.util.TestData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserApiClientTest {
    private static String testBaseUrl;
    public static final int TEST_USERS_COUNT = 10;
    public static final List<UserDto> TEST_USERS = TestData.createTestUsers(TEST_USERS_COUNT);
    public static final List<UserDto> TEST_USERS_AFTER_DELETE = TestData.createTestUsers(TEST_USERS_COUNT - 1);

    @BeforeAll
    public static void init() throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        MockWebServer server = new MockWebServer();

        // Schedule some responses.
        server.enqueue(new MockResponse().setBody(gson.toJson(TEST_USERS)));
        server.enqueue(new MockResponse().setBody(gson.toJson(TEST_USERS)));
        server.enqueue(new MockResponse().setBody(gson.toJson(TEST_USERS)));
        server.enqueue(new MockResponse().setBody(gson.toJson(TEST_USERS_AFTER_DELETE)));

        // Start the server.
        server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
        testBaseUrl = server.url(UserApiClient.USER_ENDPOINT).url().toString();
    }

    @Disabled
    @Test
    @DisplayName("Client instantiation does not throw any exception")
    void apiClientInstance() {
        //Given
        //When
        //Then
        Assertions.assertDoesNotThrow(() -> new UserApiClient(testBaseUrl));
    }

    @Test
    @Order(1)
    @DisplayName("Client get http call works correct")
    void get() {
        //Given
        UserApiClient apiClient = new UserApiClient(testBaseUrl);
        //When
        List<UserDto> users = apiClient.get();
        //Then
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(TEST_USERS_COUNT, users.size());
        assertEquals(TEST_USERS, users);
    }

    @Test
    @Order(2)
    @DisplayName("Client delete http call works correct")
    void delete() {
        //Given
        UserDto userDto = TEST_USERS.get(0);
        UserApiClient apiClient = new UserApiClient(testBaseUrl);
        List<UserDto> actualUsers = apiClient.get();
        //When
        apiClient.delete(userDto.getId());
        //Then
        List<UserDto> expectedUsers = apiClient.get();
        assertEquals(1, actualUsers.size() - expectedUsers.size());
    }
}
