package org.hbrs.se2.project.coll.control;

import org.hbrs.se2.project.coll.dtos.impl.UserDTOImpl;
import org.hbrs.se2.project.coll.entities.Company;
import org.hbrs.se2.project.coll.entities.ContactPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class AuthorizationControlTest {

    private AuthorizationControl authorizationControl;
    private ContactPersonControl contactPersonControl;

    private UserDTOImpl user;
    private ContactPerson contactPerson;

    @BeforeEach
    public void setUp() {
        authorizationControl = new AuthorizationControl();
        contactPersonControl = Mockito.mock(ContactPersonControl.class);
        ReflectionTestUtils.setField(authorizationControl, "contactPersonControl", contactPersonControl);
        user = new UserDTOImpl();
        user.setId(1);
        contactPerson = new ContactPerson();
        Company company = new Company();
        company.setId(10);
        contactPerson.setCompany(company);
    }

    @Test
    void checkPositiveIsUserAllowedToAccessCompanyApplications() {

        Mockito.when(contactPersonControl.findContactPersonById(anyInt())).thenReturn(contactPerson);

        boolean result = authorizationControl.isUserAllowedToAccessCompanyApplications(user, 10);

        assertTrue(result);
    }

    @Test
    void checkNegativeNullIsUserAllowedToAccessCompanyApplications() {

        Mockito.when(contactPersonControl.findContactPersonById(anyInt())).thenReturn(null);

        boolean result = authorizationControl.isUserAllowedToAccessCompanyApplications(user, 10);

        assertFalse(result);
    }

    @Test
    void checkNegativeContactIsUserAllowedToAccessCompanyApplications() {

        Mockito.when(contactPersonControl.findContactPersonById(anyInt())).thenReturn(contactPerson);

        boolean result = authorizationControl.isUserAllowedToAccessCompanyApplications(user, 100);

        assertFalse(result);
    }

}