package com.goit.rest.client.test.util;

import com.goit.dto.UserDto;
import com.goit.enums.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestData {

    public static List<UserDto> createTestUsers(int count) {
        List<UserDto> testUsers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            testUsers.add(createTestUser(i + 1));
        }
        return testUsers;
    }

    private static UserDto createTestUser(long id) {
        UserDto user = new UserDto();
        user.setId(id);
        user.setName("TEST_NAME" + id);
        user.setEmail("test@example.com");
        user.setGender(id % 2 == 0 ? "MALE" : "FEMALE");
        user.setStatus(id % 2 > 0 ? Status.active : Status.inactive);
        return user;
    }
}
