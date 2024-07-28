package com.felipemelozx.championship_management_system.dto.user;

import com.felipemelozx.championship_management_system.entity.Team;

public record CreateUserDto(String name,
                            String email,
                            String password,
                            Byte age,
                            Team team) {
}
