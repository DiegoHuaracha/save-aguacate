insert into perfil(clave, nombre) values(1, "Administrador");
insert into perfil(clave, nombre) values(2, "Operador");
insert into perfil(clave, nombre) values(3, "Consultor");

insert into permiso(clave, nombre, claveapp) values(1, "Registrar usuario", "USU_REG");
insert into permiso(clave, nombre, claveapp) values(2, "Consultar usuario", "USU_SEL");
insert into permiso(clave, nombre, claveapp) values(3, "Actualizar usuario", "USU_UPD");
insert into permiso(clave, nombre, claveapp) values(4, "Borrar usuario", "USU_DEL");

insert into permiso_perfil(clave_perfil, clave_permiso) values(1, 1);
insert into permiso_perfil(clave_perfil, clave_permiso) values(1, 2);
insert into permiso_perfil(clave_perfil, clave_permiso) values(1, 3);

insert into usuario(contrasennia, clave_perfil, nombre, apellidoPaterno, apellidoMaterno, fechaAlta)
values("81be87ab6f2492f7da321311ef41c411", 1, "Emmanuel", "Delgado", "Mejía", now());

insert into usuario(contrasennia, clave_perfil, nombre, apellidoPaterno, apellidoMaterno, fechaAlta)
values("b2eddeda3a86db5fef302d70a6449aa9", 1, "Diego", "Huaracha", "Gasca", now());
