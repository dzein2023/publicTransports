

-- Create City table
CREATE TABLE City (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);

-- Create PublicTransports table
CREATE TABLE PublicTransports (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES City(id)
);

-- Create Route table
CREATE TABLE Route (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES City(id)
);

-- Create StopStation table
CREATE TABLE StopStation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES City(id)
);


