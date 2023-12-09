package com.goodteacher.api.service;

public interface UserService {
    boolean anyByEmail(String email);

    boolean anyByNickname(String nickname);
}
