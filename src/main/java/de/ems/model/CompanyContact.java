package de.ems.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CompanyContact {
    @NotBlank(message = "Name des Betriebs ist erforderlich")
    private String companyName;

    @NotBlank(message = "Ansprechpartner ist erforderlich")
    private String contactPerson;

    @NotBlank(message = "E-Mail ist erforderlich")
    @Email(message = "Bitte geben Sie eine gültige E-Mail-Adresse ein")
    private String email;

    @NotBlank(message = "Telefonnummer ist erforderlich")
    private String phone;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}
