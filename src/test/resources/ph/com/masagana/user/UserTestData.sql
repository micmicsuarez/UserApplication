
INSERT INTO users (id, date_created, email_address, first_name, last_name, middle_name, password, status, username)
    VALUES(RANDOM_UUID(), current_date, 'sample@emailaddress.com',
        'FirstName', 'LastName', 'MiddleName', 'password', 'UNVERIFIED', 'username');
