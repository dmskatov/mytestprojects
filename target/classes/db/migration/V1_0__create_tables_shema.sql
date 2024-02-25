CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    full_name VARCHAR(150)
);

CREATE TABLE IF NOT EXISTS transporters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS itineraries (
    id SERIAL PRIMARY KEY,
    departure VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    duration INT NOT NULL
);

CREATE TABLE itineraries_transporters (
    id SERIAL PRIMARY KEY,
    itinerary_id INT NOT NULL,
    transporter_id INT NOT NULL,
    FOREIGN KEY (itinerary_id) REFERENCES itineraries (id),
    FOREIGN KEY (transporter_id) REFERENCES transporters (id)
);

CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    time VARCHAR(50) NOT NULL,
    seat_number INT NOT NULL,
    price DECIMAL NOT NULL,
    user_id INT,
    itinerary_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (itinerary_id) REFERENCES itineraries (id)
);