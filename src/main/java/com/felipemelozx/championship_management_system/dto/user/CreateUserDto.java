package com.felipemelozx.championship_management_system.dto.user;

public record CreateUserDto(String name,
                            String email,
                            String password,
                            Byte age,
                            String team) {
}
