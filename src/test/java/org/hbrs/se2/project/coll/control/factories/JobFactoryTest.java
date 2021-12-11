package org.hbrs.se2.project.coll.control.factories;

import org.hbrs.se2.project.coll.dtos.RecruitmentAdvertisingDTO;
import org.hbrs.se2.project.coll.dtos.impl.RecruitmentAdvertisingDTOImpl;
import org.hbrs.se2.project.coll.entities.Address;
import org.hbrs.se2.project.coll.entities.ContactPerson;
import org.hbrs.se2.project.coll.entities.JobAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class JobFactoryTest {

    private RecruitmentAdvertisingDTOImpl dto;

    private short workingHours = 40;

    @Mock
    Address address;

    @Mock
    ContactPerson contactPerson;

    @BeforeEach
    void setUp() {
        address = spy(new Address());
        contactPerson = spy(new ContactPerson());
        MockitoAnnotations.openMocks(this);
        dto = new RecruitmentAdvertisingDTOImpl();
        dto.setId(100);
        dto.setTemporaryEmployment(true);
        dto.setTypeOfEmployment("Vollzeit");
        dto.setWorkingHours(workingHours);
        dto.setRequirements("Lorem Ipsum");
        dto.setAddress(address);
        dto.setStartOfWork(LocalDate.of(1999,1,23));
        dto.setEndOfWork(LocalDate.of(2000,1,2));
        dto.setJobDescription("Sed ut perspiciatis");
        dto.setJobTitle("Datenbankexperte");
        dto.setContactPerson(contactPerson);

    }

    @Test
    void createJob() {

        JobAdvertisement jobAdvertisement = JobFactory.createJob(dto);
        assertEquals( 100 , jobAdvertisement.getId());
        assertTrue(jobAdvertisement.getTemporaryEmployment());
        assertEquals("Vollzeit" , jobAdvertisement.getTypeOfEmployment());
        assertEquals(Integer.valueOf(40) , Integer.valueOf(jobAdvertisement.getWorkingHours()));
        assertEquals("Lorem Ipsum" , jobAdvertisement.getRequirements());
        assertNotNull(jobAdvertisement.getWorkingLocation());
        assertSame(address, jobAdvertisement.getWorkingLocation());
        assertEquals("1999-01-23" , jobAdvertisement.getStartOfWork().toString());
        assertEquals("2000-01-02" , jobAdvertisement.getEndOfWork().toString());
        assertEquals("Sed ut perspiciatis" , jobAdvertisement.getJobDescription());
        assertEquals("Datenbankexperte" , jobAdvertisement.getJobTitle());
        assertNotNull(jobAdvertisement.getContactPerson());
        assertSame(contactPerson, jobAdvertisement.getContactPerson());





    }
}