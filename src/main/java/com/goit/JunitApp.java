package com.goit;

import com.goit.dto.UserDto;
import com.goit.rest.client.UserApiClient;

import java.util.List;
import java.util.Scanner;

public class JunitApp {
    public static final String BASE_URL = "https://gorest.co.in/public/v2";

    public static void main(String[] args) {
        UserApiClient apiClient = new UserApiClient(BASE_URL);
        String message = "Enter action:\n1 for load users \n2 createUser\n";
        System.out.printf(message);
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                List<UserDto> allUsers = apiClient.get();
                allUsers.forEach(System.out::println);
                break;
            case "2":
                //
            default:
                System.err.println("incorrect action");
        }
    }
}
