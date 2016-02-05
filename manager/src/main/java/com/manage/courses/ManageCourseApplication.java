package com.manage.courses;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.manage.courses.config.CourseManagerConfiguration;
import com.manage.courses.config.CourseManagerModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ManageCourseApplication extends Application<CourseManagerConfiguration> {

  private GuiceBundle<CourseManagerConfiguration> guiceBundle;

  public static void main(String[] args) throws Exception {
    new ManageCourseApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<CourseManagerConfiguration> bootstrap) {

    GuiceBundle.Builder<CourseManagerConfiguration>
      guiceBundleBuilder = GuiceBundle.newBuilder();

    guiceBundle = guiceBundleBuilder
      .setConfigClass(CourseManagerConfiguration.class)

      .addModule(new CourseManagerModule())

      .enableAutoConfig("")
      .build(Stage.DEVELOPMENT);

    bootstrap.addBundle(guiceBundle);

  }

  @Override
  public void run(CourseManagerConfiguration nostraConfiguration, Environment environment) throws Exception {

  }
}

