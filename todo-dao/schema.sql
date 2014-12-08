CREATE TABLE Todos2
(
  TodoId serial NOT NULL,
  Text text,
  StatusId bigint,
  CONSTRAINT PK_Todos_Todos_Id PRIMARY KEY (TodoId)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Todos2
  OWNER TO postgres;