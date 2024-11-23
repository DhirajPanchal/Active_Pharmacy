package io.active.pharmacy.gateway.controller.temp;

import java.util.Set;

record ProfileResponse(String username, Set<String> roles) {
}
