package com.enzulode.domain;

public record SignUpRequestModel(
    String username,
    String email,
    String password
) {}
