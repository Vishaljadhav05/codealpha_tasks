package com.vj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentGradeTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentGradeTrackerApplication.class, args);
	}

}


// output:-

/*
 
=> Database:-


mysql> use gradetracker;
Database changed
mysql> select * from students;
+----+---------------+-------------+
| id | name          | roll_number |
+----+---------------+-------------+
|  1 | Aarav Sharma  | S001        |
|  2 | Priya Verma   | S002        |
|  3 | Rohan Mehta   | S003        |
|  4 | Vishal Jadhav | S101        |
+----+---------------+-------------+
4 rows in set (0.00 sec)

mysql> select * from grades;
+----+-------+-------------+------------+
| id | score | subject     | student_id |
+----+-------+-------------+------------+
|  1 |    92 | Mathematics |          1 |
|  2 |    88 | Physics     |          1 |
|  3 |    79 | English     |          1 |
|  4 |    67 | Mathematics |          2 |
|  5 |    74 | Physics     |          2 |
|  6 |    81 | English     |          2 |
|  7 |    45 | Mathematics |          3 |
|  8 |    52 | Physics     |          3 |
|  9 |    38 | English     |          3 |
| 10 |    95 | Mathematics |          4 |
| 11 |  89.1 | Science     |          4 |
| 12 |    98 | Hindi       |          4 |
+----+-------+-------------+------------+
12 rows in set (0.00 sec)

mysql> select * from students;
+----+---------------+-------------+
| id | name          | roll_number |
+----+---------------+-------------+
|  2 | Priya Verma   | S002        |
|  3 | Rohan Mehta   | S003        |
|  4 | Vishal Jadhav | S101        |
+----+---------------+-------------+
3 rows in set (0.00 sec)

mysql> select * from grades;
+----+-------+-------------+------------+
| id | score | subject     | student_id |
+----+-------+-------------+------------+
|  4 |    67 | Mathematics |          2 |
|  5 |    74 | Physics     |          2 |
|  6 |    81 | English     |          2 |
|  7 |    45 | Mathematics |          3 |
|  8 |    52 | Physics     |          3 |
|  9 |    38 | English     |          3 |
| 10 |    95 | Mathematics |          4 |
| 11 |  89.1 | Science     |          4 |
| 12 |    98 | Hindi       |          4 |
+----+-------+-------------+------------+
9 rows in set (0.00 sec)


===> Console


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

[32m :: Spring Boot :: [39m              [2m (v4.1.2)[0;39m

[2m2026-10-28T21:01:54.528+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.vj.StudentGradeTrackerApplication   [0;39m [2m:[0;39m Starting StudentGradeTrackerApplication using Java 21.0.12 with PID 52612 (E:\Eclipse Workspace\Code Alpha\StudentGradeTracker\target\classes started by hp in E:\Eclipse Workspace\Code Alpha\StudentGradeTracker)
[2m2026-10-28T21:01:54.534+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.vj.StudentGradeTrackerApplication   [0;39m [2m:[0;39m No active profile set, falling back to 1 default profile: "default"
[2m2026-10-28T21:01:55.658+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Bootstrapping Spring Data JPA repositories in DEFAULT mode.
[2m2026-10-28T21:01:55.750+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Finished Spring Data repository scanning in 73 ms. Found 1 JPA repository interface.
[2m2026-10-28T21:01:56.694+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.s.boot.tomcat.TomcatWebServer         [0;39m [2m:[0;39m Tomcat initialized with port 8080 (http)
[2m2026-10-28T21:01:56.726+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.apache.catalina.core.StandardService  [0;39m [2m:[0;39m Starting service [Tomcat]
[2m2026-10-28T21:01:56.727+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.apache.catalina.core.StandardEngine   [0;39m [2m:[0;39m Starting Servlet engine: [Apache Tomcat/11.0.27]
[2m2026-10-28T21:01:57.166+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36morg.apache.jasper.servlet.TldScanner    [0;39m [2m:[0;39m At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
[2m2026-10-28T21:01:57.182+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mb.w.c.s.WebApplicationContextInitializer[0;39m [2m:[0;39m Root WebApplicationContext: initialization completed in 2549 ms
[2m2026-10-28T21:01:57.502+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36morg.hibernate.orm.jpa                   [0;39m [2m:[0;39m HHH008540: Processing PersistenceUnitInfo [name: default]
[2m2026-10-28T21:01:57.620+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36morg.hibernate.orm.core                  [0;39m [2m:[0;39m HHH000001: Hibernate ORM core version 7.4.13.Final
[2m2026-10-28T21:01:58.452+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.s.o.j.p.SpringPersistenceUnitInfo     [0;39m [2m:[0;39m No LoadTimeWeaver setup: ignoring JPA class transformer
[2m2026-10-28T21:01:58.523+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Starting...
[2m2026-10-28T21:01:59.218+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.zaxxer.hikari.pool.HikariPool       [0;39m [2m:[0;39m HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@24e1e90a
[2m2026-10-28T21:01:59.222+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Start completed.
[2m2026-10-28T21:01:59.401+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36morg.hibernate.orm.connections.pooling   [0;39m [2m:[0;39m HHH10001005: Database info:
	Database JDBC URL [jdbc:mysql:///gradetracker]
	Database driver: MySQL Connector/J
	Database dialect: MySQLDialect
	Database version: 8.0.45
	Default catalog/schema: gradetracker/undefined
	Autocommit mode: undefined/unknown
	Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
	JDBC fetch size: none
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
[2m2026-10-28T21:02:01.459+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36morg.hibernate.orm.core                  [0;39m [2m:[0;39m HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
[2m2026-10-28T21:02:01.968+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mj.LocalContainerEntityManagerFactoryBean[0;39m [2m:[0;39m Initialized JPA EntityManagerFactory for persistence unit 'default'
[2m2026-10-28T21:02:02.106+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.s.d.j.r.query.QueryEnhancerFactories  [0;39m [2m:[0;39m Hibernate is in classpath; If applicable, HQL parser will be used.
[2m2026-10-28T21:02:04.425+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mo.s.boot.tomcat.TomcatWebServer         [0;39m [2m:[0;39m Tomcat started on port 8080 (http) with context path '/'
[2m2026-10-28T21:02:04.437+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [           main] [0;39m[36mcom.vj.StudentGradeTrackerApplication   [0;39m [2m:[0;39m Started StudentGradeTrackerApplication in 10.696 seconds (process running for 11.602)
[2m2026-10-28T21:02:30.166+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [nio-8080-exec-1] [0;39m[36mo.a.c.c.C.[Tomcat].[localhost].[/]      [0;39m [2m:[0;39m Initializing Spring DispatcherServlet 'dispatcherServlet'
[2m2026-10-28T21:02:30.169+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Initializing Servlet 'dispatcherServlet'
[2m2026-10-28T21:02:30.171+05:30[0;39m [32m INFO[0;39m [35m52612[0;39m [2m--- [StudentGradeTracker] [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Completed initialization in 2 ms


*/