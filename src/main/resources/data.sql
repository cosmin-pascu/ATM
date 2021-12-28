INSERT INTO banknote (currency, type, quantity) VALUES ('RON', 'ONE', 100);
INSERT INTO banknote (currency, type, quantity) VALUES ('RON', 'FIVE', 100);
INSERT INTO banknote (currency, type, quantity) VALUES ('RON', 'TEN', 100);
INSERT INTO banknote (currency, type, quantity) VALUES ('RON', 'FIFTY', 50);
INSERT INTO banknote (currency, type, quantity) VALUES ('RON', 'ONE_HUNDRED', 50);

INSERT INTO user (first_name, last_name, username, password, email, user_type, funds)
    VALUES ('Cosmin', 'Demo', 'my_username', 'parola', 'eu@mail.com', 'ADMIN', 0);