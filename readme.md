Thymeleaf form 
En un input de un form el id es para vincular el label y el name es lo que utiliza thymeleaf para enviar la data al backeend

Hacer un href/actionform con atributo
"@{/actualizarMascota/{id}(id=${mascota.id})}"


Input select
For each de las opciones 
th:each="duenio : ${duenios}"
Valor de las opciones
th:value="${duenio.id}"
Que mostramos en las opciones
th:text="${duenio.nombre}" 
Seleccionar un atributo por defecto dentro de un select, en este caso el duenio que ya tiene vinculado esa mascota
th:selected ="${duenio.id eq mascota.duenio.id }"

Form de actualizacion

<input name="_method" type="hidden" value="put" />

El atributo name="_method" con el valor put dentro de un elemento input en Thymeleaf se utiliza para realizar una solicitud HTTP de tipo PUT. Este tipo de solicitud se usa típicamente para actualizar recursos en un servidor web.
Cuando se desea realizar una actualización de un recurso en el servidor, se utiliza una solicitud HTTP PUT para enviar los datos actualizados al servidor.


Simular SQL en Java, sirve para simular datos para casos de uso

En resources/application.properties agregamos la propiedad

# Base de datos SQL precargados
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

y luego creamos el archivo data.sql en resources

Es importante que a la hora de crear las tablas sea en el orden segun las relaciones dadas.
Ej la mascota tiene un duenio y si creamos la mascota primero y despues al duenio va a tirar error

Los nombres de las tablas es igual al nombre de la clase indicada en model con minuscula Ej. la tabla de nuestro modelo Duenio seria duenio.
O tambien tenemos la opcion de indicar en la clase del modelo Duenio la annotation @Table("nombredelatabla") 

Base de datos en memoria H2 con datos precargados en application.properties

# Base de datos SQL H2 precargados 
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

JPA Repository crear query que ordena por Nombre Ascendente (Distingue entre mayusculas y minusculas)
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

List<Mascota> findAllByOrderByNombreAsc();

otra opcion para que ignore si es mayuscula o minuscula es hacer una query

@Query("SELECT m FROM Mascota m ORDER BY LOWER(m.nombre) ASC")
findAllOrderByNombreIgnoreCaseDesc();

Podemos usar un Sort en el service para agrupar y ordenar los datos que nos trae el repository
	
	public List<Mascota> listarMascotas(){
		//criterio
		Sort sortBy = Sort.by(Sort.Order.asc("nombre").ignoreCase());
		// retonamos segun repository y criterio
		return mascotaRepository.findAll(sortBy);
	}

Cramos el Sort, definimos su direccion y bajo que criterio entre "", luego lo pasamos como argumento al repositorio.metodo(sort)

Otra opcion es directamente colocarlo como argumento en el metodo del repository

return mascotaRepository.findAll((Sort.by(Sort.Order.asc("nombre").ignoreCase())));

https://danielme.com/2023/04/26/curso-spring-data-jpa-ordenacion-sort/

Loombok 
@Data = toString, equals, hashcode, getters y setters


Crear una annotation para validar datos no primitivos

https://docs.spring.io/spring-framework/reference/core/validation.html

Spring Security 

Sirve para proteger los endpoints c hash de token que valida si esta o no para dejarte pasar.

Estructura de clases y paquetes:

config
	JwtTokenUtil
	SecurityConfig (puede o no extender nada (versiones nuevas), SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>, WebSecurityConfigurerAdapter)

model
	Usuario

repository
	UsuarioRepository

service
	CustomUserDetailsService

web
	JwtRequestFilter
	
resources
	Aplication.properties

Salt -> Incrementar la seguridad en nuestro cifrados

Con la annotation @Value("${jwt.secret}")
obtenemos el valor de la variable de entorno que asignamos en application.properties

SecurityConfig
https://www.baeldung.com/java-config-spring-security