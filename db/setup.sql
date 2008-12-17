-- *************************************************************************
-- Drop Table
-- *************************************************************************
DROP TABLE IF EXISTS Activity;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Sites;
DROP TABLE IF EXISTS ActivityCode;


-- *************************************************************************
-- ActivityCode table setup
-- *************************************************************************
CREATE TABLE ActivityCode
(
	code INTEGER NOT NULL PRIMARY KEY,
	shortlabel VARCHAR(30) NOT NULL,
	longlabel VARCHAR(80)
) ENGINE = InnoDB;

INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(0,'LOGIN','User login');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(1,'AUTH_FAILURE','Failed login attempt');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(2,'ACTIVATE_USER','User account activated');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(3,'DEACTIVATE_USER','User account deactivated');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(4,'ADD_USER','New user added');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(5,'EDIT_USER','User data edited');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(6,'ADD_SITE','New site added');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(7,'EDIT_SITE','Site data edited');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(8,'PASSWORD_RESET','User password reset');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(9,'PASSWORD_CHANGE','User password changed');
INSERT INTO ActivityCode(code,shortlabel,longlabel) VALUES(10,'LOCKOUT','User account locked out');

-- *************************************************************************
-- Site table setup
-- *************************************************************************
CREATE TABLE Sites
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	domain VARCHAR(64) NOT NULL,
	name VARCHAR(128),
	primarycontact VARCHAR(128),
	contactphone VARCHAR(64),
	contactemail VARCHAR(128),
	active BOOL NOT NULL,
	locked BOOL NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB;


-- *************************************************************************
-- User table setup
-- *************************************************************************
CREATE TABLE Users
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	siteid INTEGER NOT NULL,
	loginid VARCHAR(32) NOT NULL,
	name VARCHAR(128) NOT NULL,
	emailaddress VARCHAR(128),
	password VARCHAR(64),
	pwdchangedt DATE,
	previouspwd VARCHAR(64),
	lastlogin DATETIME,
	failedattempts TINYINT NOT NULL DEFAULT 0,
	lockeddttm DATETIME,
	resetcode varchar(8),
	adminuser BOOL NOT NULL,
	active BOOL NOT NULL,
	locked BOOL NOT NULL,
	PRIMARY KEY (id),
	KEY (siteid)
) ENGINE=InnoDB;

ALTER TABLE Users ADD CONSTRAINT FK_Users_Sites
	FOREIGN KEY (siteid) REFERENCES Sites (id);


-- *************************************************************************
-- Activity table setup
-- *************************************************************************
CREATE TABLE Activity
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	affecteduser INTEGER,
	affectedsite INTEGER,
	performedby INTEGER NOT NULL,
	ipaddress VARCHAR(20) NOT NULL,
	activitycode INTEGER NOT NULL,
	message VARCHAR(255),
	activitydttm DATETIME NOT NULL,
	PRIMARY KEY (id),
	KEY (activitycode),
	KEY (affecteduser),
	KEY (performedby)
) ENGINE = InnoDB;

ALTER TABLE Activity ADD CONSTRAINT FK_Activity_ActivityCode
	FOREIGN KEY (activitycode) REFERENCES ActivityCode (code);

ALTER TABLE Activity ADD CONSTRAINT FK_Activity_AffectedUser
	FOREIGN KEY (affecteduser) REFERENCES Users (id);

ALTER TABLE Activity ADD CONSTRAINT FK_Activity_AffectedSite
	FOREIGN KEY (affectedsite) REFERENCES Sites (id);

ALTER TABLE Activity ADD CONSTRAINT FK_Activity_PerformedBy
	FOREIGN KEY (performedby) REFERENCES Users (id);

