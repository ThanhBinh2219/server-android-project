package com.commic.v1.services.user;

import com.commic.v1.dto.UserDTO;
import com.commic.v1.dto.requests.ChangePasswordRequest;
import com.commic.v1.dto.responses.APIResponse;

public interface IUserService {
    /**
     * Generates an OTP (One-Time Password) and sends it to the provided email address.
     *
     * @param email The email address to which the OTP will be sent.
     * @return An APIResponse<Void> indicating the result of the operation.
     */
    APIResponse<Void> forgotPassword(String email);
    APIResponse<Void> changePassword(ChangePasswordRequest passwordRequest);

    APIResponse<Void> register(UserDTO userDTO);
}
