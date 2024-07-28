package com.felipemelozx.championship_management_system.dto.user;

import java.util.List;

public record FailureResponseDTO (boolean success, List<String> fails) {
}