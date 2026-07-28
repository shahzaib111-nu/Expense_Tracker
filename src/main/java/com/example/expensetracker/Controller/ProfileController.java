package com.example.expensetracker.Controller;


import com.example.expensetracker.DTO.Response.ProfileDTO;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.expensetracker.DTO.Request.ChangePasswordDTO;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(profileService.getProfile(user));
    }

    @PatchMapping ("/change-password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal User user , @RequestBody ChangePasswordDTO request){

        return ResponseEntity.ok(profileService.changePassword(user,request));

    }

}
