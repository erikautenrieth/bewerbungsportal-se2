package org.hbrs.se2.project.coll.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.hbrs.se2.project.coll.control.StudentProfileControl;
import org.hbrs.se2.project.coll.dtos.StudentUserDTO;
import org.hbrs.se2.project.coll.dtos.UserDTO;
import org.hbrs.se2.project.coll.dtos.impl.StudentUserDTOImpl;
import org.hbrs.se2.project.coll.entities.Address;
import org.hbrs.se2.project.coll.entities.StudentUser;
import org.hbrs.se2.project.coll.layout.AppView;
import org.hbrs.se2.project.coll.repository.AddressRepository;
import org.hbrs.se2.project.coll.repository.StudentUserRepository;
import org.hbrs.se2.project.coll.util.Globals;
import org.hbrs.se2.project.coll.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Route(value = "profile_edit", layout = AppView.class)
@PageTitle("Edit your Profile")
public class StudentProfileEditView extends VerticalLayout implements HasUrlParameter<String>, BeforeEnterObserver {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private StudentProfileControl profileControl;

    @Autowired
    private AddressRepository addressRepository;

    private StudentUserDTO profileDTO;
    List<Address> existingAddresses;
    Address addr = new Address();
    int studentId;

    //automatisches binding der Textfelder in ein DTO kann nicht durchgeführt werden,
    //da address eigene Entity ist und kein String
    //private Binder<StudentUserDTOImpl> binder = new Binder(StudentUserDTOImpl.class);

    Label       lsalutation     = new Label("Anrede:");
    Label       ltitle          = new Label("Titel:");
    Label       lfirstname      = new Label("Vorname:");
    Label       llastname       = new Label("Nachname:");
    Label       lgraduation     = new Label("Abschluss:");
    Label       ldateOfBirth    = new Label("Geburtsdatum:");
    Label       lstreet         = new Label("Strasse:");
    Label       lstreetnumber   = new Label("Hausnummer:");
    Label       lpostalcode     = new Label("PLZ:");
    Label       lcity           = new Label("Ort:");
    Label       lcountry        = new Label("Land:");
    Label       lskills         = new Label("Skills:");
    Label       lemail          = new Label("E-Mail:");
    Label       lphone          = new Label("Telefon:");
    Label       linterests      = new Label("Interessen:");
    Label       lwebsite        = new Label("Webseite:");
    Label       ldescription    = new Label("Über mich:");

    TextField   salutation      = new TextField();
    TextField   title           = new TextField();
    TextField   firstName       = new TextField();
    TextField   lastName        = new TextField();
    TextField   graduation      = new TextField();
    DatePicker  dateOfBirth     = new DatePicker();
    TextField   street          = new TextField();
    TextField   streetnumber    = new TextField();
    TextField   postalcode      = new TextField();
    TextField   city            = new TextField();
    TextField   country         = new TextField();
    TextField   skills          = new TextField();
    TextField   email           = new TextField();
    TextField   phone           = new TextField();
    TextField   interests       = new TextField();
    TextField   website         = new TextField();
    TextField   description     = new TextField();

    Div         div           = new Div();

    @Override
    public void setParameter(BeforeEvent event,
                             String parameter) {
        if (!Utils.StringIsEmptyOrNull(parameter)) {
            checkIfUserIsLoggedIn();
            profileDTO = profileControl.loadProfileDataById(Integer.parseInt(parameter));
            studentId  = profileDTO.getId();

            existingAddresses   = addressRepository.getByIdAfter(0);
            boolean ownership = checkIfUserIsProfileOwner();

            if(ownership) {
                // Skip ID so one can be generated.
                addr.setStreet(profileDTO.getAddress().getStreet());
                addr.setHouseNumber(profileDTO.getAddress().getHouseNumber());
                addr.setPostalCode(profileDTO.getAddress().getPostalCode());
                addr.setCity(profileDTO.getAddress().getCity());
                addr.setCountry(profileDTO.getAddress().getCountry());

                initLabels(profileDTO);
                createProfileEditView();
            }
        }
    }

    // Used to read DTO data and inject them into the labels
    public void initLabels(StudentUserDTO profileDTO) {
        studentId = profileDTO.getId();
        salutation.setPlaceholder(profileDTO.getSalutation());
        title.setPlaceholder(profileDTO.getTitle());
        firstName.setPlaceholder(profileDTO.getFirstName());
        lastName.setPlaceholder(profileDTO.getLastName());
        graduation.setPlaceholder(profileDTO.getGraduation());
        dateOfBirth.setValue(profileDTO.getDateOfBirth());
        //dateOfBirth.setPlaceholder(profileDTO.getDateOfBirth().toString());
        street.setPlaceholder(addr.getStreet());
        streetnumber.setPlaceholder(addr.getHouseNumber());
        postalcode.setPlaceholder(addr.getPostalCode());
        city.setPlaceholder(addr.getCity());
        country.setPlaceholder(addr.getCountry());
        skills.setPlaceholder(profileDTO.getSkills());
        email.setPlaceholder(profileDTO.getEmail());
        phone.setPlaceholder(profileDTO.getPhone());
        interests.setPlaceholder(profileDTO.getInterests());
        website.setPlaceholder(profileDTO.getWebsite());
        description.setPlaceholder(profileDTO.getDescription());
    }

    // Build profile content
    public void createProfileEditView() {
        H2 h2 = new H2("Editiere mein Profil");

        // TODO: Get Image from Database
        // Profile Image
        Image profileImage = new Image("https://thispersondoesnotexist.com/image", "placeholder");
        profileImage.setWidth("200px");
        profileImage.getElement().getStyle().set("border", "1px solid black");

        // Styling
        for (Label label : new Label[]{ lsalutation, ltitle, lfirstname, llastname, lgraduation, ldateOfBirth, lstreet,
                lstreetnumber, lpostalcode, lcity, lcountry, lskills, lemail, lphone, linterests, lwebsite,
                ldescription}) {
            label.getElement().getStyle().set("font-weight", "bold");
            label.getElement().getStyle().set("width", "200px");        // For alignment
        }

        for (TextField textfield : new TextField[]{ salutation, title, firstName, lastName, graduation, street,
                streetnumber, postalcode, city, country, skills, email, phone, interests, website, description }) {
            textfield.getElement().getStyle().set("height", "20px");
            textfield.getElement().getStyle().set("width", "300px");
        }

        // Profile Data
        HorizontalLayout hsalutation    = new HorizontalLayout(lsalutation, salutation);
        HorizontalLayout htitle         = new HorizontalLayout(ltitle, title);
        HorizontalLayout hfirstname     = new HorizontalLayout(lfirstname, firstName);
        HorizontalLayout hlastname      = new HorizontalLayout(llastname, lastName);
        HorizontalLayout hoccupation    = new HorizontalLayout(lgraduation, graduation);
        HorizontalLayout hbirthdate     = new HorizontalLayout(ldateOfBirth, dateOfBirth);
        HorizontalLayout hstreet        = new HorizontalLayout(lstreet, street);
        HorizontalLayout hstreetnumber  = new HorizontalLayout(lstreetnumber, streetnumber);
        HorizontalLayout hpostalcode    = new HorizontalLayout(lpostalcode, postalcode);
        HorizontalLayout hcity          = new HorizontalLayout(lcity, city);
        HorizontalLayout hcountry       = new HorizontalLayout(lcountry, country);
        HorizontalLayout hskills        = new HorizontalLayout(lskills, skills);
        HorizontalLayout hemail         = new HorizontalLayout(lemail, email);
        HorizontalLayout hnumber        = new HorizontalLayout(lphone, phone);
        HorizontalLayout hinterests     = new HorizontalLayout(linterests, interests);
        HorizontalLayout hwebsite       = new HorizontalLayout(lwebsite, website);
        HorizontalLayout haboutme       = new HorizontalLayout(ldescription, description);

        // Create Save and Cancel Buttons
        HorizontalLayout hbuttons = new HorizontalLayout();
        Button saveButton         = new Button("Speichern");
        Button cancelButton       = new Button("Abbrechen");

        saveButton.addClickListener(e -> {
                updateProfileData();
                UI.getCurrent().navigate(Globals.Pages.PROFILE_VIEW + studentId);
        });
        cancelButton.addClickListener(e -> UI.getCurrent().navigate(Globals.Pages.PROFILE_VIEW +
                studentId));
        hbuttons.add(saveButton, cancelButton);

        // Alignment of profile information
        for (HorizontalLayout HL : new HorizontalLayout[]{ hsalutation, htitle, hfirstname, hlastname, hoccupation,
                hbirthdate, hstreet, hstreetnumber, hpostalcode, hcity, hcountry, hskills, hemail, hnumber, hinterests,
                hwebsite, haboutme, hbuttons }) {
            HL.getElement().getStyle().set("margin-top", "10px");
        }

        // Append everything to the site
        div.add(h2, profileImage, hsalutation, htitle, hfirstname, hlastname, hoccupation, hbirthdate, hstreet,
                hstreetnumber, hpostalcode, hcity, hcountry, hskills, hemail, hnumber, hinterests, hwebsite, haboutme,
                hbuttons);
        add(div);
    }

    // Used to save modified data in the database
    public void updateProfileData() {

        // Only modify data if Textfields are not empty. Otherwise, use previous data.
        StudentUser updatedProfile = new StudentUser();

        // ID
        updatedProfile.setId(studentId);

        // Salutation
        if(!Objects.equals(salutation.getValue(), ""))
            updatedProfile.setSalutation(salutation.getValue());
        else
            updatedProfile.setSalutation(salutation.getPlaceholder());

        // Title
        if(!Objects.equals(title.getValue(), ""))
            updatedProfile.setTitle(title.getValue());
        else
            updatedProfile.setTitle(title.getPlaceholder());

        // First Name
        if(!Objects.equals(firstName.getValue(), ""))
            updatedProfile.setFirstName(firstName.getValue());
        else
            updatedProfile.setFirstName(firstName.getPlaceholder());

        // Last Name
        if(!Objects.equals(lastName.getValue(), ""))
            updatedProfile.setLastName(lastName.getValue());
        else
            updatedProfile.setLastName(lastName.getPlaceholder());

        // Graduation
        if(!Objects.equals(graduation.getValue(), ""))
            updatedProfile.setGraduation(graduation.getValue());
        else
            updatedProfile.setGraduation(graduation.getPlaceholder());

        // Date of Birth
        if(Objects.equals(dateOfBirth.getValue().toString(), ""))
            updatedProfile.setDateOfBirth(dateOfBirth.getValue());
        else
            updatedProfile.setDateOfBirth(profileDTO.getDateOfBirth());

        // Address
        // Street
        if(!Objects.equals(street.getValue(), ""))
            addr.setStreet(street.getValue());
        else
            addr.setStreet(street.getPlaceholder());

        // Street Number
        if(!Objects.equals(streetnumber.getValue(), ""))
            addr.setHouseNumber(streetnumber.getValue());
        else
            addr.setHouseNumber(streetnumber.getPlaceholder());

        // Postalcode
        if(!Objects.equals(postalcode.getValue(), ""))
            addr.setPostalCode(postalcode.getValue());
        else
            addr.setPostalCode(postalcode.getPlaceholder());

        // City
        if(!Objects.equals(city.getValue(), ""))
            addr.setCity(city.getValue());
        else
            addr.setCity(city.getPlaceholder());

        // Country
        if(!Objects.equals(country.getValue(), ""))
            addr.setCountry(country.getValue());
        else
            addr.setCountry(country.getPlaceholder());

        // Check DB for existing address
        int newAddressID = checkAddressExistence(addr, existingAddresses);
        if(newAddressID != -1) {
            addr = addressRepository.getById(newAddressID);
        } else {
            addressRepository.save(addr);
        }
        updatedProfile.setAddress(addr);

        // Skills
        if(!Objects.equals(skills.getValue(), ""))
            updatedProfile.setSkills(skills.getValue());
        else
            updatedProfile.setSkills(skills.getPlaceholder());

        // Email
        if(!Objects.equals(email.getValue(), ""))
            updatedProfile.setEmail(email.getValue());
        else
            updatedProfile.setEmail(email.getPlaceholder());

        // Phone
        if(!Objects.equals(phone.getValue(), ""))
            updatedProfile.setPhone(phone.getValue());
        else
            updatedProfile.setPhone(phone.getPlaceholder());

        // Interests
        if(!Objects.equals(interests.getValue(), ""))
            updatedProfile.setInterests(interests.getValue());
        else
            updatedProfile.setInterests(interests.getPlaceholder());

        // Website
        if(!Objects.equals(website.getValue(), ""))
            updatedProfile.setWebsite(website.getValue());
        else
            updatedProfile.setWebsite(website.getPlaceholder());

        // Description
        if(!Objects.equals(description.getValue(), ""))
            updatedProfile.setDescription(description.getValue());
        else
            updatedProfile.setDescription(description.getPlaceholder());

        // Password
        updatedProfile.setPassword(profileDTO.getPassword());

        // Type
        updatedProfile.setType("st");

        // Save in DB
        studentUserRepository.save(updatedProfile);
    }

    /*  We have to check if the address we edited in the View already exists in the DB.
        Return True  if it exists
        Return False if it does not exist
    */
    public int checkAddressExistence(Address a, List<Address> addresses) {
        int ID = -1;

        for(Address b : addresses) {
            if(Objects.equals(a.getStreet(), b.getStreet()) &&
                    Objects.equals(a.getHouseNumber(), b.getHouseNumber()) &&
                    Objects.equals(a.getPostalCode(), b.getPostalCode()) &&
                    Objects.equals(a.getCity(), b.getCity()) &&
                    Objects.equals(a.getCountry(), b.getCountry())) {
                ID = b.getId();
                break;
            }
        }
        return ID;
    }

    public StudentUserDTOImpl createStudentUserDTOImpl() {

        StudentUserDTOImpl studentUserDTO = new StudentUserDTOImpl();
        studentUserDTO.setId(profileDTO.getId());
        studentUserDTO.setSalutation(profileDTO.getSalutation());
        studentUserDTO.setTitle(profileDTO.getTitle());
        studentUserDTO.setFirstname(firstName.getValue());
        studentUserDTO.setLastname(lastName.getValue());
        studentUserDTO.setGraduation(graduation.getValue());
        studentUserDTO.setDateOfBirth(dateOfBirth.getValue());
        studentUserDTO.setAddress(profileDTO.getAddress());
        studentUserDTO.setSkills(skills.getValue());
        studentUserDTO.setEmail(email.getValue());
        studentUserDTO.setPassword(profileDTO.getPassword());
        studentUserDTO.setPhone(phone.getValue());
        studentUserDTO.setInterests(interests.getValue());
        studentUserDTO.setWebsite(website.getValue());
        studentUserDTO.setDescription(description.getValue());
        studentUserDTO.setType("st");

        return studentUserDTO;
    }

    public boolean checkForEmptyInput() {
        return checkForEmptyTextField(firstName) ||
                checkForEmptyTextField(lastName) ||
                checkForEmptyTextField(email) ||
                checkForEmptyTextField(phone);
    }

    public boolean checkForEmptyTextField(TextField textField) {
        boolean empty = Utils.StringIsEmptyOrNull(textField.getValue());
        if (empty) {
            textField.setInvalid(true);
            textField.setErrorMessage("Bitte gib einen Wert ein");
        } else {
            textField.setInvalid(false);
        }
        return empty;
    }

    private void checkIfUserIsLoggedIn() {
        // Falls der Benutzer nicht eingeloggt ist, dann wird er auf die Startseite gelenkt
        UserDTO userDTO = this.getCurrentUser();
        if (userDTO == null) {
            UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
        }
    }

    // If the user is not the owner of this profile, they get redirected to the profile
    private boolean checkIfUserIsProfileOwner() {
        int userId = this.getCurrentUser().getId();

        if(userId != studentId)
        {
            UI.getCurrent().navigate(Globals.Pages.PROFILE_VIEW + studentId);
            UI.getCurrent().getPage().reload();
            return false;
        }
        else
            return true;

    }

    private UserDTO getCurrentUser() {
        return (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }

    @Override
    /**
     * Methode wird vor der eigentlichen Darstellung der UI-Components aufgerufen.
     * Hier kann man die finale Darstellung noch abbrechen, wenn z.B. der Nutzer nicht eingeloggt ist
     * Dann erfolgt hier ein ReDirect auf die Login-Seite. Eine Navigation (Methode navigate)
     * ist hier nicht möglich, da die finale Navigation noch nicht stattgefunden hat.
     * Diese Methode in der AppLayout sichert auch den un-authorisierten Zugriff auf die innerliegenden
     * Views (hier: ShowCarsView und EnterCarView) ab.
     *
     */
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (getCurrentUser() == null){
            beforeEnterEvent.rerouteTo(Globals.Pages.LOGIN_VIEW);
        }
    }

    public StudentProfileEditView() {

    }
}
