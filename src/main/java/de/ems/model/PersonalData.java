package de.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Benutzername ist erforderlich")
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

    public PersonalData(Long id) {
    	setId(id);
    }
    
    public PersonalData(String username) {
    	setUsername(username);
    }
    
    public PersonalData() {
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassTeacher() {
		return classTeacher;
	}

	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}

	public String getGraduationPeriod() {
		return graduationPeriod;
	}

	public void setGraduationPeriod(String graduationPeriod) {
		this.graduationPeriod = graduationPeriod;
	}

	public Integer getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    
}
