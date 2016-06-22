package org.demo.controller.utils;

import org.demo.exception.ResourceNotFoundException;

public class RestPreconditions {
    public static <T> T checkNotNull(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
}