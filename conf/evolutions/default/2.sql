# --- Sample dataset

# --- !Ups

insert into public."User" (id,name,email,pw,role) values (  1,'Admin','admin@admin.com', 'senha.123', 1);
insert into public."User" (id,name,email,pw,role) values (  2,'Phil','phil@ufc.br', 'senha.123', 2);
insert into public."User" (id,name,email,pw,role) values (  3,'Paulo','paulo@ufc.br', 'senha.123', 2);

# --- !Downs

delete from public."User";