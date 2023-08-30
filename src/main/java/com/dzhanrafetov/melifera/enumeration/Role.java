package com.dzhanrafetov.melifera.enumeration;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

        public static Stream<Role> stream() {
            return Arrays.stream(Role.values());
        }
    }

