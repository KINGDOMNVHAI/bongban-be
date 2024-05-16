package com.codewithproject.springsecurity.services;

import com.codewithproject.springsecurity.dto.response.BladeListResponse;

import java.util.List;

public interface BladeService {

    List<BladeListResponse> getListBlade();
}
