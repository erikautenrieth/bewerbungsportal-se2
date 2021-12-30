INSERT INTO collhbrs.col_tab_address (postal_code,city,country,street,house_number)  VALUES ( '56075', 'Koblenz' , 'Deutschland' , 'Thielenstrasse ' , 3);
INSERT INTO collhbrs.col_tab_address (postal_code,city,country,street,house_number)  VALUES ( '56075', 'Koblenz' , 'Deutschland' , 'Finkenherd' , 4);
INSERT INTO collhbrs.col_tab_address (postal_code,city,country,street,house_number)  VALUES ( '53757', 'Sankt Augustin' , 'Deutschland' , 'Grantham-Allee' , 20);

INSERT INTO collhbrs.col_tab_company VALUES (40000000, 'Dicker Bubatz', 'www.bubatz.de', '12345', 'Dicker Bubatz', 10000000, 'firma@bubatz.de', '12345');
INSERT INTO collhbrs.col_tab_company VALUES (40000001, 'Tolle superkrasse Testfirma', 'konichkg.de', '10000071', 'Konich KG', 10000001, 'konichkg@gmail.com', '01233211');

INSERT INTO collhbrs.col_tab_user VALUES (20000000, 'Hans', 'Meier', 10000000, '2021-12-01', '$2a$10$Jpr8E5lKZpFtYNU4FZQgzeBM32DKPoi6mB6G/8slHPP3TuybfXEjW', '12345', 'Herr', 'Dr.', 'hans@hbrs.de', 'st');
INSERT INTO collhbrs.col_tab_user VALUES (20000001, 'Kevin', 'Konich', 10000001, '1990-07-05', '$2a$10$mYvXOfsNFbxs3puT2f3zku6/PBjsceQt4vcRLzV1c0s/JsdWYYvTi', '12382318', 'Herr', '-', 'konich@hbrs.de', 'cp');
INSERT INTO collhbrs.col_tab_user VALUES (20000002, 'Admin', 'Bubatz', 10000002, '2021-12-01', '$2a$10$J5UnKRTDtKNfpLbp/QYw2.UVW2zOzeTtyommHOQPcmFpYawBLXBDa', '12345', 'Herr', ' ', 'admin@bubatz.de', 'cp');

INSERT INTO collhbrs.col_tab_contact_person VALUES (20000000, 40000000, 'admin');
INSERT INTO collhbrs.col_tab_contact_person VALUES (20000001, 40000001, 'admin');


INSERT INTO collhbrs.col_tab_job_advertisement VALUES (false, 'Vollzeit', 60, 'Alles', 10000001, '2021-12-01', '2024-01-14', 20000000, 'Du weißt was ich meine', 10, 'Persönliche Assistentin', 29999952);

