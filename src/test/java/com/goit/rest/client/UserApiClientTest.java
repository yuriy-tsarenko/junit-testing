package com.goit.rest.client;

import com.goit.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserApiClientTest {
    private static final String TEST_BASE_URL = "https://test.host";
    public static final String BASE_URL = "https://gorest.co.in/public/v2";

    @BeforeAll
    public static void init() {
        // setup configuration before all tests
    }

    @Test
    void ifCreationOfInstanceDoesNotThrow() {
        //Given
        //When
        //Then
        Assertions.assertDoesNotThrow(() -> new UserApiClient(TEST_BASE_URL));
    }

    @Test
    void getReturnsData() {
        //Given
        UserApiClient apiClient = new UserApiClient(BASE_URL);
        //When
        List<UserDto> users = apiClient.get();
        //Then
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }
}