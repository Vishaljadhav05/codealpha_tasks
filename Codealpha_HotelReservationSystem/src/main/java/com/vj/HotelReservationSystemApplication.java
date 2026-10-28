package com.vj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationSystemApplication.class, args);
	}

}


// Output:-


/*  DataBase:
 
 => Booking Table
 
 
mysql> select * from booking;
+----+------------+------------+----------------------------+------------------+------------+--------+-----------+--------------+------------+---------+
| id | check_in   | check_out  | created_at                 | guest_email      | guest_name | guests | status    | total_amount | payment_id | room_id |
+----+------------+------------+----------------------------+------------------+------------+--------+-----------+--------------+------------+---------+
|  1 | 2026-10-27 | 2026-10-29 | 2026-10-27 16:55:44.806556 | vishal@gmail.com | Vishal     |      2 | CONFIRMED |      9000.00 |          2 |       6 |
|  2 | 2026-10-28 | 2026-10-31 | 2026-10-27 16:56:58.581574 | arun@gamil.com   | Arun       |      1 | CONFIRMED |      7500.00 |          3 |       2 |
+----+------------+------------+----------------------------+------------------+------------+--------+-----------+--------------+------------+---------+
2 rows in set (0.00 sec)

 => Room Table


mysql> select * from room;
+----+----------+----------+-----------------+-------------+
| id | capacity | category | price_per_night | room_number |
+----+----------+----------+-----------------+-------------+
|  1 |        2 | STANDARD |         2500.00 | 101         |
|  2 |        2 | STANDARD |         2500.00 | 102         |
|  3 |        2 | STANDARD |         2500.00 | 103         |
|  4 |        2 | STANDARD |         2500.00 | 104         |
|  5 |        2 | STANDARD |         2500.00 | 105         |
|  6 |        3 | DELUXE   |         4500.00 | 201         |
|  7 |        3 | DELUXE   |         4500.00 | 202         |
|  8 |        3 | DELUXE   |         4500.00 | 203         |
|  9 |        4 | SUITE    |         8000.00 | 301         |
| 10 |        4 | SUITE    |         8000.00 | 302         |
+----+----------+----------+-----------------+-------------+
10 rows in set (0.01 sec)


=> Payment Table

mysql> select * from payment;
+----+---------+--------+----------------------------+---------+----------------+
| id | amount  | method | paid_at                    | status  | transaction_id |
+----+---------+--------+----------------------------+---------+----------------+
|  1 | 9000.00 | CARD   | 2026-10-27 16:55:58.801612 | FAILED  | TXN-22B36708   |
|  2 | 9000.00 | UPI    | 2026-10-27 16:56:15.844386 | SUCCESS | TXN-77069046   |
|  3 | 7500.00 | CARD   | 2026-10-27 16:57:17.619502 | SUCCESS | TXN-1A4512FA   |
+----+---------+--------+----------------------------+---------+----------------+
3 rows in set (0.00 sec)


 */



/*  Console Output:-
 


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

[32m :: Spring Boot :: [39m              [2m (v4.1.2)[0;39m

[2m2026-10-27T16:54:25.228+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mc.vj.HotelReservationSystemApplication  [0;39m [2m:[0;39m Starting HotelReservationSystemApplication using Java 21.0.12 with PID 8408 (E:\Eclipse Workspace\Code Alpha\HotelReservationSystem\target\classes started by hp in E:\Eclipse Workspace\Code Alpha\HotelReservationSystem)
[2m2026-10-27T16:54:25.230+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mc.vj.HotelReservationSystemApplication  [0;39m [2m:[0;39m No active profile set, falling back to 1 default profile: "default"
[2m2026-10-27T16:54:25.704+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Bootstrapping Spring Data JPA repositories in DEFAULT mode.
[2m2026-10-27T16:54:25.743+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Finished Spring Data repository scanning in 33 ms. Found 2 JPA repository interfaces.
[2m2026-10-27T16:54:26.064+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.s.boot.tomcat.TomcatWebServer         [0;39m [2m:[0;39m Tomcat initialized with port 8080 (http)
[2m2026-10-27T16:54:26.076+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.apache.catalina.core.StandardService  [0;39m [2m:[0;39m Starting service [Tomcat]
[2m2026-10-27T16:54:26.076+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.apache.catalina.core.StandardEngine   [0;39m [2m:[0;39m Starting Servlet engine: [Apache Tomcat/11.0.27]
[2m2026-10-27T16:54:26.240+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36morg.apache.jasper.servlet.TldScanner    [0;39m [2m:[0;39m At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
[2m2026-10-27T16:54:26.247+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mb.w.c.s.WebApplicationContextInitializer[0;39m [2m:[0;39m Root WebApplicationContext: initialization completed in 977 ms
[2m2026-10-27T16:54:26.377+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36morg.hibernate.orm.jpa                   [0;39m [2m:[0;39m HHH008540: Processing PersistenceUnitInfo [name: default]
[2m2026-10-27T16:54:26.439+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36morg.hibernate.orm.core                  [0;39m [2m:[0;39m HHH000001: Hibernate ORM core version 7.4.13.Final
[2m2026-10-27T16:54:26.800+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.s.o.j.p.SpringPersistenceUnitInfo     [0;39m [2m:[0;39m No LoadTimeWeaver setup: ignoring JPA class transformer
[2m2026-10-27T16:54:26.829+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Starting...
[2m2026-10-27T16:54:27.101+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mcom.zaxxer.hikari.pool.HikariPool       [0;39m [2m:[0;39m HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@40b54762
[2m2026-10-27T16:54:27.104+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Start completed.
[2m2026-10-27T16:54:27.176+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36morg.hibernate.orm.connections.pooling   [0;39m [2m:[0;39m HHH10001005: Database info:
	Database JDBC URL [jdbc:mysql:///hotel_database]
	Database driver: MySQL Connector/J
	Database dialect: MySQLDialect
	Database version: 8.0.45
	Default catalog/schema: hotel_database/undefined
	Autocommit mode: undefined/unknown
	Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
[2m2026-10-27T16:54:27.996+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36morg.hibernate.orm.core                  [0;39m [2m:[0;39m HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: 
    create table booking (
        id bigint not null auto_increment,
        check_in date,
        check_out date,
        created_at datetime(6),
        guest_email varchar(255),
        guest_name varchar(255),
        guests integer not null,
        status enum ('CANCELLED','CONFIRMED','PENDING'),
        total_amount decimal(38,2),
        payment_id bigint,
        room_id bigint not null,
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    create table payment (
        id bigint not null auto_increment,
        amount decimal(38,2),
        method enum ('CARD','CASH','UPI'),
        paid_at datetime(6),
        status enum ('FAILED','REFUNDED','SUCCESS'),
        transaction_id varchar(255),
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    create table room (
        id bigint not null auto_increment,
        capacity integer not null,
        category enum ('DELUXE','STANDARD','SUITE'),
        price_per_night decimal(38,2),
        room_number varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    alter table booking 
       drop index UKxcv4bjb631pysj91ybp40vpo
Hibernate: 
    alter table booking 
       add constraint UKxcv4bjb631pysj91ybp40vpo unique (payment_id)
Hibernate: 
    alter table room 
       drop index UKfvetq5dj3wcvmdf19bbof0os6
Hibernate: 
    alter table room 
       add constraint UKfvetq5dj3wcvmdf19bbof0os6 unique (room_number)
Hibernate: 
    alter table booking 
       add constraint FK70t92vvx289ayx2hq2v4hdcjl 
       foreign key (payment_id) 
       references payment (id)
Hibernate: 
    alter table booking 
       add constraint FKq83pan5xy2a6rn0qsl9bckqai 
       foreign key (room_id) 
       references room (id)
[2m2026-10-27T16:54:28.440+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mj.LocalContainerEntityManagerFactoryBean[0;39m [2m:[0;39m Initialized JPA EntityManagerFactory for persistence unit 'default'
[2m2026-10-27T16:54:28.520+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.s.d.j.r.query.QueryEnhancerFactories  [0;39m [2m:[0;39m Hibernate is in classpath; If applicable, HQL parser will be used.
[2m2026-10-27T16:54:29.415+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mo.s.boot.tomcat.TomcatWebServer         [0;39m [2m:[0;39m Tomcat started on port 8080 (http) with context path '/'
[2m2026-10-27T16:54:29.418+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [           main] [0;39m[36mc.vj.HotelReservationSystemApplication  [0;39m [2m:[0;39m Started HotelReservationSystemApplication in 4.553 seconds (process running for 4.897)
Hibernate: 
    select
        count(*) 
    from
        room r1_0
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        room
        (capacity, category, price_per_night, room_number) 
    values
        (?, ?, ?, ?)
[2m2026-10-27T16:54:34.010+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [nio-8080-exec-1] [0;39m[36mo.a.c.c.C.[Tomcat].[localhost].[/]      [0;39m [2m:[0;39m Initializing Spring DispatcherServlet 'dispatcherServlet'
[2m2026-10-27T16:54:34.010+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Initializing Servlet 'dispatcherServlet'
[2m2026-10-27T16:54:34.010+05:30[0;39m [32m INFO[0;39m [35m8408[0;39m [2m--- [HotelReservationSystem] [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Completed initialization in 0 ms
Hibernate: 
    select
--------------------------------------etc.....


*/