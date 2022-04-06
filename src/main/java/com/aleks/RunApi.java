package com.aleks;

import com.aleks.controller.ApiController;
import com.aleks.controller.Controller;

public class RunApi {
    public static void main(String[] args) {
        Controller controller = new ApiController();
        controller.start();
    }
}
