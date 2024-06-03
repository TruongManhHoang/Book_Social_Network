package com.devteria.profile.controller;

import com.devteria.profile.dto.request.UserProfileCreateRequest;
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

    @PostMapping
    public ApiResponse<UserProfileResponse> createProfile(@RequestBody UserProfileCreateRequest request){
        ApiResponse<UserProfileResponse> response = new ApiResponse<>();
        response.setResult(userProfileService.createProfile(request));
        return response;
    }
    @GetMapping
    public ApiResponse<List<UserProfileResponse>> getProfile(){
        ApiResponse<List<UserProfileResponse>> response = new ApiResponse<>();
        response.setResult(userProfileService.getProfile());
        return response;
    }
    @GetMapping("/{profileId}")
    public ApiResponse<UserProfileResponse> getProfileById(@PathVariable("profileId") String profileId){
        ApiResponse<UserProfileResponse> response = new ApiResponse<>();
        response.setResult(userProfileService.getProfileById(profileId));
        return response;
    }
    @PutMapping("/{profileId}")
    public ApiResponse<UserProfileResponse> updateProfile(@RequestBody UserProfileCreateRequest request,@PathVariable("profileId") String profileId){
        ApiResponse<UserProfileResponse> response = new ApiResponse<>();
        response.setResult(userProfileService.updateUser(profileId,request));
        return response;
    }
    @DeleteMapping("/{profileId}")
    public ApiResponse<String> delete(@PathVariable("profileId") String profileId){
        userProfileService.delete(profileId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("User has been deleted");
        return response;
    }
}
