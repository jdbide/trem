# Configuration **Hibernate**

### Configuration de la connexion au socle


1. Context.xml

```xml
<Context antiJARLocking="true" path="/">
	<Resource name="BeanManager" auth="Container"
		type="javax.enterprise.inject.spi.BeanManager" factory="org.jboss.weld.resources.ManagerObjectFactory" />

	<Resource name="jdbc/socle" auth="Container"  type="com.mchange.v2.c3p0.ComboPooledDataSource"
	 factory="org.apache.naming.factory.BeanFactory"
		user="<user>" password="<password>" driverClass="com.mysql.jdbc.Driver"
		jdbcUrl="jdbc:mysql://<server's ip>/<database name>"  minPoolSize="4" maxPoolSize="8" />

	<Realm className="org.apache.catalina.realm.DataSourceRealm"
		dataSourceName="jdbc/socle" userTable="socle_user" userNameCol="loginUser"
		userCredCol="passwordUser" userRoleTable="socle_user" roleNameCol="tomcatRoleUser"
		localDataSource="true" digest="sha1">
	</Realm>
</Context>
```

1. pom.xml

Il faut ajouter la dépendance c3p0



### Quelques liens

__configuration c3p0__ :
<http://www.mchange.com/projects/c3p0/>
