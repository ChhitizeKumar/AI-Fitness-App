package com.fitnessai.activityservice.service;

import com.fitnessai.activityservice.model.Activity;
import com.fitnessai.activityservice.repository.ActivityRepository;
import com.fitnessai.activityservice.dto.ActivityRequest;
import com.fitnessai.activityservice.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest request) {

        String userId = request.getUserId();

        if(!userValidationService.validateUser(userId))
            throw new  RuntimeException("Invalid User: "+ userId);


        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .duration(request.getDuration())
                .additionalMetrics(request.getAdditionalMetrics())
                .activityType(request.getActivityType())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {


        ActivityResponse response = new ActivityResponse();

        response.setId(activity.getId());
        response.setActivityType(activity.getActivityType());
        response.setDuration(activity.getDuration());
        response.setCreatedAt(activity.getCreatedAt());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setStartTime(activity.getStartTime());
        response.setUpdatedAt(activity.getUpdatedAt());
        response.setUserId(activity.getUserId());

        return response;
    }
}
