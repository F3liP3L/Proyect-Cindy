

CREATE TABLE cliente (
    codigo   VARCHAR2(50) NOT NULL,
    dni      VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(100) NOT NULL,
    nombre   VARCHAR2(100) NOT NULL,
    correo   VARCHAR2(256) NOT NULL,
    clave    VARCHAR2(50) NOT NULL,
    telefono VARCHAR2(50) NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( codigo );

CREATE TABLE evento (
    codigo         VARCHAR2(50) NOT NULL,
    nombre         VARCHAR2(50) NOT NULL,
    cliente_codigo VARCHAR2(50) NOT NULL,
    salon_codigo   VARCHAR2(50) NOT NULL,
    fecha_reserva  DATE NOT NULL,
    fecha_entrega  DATE NOT NULL,
    estado         CHAR(1) NOT NULL
);

ALTER TABLE evento ADD CONSTRAINT evento_pk PRIMARY KEY ( codigo );

CREATE TABLE salon (
    codigo VARCHAR2(50) NOT NULL,
    nombre VARCHAR2(50) NOT NULL
);

ALTER TABLE salon ADD CONSTRAINT salon_pk PRIMARY KEY ( codigo );

ALTER TABLE evento
    ADD CONSTRAINT evento_cliente_fk FOREIGN KEY ( cliente_codigo )
        REFERENCES cliente ( codigo );

ALTER TABLE evento
    ADD CONSTRAINT evento_salon_fk FOREIGN KEY ( salon_codigo )
        REFERENCES salon ( codigo );
