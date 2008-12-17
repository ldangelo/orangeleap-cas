-- *************************************************************************
-- Truncate tables (other than ActivityCode)
-- *************************************************************************

TRUNCATE TABLE Activity;
TRUNCATE TABLE Users;
TRUNCATE TABLE Sites;

-- *************************************************************************
-- Create two sites. MPower and AlphaOmega
-- *************************************************************************

INSERT Sites(domain,name,primarycontact,active,locked) VALUES('mpower','MPower Open','Orange Man',true, false);
INSERT Sites(domain,name,primarycontact,active,locked) VALUES('alphaomega','AlphaOmega, LLC','Adam Smith',true, false);
INSERT Sites(domain,name,primarycontact,active,locked) VALUES('openup','Open Horizons','Larry Bunt',true, false);


-- *************************************************************************
-- Add some users
-- *************************************************************************

INSERT Users(siteid,loginid,name,adminuser,active,locked) VALUES(1,'bsmith','Barney Smith',false,true,false);
INSERT Users(siteid,loginid,name,adminuser,active,locked) VALUES(1,'lsmittle','Larry Smittle',false,true,false);
INSERT Users(siteid,loginid,name,adminuser,active,locked) VALUES(1,'ksmile','Karen Smile',false,true,false);
INSERT Users(siteid,loginid,name,adminuser,active,locked) VALUES(1,'dduncan','David Duncan',false,true,false);

