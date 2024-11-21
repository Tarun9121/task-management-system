package com.task.task.management.system.service.implementation;

import com.task.task.management.system.constants.TaskManagementSystem;
import com.task.task.management.system.dto.LoginDto;
import com.task.task.management.system.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LoginService {
    public ResponseEntity<String> checkUserCrediantals(LoginDto loginDto) {
        try {
            if(ObjectUtils.isEmpty(loginDto)) {
                throw new ApiException(TaskManagementSystem.CREDIANTALS_WRONG);
            }

            if(loginDto.getUsername().equals("TarunSwaroop") && loginDto.getPassword().equals("0000")) {
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failure");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unexpected error");
        }
    }
}
