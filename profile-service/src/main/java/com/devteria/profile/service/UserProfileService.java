package com.devteria.profile.service;

import com.devteria.profile.dto.request.UserProfileCreateRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.exception.AppException;
import com.devteria.profile.exception.ErrorCode;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;

    UserProfileMapper userProfileMapper;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    public UserProfileResponse createProfile(UserProfileCreateRequest request){
         UserProfile userProfile = userProfileMapper.toUserProfile(request);

         userProfile = userProfileRepository.save(userProfile);

         return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse updateUser(String userId, UserProfileCreateRequest request) {
        UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userProfileMapper.updateUser(user, request);

        return userProfileMapper.toUserProfileResponse(userProfileRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getProfile(){
        return userProfileRepository.findAll().stream().map(userProfileMapper::toUserProfileResponse).toList();
    }
    public UserProfileResponse getProfileById(String id){
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
    public void delete(String id){
        userProfileRepository.deleteById(id);
    }
}
