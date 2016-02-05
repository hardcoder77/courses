package com.manage.courses.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.CourseRepository;
import com.manage.courses.internal.course.CourseResource;
import com.manage.courses.internal.datastore.DataStore;
import com.manage.courses.internal.datastore.IDataStore;
import com.manage.courses.internal.user.UserRepository;
import com.manage.courses.internal.user.UserResource;
import com.manage.courses.user.IUserRepository;
import io.dropwizard.jackson.Jackson;

public class CourseManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserResource.class).in(Singleton.class);
        bind(IUserRepository.class).to(UserRepository.class).in(Singleton.class);
        bind(CourseResource.class).in(Singleton.class);
        bind(ICourseRepository.class).to(CourseRepository.class).in(Singleton.class);
        bind(IDataStore.class).to(DataStore.class).in(Singleton.class);
    }

    @Provides
    ObjectMapper provideObjectMapper() {
        return Jackson.newObjectMapper();
    }

}

