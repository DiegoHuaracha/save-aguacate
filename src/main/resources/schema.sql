drop table if exists permiso_perfil;
drop table if exists usuario;
drop table if exists perfil;
drop table if exists permiso;

create table perfil(
  clave smallint not null auto_increment
  , nombre varchar(20) not null
  , primary key(clave)
);

create table permiso(
  clave smallint not null auto_increment
  , nombre varchar(255) not null
  , claveapp varchar(10) not null
  , primary key(clave)
);

create table permiso_perfil(
  clave_permiso smallint not null
  , clave_perfil smallint not null
  , foreign key(clave_permiso) references permiso(clave)
  , foreign key(clave_perfil) references perfil(clave)
);

create table usuario(
  clave smallint not null auto_increment
  , contrasennia varchar(150) not null
  , clave_perfil smallint not null
  , nombre varchar(150) not null
  , apellidoPaterno varchar(150) not null
  , apellidoMaterno varchar(150) not null
  , fechaAlta date not null
  , activo smallint not null default 1
  , fechaUltimoAcceso date
  , primary key(clave)
  , foreign key(clave_perfil) references perfil(clave)
);