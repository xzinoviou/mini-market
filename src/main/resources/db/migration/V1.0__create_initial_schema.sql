CREATE TABLE `privilege`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `privilege_type` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `role_type` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `roles_privileges`
(
    `role_id`      bigint(20) NOT NULL,
    `privilege_id` bigint(20) NOT NULL,
    KEY            `PRIVILEGE` (`privilege_id`),
    KEY            `FK_ROLES_PRIVILEGES_ROLE` (`role_id`),
    CONSTRAINT `FK_ROLES_PRIVILEGES_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    CONSTRAINT `PRIVILEGE` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `birth_date`    datetime(6) DEFAULT NULL,
    `creation_date` datetime(6) DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `enabled`       bit(1)       DEFAULT NULL,
    `expired`       bit(1)       DEFAULT NULL,
    `first_name`    varchar(255) DEFAULT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `locked`        bit(1)       DEFAULT NULL,
    `pass`          varchar(255) DEFAULT NULL,
    `token_expired` bit(1)       DEFAULT NULL,
    `update_date`   datetime(6) DEFAULT NULL,
    `username`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
    UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users_roles`
(
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    KEY       `FK_USERS_ROLES_ROLE` (`role_id`),
    KEY       `FK_USERS_ROLES_USER` (`user_id`),
    CONSTRAINT `FK_USERS_ROLES_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    CONSTRAINT `FK_USERS_ROLES_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
