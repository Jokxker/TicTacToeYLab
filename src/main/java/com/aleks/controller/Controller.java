package com.aleks.controller;

import com.aleks.repository.StorageH2;

public abstract class Controller {
    StorageH2 dbH2;

    public abstract void start();
}
