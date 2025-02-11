package de.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class PersonalData {
	
	
    public static final String STATUS_WAITING_FOR_SIGNATURES = "WAITING_FOR_SIGNATURES";
    public static final String STATUS_CHECKING_INTEREST = "CHECKING_INTEREST";
	
	@Id
	private String username;

	@NotBlank(message = "Name ist erforderlich")
	private String lastName;

	@NotBlank(message = "Vorname ist erforderlich")
	private String firstName;

	@NotBlank(message = "Anschrift ist erforderlich")
	private String address;

	@NotNull(message = "Geburtsdatum ist erforderlich")
	@Past(message = "Geburtsdatum muss in der Vergangenheit liegen")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@NotBlank(message = "E-Mail ist erforderlich")
	@Email(message = "Bitte geben Sie eine gültige E-Mail-Adresse ein")
	private String email;

	@NotBlank(message = "Handynummer ist erforderlich")
	@Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "Bitte geben Sie eine gültige Handynummer ein")
	private String phoneNumber;

	@NotBlank(message = "Klasse ist erforderlich")
	private String className;

	@NotBlank(message = "Klassenlehrer ist erforderlich")
	private String classTeacher;

	@NotNull(message = "Abschlusszeitraum ist erforderlich")
	private String graduationPeriod;

	@NotNull(message = "Abschlussjahr ist erforderlich")
	@Min(value = 2024, message = "Das Abschlussjahr muss 2024 oder später sein")
	private Integer graduationYear;

	@NotBlank(message = "Name des Betriebs ist erforderlich")
	private String companyName;

	@NotBlank(message = "Ansprechpartner ist erforderlich")
	private String contactPerson;

    private String status;
	
	@NotBlank(message = "E-Mail ist erforderlich")
	@Email(message = "Bitte geben Sie eine gültige E-Mail-Adresse ein")
	private String companyEmail;

	@NotBlank(message = "Telefonnummer ist erforderlich")
	private String phone;

	public PersonalData() {

	}
	
	public PersonalData(String username) {
		setUsername(username);
	}

    public String getAddress() {
		return address;
	}

    public String getClassName() {
		return className;
	}

	public String getClassTeacher() {
		return classTeacher;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGraduationPeriod() {
		return graduationPeriod;
	}

	public Integer getGraduationYear() {
		return graduationYear;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getUsername() {
		return username;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGraduationPeriod(String graduationPeriod) {
		this.graduationPeriod = graduationPeriod;
	}

	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
