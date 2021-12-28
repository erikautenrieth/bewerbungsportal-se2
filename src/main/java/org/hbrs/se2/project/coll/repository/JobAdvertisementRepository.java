package org.hbrs.se2.project.coll.repository;

import org.hbrs.se2.project.coll.dtos.JobAdvertisementDTO;
import org.hbrs.se2.project.coll.entities.JobAdvertisement;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Integer> {

    JobAdvertisement findJobAdvertisementByJobDescription(String string);
    JobAdvertisement findJobAdvertisementById(int id);

    // TODO: Pull out Queries and name these methods better.
    // Filter functions ...
    // All filters
    List<JobAdvertisement> findJobAdvertisementsByJobTitleContainsIgnoreCaseAndTypeOfEmploymentContainsIgnoreCaseAndRequirementsContainsIgnoreCaseAndTemporaryEmploymentAndStartOfWorkIsGreaterThanEqualOrderByStartOfWorkDesc(String title,
                                                                                                                                                                                                                               String type,
                                                                                                                                                                                                                               String requirements,
                                                                                                                                                                                                                               boolean temporaryEmployment, LocalDate date);
    // Title, Type, Requirements only
    List<JobAdvertisement> findJobAdvertisementsByJobTitleContainsIgnoreCaseAndTypeOfEmploymentContainsIgnoreCaseAndRequirementsContainsIgnoreCaseAndStartOfWorkIsGreaterThanEqualOrderByStartOfWorkDesc(String title,
                                                                                                                                                                                                         String type,
                                                                                                                                                                                                         String requirements, LocalDate date);

    // Title, Requirements, Temporary employment only
    List<JobAdvertisement> findJobAdvertisementsByJobTitleContainsIgnoreCaseAndRequirementsContainsIgnoreCaseAndTemporaryEmploymentAndStartOfWorkIsGreaterThanEqualOrderByStartOfWorkDesc(String title,
                                                                                                                                                                                          String requirements,
                                                                                                                                                                                          boolean temporaryEmployment, LocalDate date);

    // Title, Requirements only
    List<JobAdvertisement> findJobAdvertisementsByJobTitleContainsIgnoreCaseAndRequirementsContainsIgnoreCaseAndStartOfWorkIsGreaterThanEqualOrderByStartOfWorkDesc(String title, String requirements, LocalDate date);


    /* Finds all JobAdvertisements from a given CompanyId
    */
    @Query(
            value = "SELECT * FROM collhbrs.col_tab_job_advertisement a WHERE a.contact_person_id = " +
            "(SELECT MAX(user_id) FROM collhbrs.col_tab_contact_person WHERE company_id = :id)",
            nativeQuery = true
    )
    List<JobAdvertisement> findJobAdvertisementsByCompanyId(@Param("id") int id);

}
