CREATE TABLE IF NOT EXISTS personal_data (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    class_name VARCHAR(255) NOT NULL,
    class_teacher VARCHAR(255) NOT NULL,
    graduation_period VARCHAR(255) NOT NULL,
    graduation_year INTEGER NOT NULL,
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT graduation_year_check CHECK (graduation_year >= 2024)
);
