package org.hbrs.se2.project.coll.dtos;

import com.vaadin.flow.component.html.Label;
import org.hbrs.se2.project.coll.dtos.impl.StellenausschreibungDTO;

import javax.persistence.OneToMany;
import java.util.List;

public interface CompanyProfileDTO {

    public int      getID();

    public String   getCompanyName();
    public String   getAddress();
    public String   getTelephone();
    public String   getEmail();
    public String   getWebsite();
    public String   getDescription();

    @OneToMany
    public List<StellenausschreibungDTO> getOffers();

}
