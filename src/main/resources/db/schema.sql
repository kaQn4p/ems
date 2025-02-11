CREATE TABLE personal_data (
    username VARCHAR(255) PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    class_name VARCHAR(255) NOT NULL,
    class_teacher VARCHAR(255) NOT NULL,
    graduation_period VARCHAR(255) NOT NULL,
    graduation_year INT NOT NULL CHECK (graduation_year >= 2024),
    company_name VARCHAR(255) NOT NULL,
    contact_person VARCHAR(255) NOT NULL,
    company_email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);