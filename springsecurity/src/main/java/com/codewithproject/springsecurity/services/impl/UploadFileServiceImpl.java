package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.repository.BladeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileServiceImpl {

    @Autowired
    private BladeRepository bladeRepo;

}
