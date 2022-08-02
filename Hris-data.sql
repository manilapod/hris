
INSERT INTO EMPLOYEE(BENEFIT_ID, FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE, JOB_ROLE, ADDRESS, TELEPHONE_NUMBER, STATE, IN_TRAINING, PERFORMANCE, STARTED_DATE, LAST_UPDATE)
VALUES(7,'NICK','WAHLBERG','masiv80806@teasya.com','2001-07-17','Database Analyst','3899 Star Trek Drive Pensacola','12182973441','FL',FALSE,'8','2012-04-15','04:34:33'),
(5,'ED','CHASE','picipo3704@satedly.com','2002-06-1','Human Resources','3717 Stiles Street Pittsburgh','14244351204','PA',TRUE,'5','2022-04-17','04:34:33'),
(8,'JENNIFER','DAVIS','neberox405@satedly.com','1998-02-15','Analyst','1912 Fowler Avenue Newnan','18333552154','GA',FALSE,'6','2017-04-1','06:25:30'),
(6,'JOHNNY','LOLLOBRIGIDA','xanofi7677@storypo.com','1991-02-21','SDET Intern','2090 Maxwell Street Enfield','18136865209','CT',TRUE,'3','2014-7-25','07:46:45'),
(2,'BETTE','NICHOLSON','catid64240@satedly.com','1895-10-5','Manual Testing','3711 Masonic Hill Road Little Rock','13612194508','AR',TRUE,'5','2010-2-23','08:56:25'),
(3,'GRACE','MOSTEL','canir47468@satedly.com','1990-11-12','Software Developer','4482 Mapleview Drive Memphis','19146440309','TN',TRUE,'9.8','2016-2-26','09:34:35'),
(4,'MATTHEW','JOHANSSON','tidim41401@satedly.com','1904-4-5','Cyber Security Engineer','1793 Horner Street Akron','19253095296','OH',FALSE,'6.7','2014-5-6','10:34:11'),
(1,'JOE','SWANK','yanir19873@tebyy.com','1999-12-1','Penetration Testing','4766 Norman Street Long Beach','18333220089','CA',FALSE,'8.7','2017-12-9','03:34:75');

INSERT INTO BENEFIT(EMPLOYEE_ID, RETIREMENT_ID, INSURANCE_ID, LIFE_INSURANCE, HEALTH_INSURANCE, RETIREMENT_PLANS, TUITION_ASSISTANCE) 
VALUES(7,4,1,'Term life insurance', 'EPO', '401k','Prepaid Tuition Plan'),
(5,2,3,'Whole life insurance','PPO','GIA','Tuition Guarantee Plan'),
(7,2,1,'Universal life insurance','HMO','NQDC','Prepaid Tuition Plan'),
(6,3,3,'Variable life insurance','POS','Defined contribution plans','Tuition Guarantee Plan'),
(2,4,8,'Indexed universal life insurance','HMO','IRA plans','Prepaid Tuition Plan'),
(3,1,2,'Simplified issue life insurance','PPO','Cash-balance plans','Tuition Payment Plan'),
(4,3,3,'Guaranteed issue life insurance','EPO','Cash-value life insurance plan','Tuition Guarantee Plan'),
(1,3,2,'Group life insurance', 'POS', 'Traditional pensions','Tuition Payment Plan');



INSERT INTO INSURANCE(BENEFIT_ID, LIFE_PREM, HEALTH_PREM) 
VALUES(2,'5.4','3.6'),
(4,'6.7','2.4'),
(4,'3.2','6.4'),
(5,'5.7','6.3');



INSERT INTO PAYROLL(EMPLOYEE_ID, HOURLY_RATE, GROSS_SALARY, SALARY, YEARLY_BONUS, STATE_TAX, FEDERAL_TAX, EMPLOYMENT_TYPE, WEEKLY_HOURS, PAY_CYCLE, EXTRA_HOURS, EFFECTIVE) 
VALUES(1,'13.5','115.000','98.990','10','5.75','12','M','8','2 week', '5', 'yes'),
(4,'11.5','100.000','80.670','10','4.39','12','F','5','2 week', '5', 'yes'),
(2,'20.0','140.000','95.890','10','7.75','12','F','9','2 week', '5', 'no'),
(3,'17.0','121.000','100.000','10','1.25','12','M','21','2 week', '5', 'yes'),
(7,'16.0','113.000','75.750','10','3.34','12','F','6','2 week', '5', 'yes'),
(5,'11.0','97.000','85.000','10','5.55','12','M','7','2 week', '5', 'no'),
(8,'24.0','85.000','75.00','10','2.65','12','M','13','2 week', '5', 'yes'),
(6,'22.0','112.000','89.000','10','1.85','12','F','24','2 week', '5', 'no'),
(9,'33.0','154.000','121.000','10','5.75','12','M','10','2 week', '5', 'yes');

INSERT INTO PTE(TRAINING_ID, FIRST_NAME, LAST_NAME, EMAIL, ZIP_CODE, POSITION, DATA_APPLIED, APP_STATUS, LAST_UPDATE) 
VALUES(1,'KEVIN','GARLAND','xanofi7677@storypo.com','03820','SOC Analyst','2015-2-25','NOT LIKEY','2015-3-15'), 
(2,'CATE','MCQUEEN','vacitor968@tebyy.com','11967','Social Services','2016-5-5','LIKEY','2016-6-25'),
(3,'DARYL','CRAWFORD','figoki4968@tebyy.com',' 55303','Cloud Analyst','2019-1-6','MABYE','2019-2-13'),
(4,'GRETA','KEITEL','jodori6277@teasya.com','45103','Cloud Security Engineer','2018-7-26','LIKEY','2018-8-20'),
(5,'ADAM','HOPPER','ravoco2905@storypo.com',' 46383 ','Offensive Security','2017-8-13','LIKEY','2017-9-10');

INSERT INTO TAX(PAYROLL_ID, STATE_PREM, FEDERAL_PREM) 
VALUES(5,'1.75','6.25'),
(2,'2.75','3.25'),
(7,'4.75','5.25'),
(6,'1.75','6.25'),
(8,'5.75','4.25'),
(9,'1.75','7.25'),
(3,'6.75','2.25'),
(1,'1.75','7.25'),
(4,'3.75','1.25');

INSERT INTO RETIREMENT(BENEFIT_ID, IRA_AMOUNT, PTB_AMOUNT, TSA_AMOUNT)
VALUES(2, '50.00','20.50','20.50'),
(4, '50.00','20.50','20.50'),
(6, '50.00','20.50','20.50'),
(8, '50.00','20.50','20.50'),
(1, '50.00','20.50','20.50'),
(3, '50.00','20.50','20.50'),
(2, '50.00','20.50','20.50'),
(5, '50.00','20.50','20.50'),
(9, '50.00','20.50','20.50');

INSERT INTO TRAINING(EMPLOYEE_ID, TRAINEE_NAME, COMPLETED) 
VALUES(10,'KEVIN GARLAND','NO'),
(11,'CATE MCQUEEN','NO'),
(12,'DARYL CRAWFORD','NO'),
(13,'GRETA KEITEL','YES'),
(14,'ADAM HOPPER','YES');