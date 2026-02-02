package com.predictorInfotech.sarkariUpdates.projection;

import com.predictorInfotech.sarkariUpdates.model.MenuItem;

import java.time.LocalDateTime;

public interface MenuDetailProjection {


    Long getId();

    MenuProjection getMenu();

    interface MenuProjection {
        Long getId();

        String getMenuName();
    }

    LocalDateTime getPostedDate();

    String getMetaTitle();

    String getMetaDescription();

    String getSlug();

    String getTitle();

    String getDescription();

    LocalDateTime getApplyStartDate();

    LocalDateTime getApplyLastDate();

    LocalDateTime getAdmitCardDate();

    LocalDateTime getNotificationDate();

    LocalDateTime getResultDate();

    String getApplicableFee();

    String getAgeLimit();

    Integer getTotalPost();

    String getVacancyDetails();

    String getFaqQuestions();

}
