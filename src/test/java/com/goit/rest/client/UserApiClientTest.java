package com.goit.rest.client;

import com.goit.dto.UserDto;
import com.goit.rest.client.test.util.TestData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserApiClientTest {
    private static String TEST_BASE_URL;
    public static final int TEST_USERS_COUNT = 10;
    public static final List<UserDto> testUsers = TestData.createTestUsers(TEST_USERS_COUNT);
    public static final List<UserDto> testUsersAfterDelete = TestData.createTestUsers(TEST_USERS_COUNT-1);

    @BeforeAll
    public static void init() throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        MockWebServer server = new MockWebServer();

        // Schedule some responses.
        server.enqueue(new MockResponse().setBody(gson.toJson(testUsers)));
        server.enqueue(new MockResponse().setBody(gson.toJson(testUsers)));
        server.enqueue(new MockResponse().setBody(gson.toJson(testUsers)));
        server.enqueue(new MockResponse().setBody(gson.toJson(testUsersAfterDelete)));

        // Start the server.
        server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
        TEST_BASE_URL = server.url(UserApiClient.USER_ENDPOINT).url().toString();
    }

    @Disabled
    @Test
    @DisplayName("Client instantiation does not throw any exception")
    void apiClientInstance() {
        //Given
        //When
        //Then
        Assertions.assertDoesNotThrow(() -> new UserApiClient(TEST_BASE_URL));
    }

    @Test
    @Order(1)
    @DisplayName("Client get http call works correct")
    void get() {
        //Given
        UserApiClient apiClient = new UserApiClient(TEST_BASE_URL);
        //When
        List<UserDto> users = apiClient.get();
        //Then
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(TEST_USERS_COUNT, users.size());
        assertEquals(testUsers, users);
    }

    @Test
    @Order(2)
    @DisplayName("Client delete http call works correct")
    void delete() {
        //Given
        UserDto userDto = testUsers.get(0);
        UserApiClient apiClient = new UserApiClient(TEST_BASE_URL);
        List<UserDto> actualUsers = apiClient.get();
        //When
        apiClient.delete(userDto.getId());
        //Then
        List<UserDto> expectedUsers = apiClient.get();
        assertEquals(1, actualUsers.size() - expectedUsers.size());
    }
}