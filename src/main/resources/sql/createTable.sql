CREATE TABLE Users (
   userId serial PRIMARY KEY NOT NULL,
   firstname varchar (30),
   lastname varchar (30),
   email varchar (30),
   createdDate date NOT NULL,
   updatedDate date NOT NULL
   )


CREATE TABLE Status (
   statusId serial PRIMARY KEY NOT NULL,
   status varchar (50),
   isActive boolean,
   updatedBy varchar (60),
   updateDate date NOT NULL
   createdBy varchar (60),
   createdDate date NOT NULL,
)

CREATE TABLE Reason (
   reasonId serial PRIMARY KEY NOT NULL,
   reason varchar (50),
   isActive boolean,
   createdBy varchar (60),
   createdDate date NOT NULL,
   updatedBy varchar (60),
   UpdatedDate date NOT NULL
)


CREATE TABLE TODO (

  todoId serial PRIMARY KEY NOT NULL,
  projectName varchar (30),
  projectOwner varchar (60),
  statusId integer references projectstatus (statusid),
  reasonId integer references projectreason (reasonid),
  description text,
  completionDate date NOT NULL,
  createdDate date NOT NULL, 
  updatedDate date NOT NULL,
  createdBy varchar(60) NOT NULL,
  updatedBy varchar(60) NOT NULL
)

