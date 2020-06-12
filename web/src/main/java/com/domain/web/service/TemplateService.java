package com.domain.web.service;

import com.domain.dao.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author leone
 **/
@Service
public class TemplateService {

    @Resource
    private UserRepository userRepository;


}
