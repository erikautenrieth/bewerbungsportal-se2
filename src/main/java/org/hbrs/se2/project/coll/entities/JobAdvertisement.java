package org.hbrs.se2.project.coll.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name ="col_tab_job_advertisement" , schema = "collhbrs" )
public class JobAdvertisement {

    private int id;
    private boolean temporaryEmployment;
    private short workingHours;
    private String requirements;
    private Address workingLocation;
    private LocalDate startOfWork;
    private LocalDate endOfWork;
    private ContactPerson contactPerson;
    private String jobDescription;
    private String jobTitle;
    private CompanyProfile company;
    //TODO JOIN mit Studenten muss N:M sein und nicht 1:N

    @Id
    @GeneratedValue(
            generator = "advertisement_id"
    )
    @SequenceGenerator(
            name = "advertisement_id",
            sequenceName = "collhbrs.col_seq_advertisement_id"
    )
    @Column(name = "advertisement_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "temporary_employment")
    public boolean getTemporaryEmployment() {
        return temporaryEmployment;
    }
    public void setTemporaryEmployment(boolean temporaryEmployment) {
        this.temporaryEmployment = temporaryEmployment;
    }

    @Basic
    @Column(name = "working_hours")
    public short getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(short workingHours) {
        this.workingHours = workingHours;
    }

    @Basic
    @Column(name = "requirements")
    public String getRequirements() {
        return requirements;
    }
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @ManyToOne
    @JoinColumn(name = "address_id")
    public Address getWorkingLocation() {
        return workingLocation;
    }
    public void setWorkingLocation(Address workingLocation) {
        this.workingLocation = workingLocation;
    }

    @Basic
    @Column(name = "start_of_work")
    public LocalDate getStartOfWork() {
        return startOfWork;
    }
    public void setStartOfWork(LocalDate startOfWork) {
        this.startOfWork = startOfWork;
    }

    @Basic
    @Column(name = "end_of_work")
    public LocalDate getEndOfWork() {
        return endOfWork;
    }
    public void setEndOfWork(LocalDate endOfWork) {
            this.endOfWork = endOfWork;
    }

    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    public ContactPerson getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "job_description")
    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "job_title")
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @ManyToOne
    @JoinTable(
            name = "col_tab_contact_person",
            joinColumns = @JoinColumn(name = "company_id")
    )
    public CompanyProfile getCompany() {
        return company;
    }
    public void setCompany(CompanyProfile company) {
        this.company = company;
    }
}