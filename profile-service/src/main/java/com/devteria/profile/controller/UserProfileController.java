package com.devteria.profile.controller;

import com.devteria.profile.dto.response.ApiResponse;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userProfile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileController {
    UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{profileId}")
    public ApiResponse<UserProfileResponse> getProfileById(@PathVariable("profileId") String profileId){
        ApiResponse<UserProfileResponse> response = new ApiResponse<>();
        response.setResult(userProfileService.getProfileById(profileId));
        return response;
    }
}
