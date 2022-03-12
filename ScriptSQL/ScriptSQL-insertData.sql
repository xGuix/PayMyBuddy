/* Create users for test */
INSERT INTO user (`firstname`,`lastname`,`city`,`email`,`password`,`balance`) VALUES ('Guix','Debrens','Orion','gd@paymybuddy.com','Admin',1500);
INSERT INTO user (`firstname`,`lastname`,`city`,`email`,`password`,`balance`) VALUES ('Bob','Lazar','Nevada','bl@zone51.com','Zone51',100);
INSERT INTO user (`firstname`,`lastname`,`city`,`email`,`password`,`balance`) VALUES ('Bel','Zebuth','Hell','bz@welcomtohell.com','Satan',100);
INSERT INTO user (`firstname`,`lastname`,`city`,`email`,`password`,`balance`) VALUES ('Jesus','Christ','Heaven','jc@welcometoparadise.com','Godfather',100);
INSERT INTO user (`firstname`,`lastname`,`city`,`email`,`password`,`balance`) VALUES ('Bob','Marley','Kingstone','bm@raggamusic.com','Zion',100);

/* Create users friends list */
INSERT INTO connection (user_id,connection_id) VALUES(1,2);
INSERT INTO connection (user_id,connection_id) VALUES(1,3);
INSERT INTO connection (user_id,connection_id) VALUES(1,4);
INSERT INTO connection (user_id,connection_id) VALUES(2,1);
INSERT INTO connection (user_id,connection_id) VALUES(3,1);
INSERT INTO connection (user_id,connection_id) VALUES(4,1);

/* Create users bankaccount */
INSERT INTO bankaccount(iban_account,bank_name,user_id) VALUES("FR7630001007941234567890185",'Banque de France',1);
INSERT INTO bankaccount(iban_account,bank_name,user_id) VALUES("FR7630004000031234567890143",'BNP Paribas',2);
INSERT INTO bankaccount(iban_account,bank_name,user_id) VALUES("FR7630056009271234567890182",'HSBC',3);
INSERT INTO bankaccount(iban_account,bank_name,user_id) VALUES("FR7630002032531234567890168",'Crédit Lyonnais',4);

/* Create users transaction */
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,1000.00,"Gift of gods dude",50.0,1,2);
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,1000.00,"Gift of gods dude",50.0,1,3);
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,1000.00,"Gift of gods dude",50.0,1,4);
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,100.00,"I said thank you god",5.0,2,1);
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,100.00,"I said thank you god",5.0,3,1);
INSERT INTO transaction(datetime,amount,description,transactionfee,receiver_id,sender_id) VALUES(CURRENT_TIMESTAMP,100.00,"I said thank you god",5.0,4,1);