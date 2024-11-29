<h1 align="center"> Challenge de forohub para arula latam </h1>
<h4 align="center">
construccion de api rest para el challenge para arula latam
</h4> 
<p>Lo primero que se debe hacer es instalar la base de datos mysql.</p>
<p>una vez instalada la BD se debe crear la base de datos(forohub),despues de crear la base de datos, se debe proceder a crear las siguientes variables de ambiente</p>
<img width="437" alt="Captura de pantalla 2024-10-23 163157" src="https://github.com/user-attachments/assets/56d29688-3d79-46ea-95a6-4ec3a429ae8c">
<h1>spring.datasource.url=jdbc:mysql://${DB_HOST}/forohub</h1>
<h1>spring.datasource.username=${DB_USER}</h1>
<h1>spring.datasource.password=${DB_PASSWORD}</h1>
<h1>api.security.secret=${JWT_SECRET}</h1>

<p>Estos datos de configuracion estan en el siguiente archivo (challenge-aluraforo/src/main/resources/application.properties)</p>

<p>Una vez hecha la configuracion se puede ejecutar la aplicacion de api rest foro hub</p>
<p>la api tiene la siguiente seguridad por roles(ROLE_ADMIN,ROLE_USER)<p>
<p> los enpoint para controlar los usuarios,perfiles,cursos solo pueden se accesibles para los usuarios que tengan el rol ROLE_ADMIN</p>
<p>usuario-controller</p>
<ul>
 <li>GET /usuario </li>
 <li>GET /usuario/{id}</li>
 <li>PUT /usuario</li>
 <li>POST /usuario</li>
 <li>POST /usuario/addPerfil</li>
  <li>DELETE /usuario/{id}</li>
</ul>
<p>perfil-controller</p>
<ul>
 <li>GET /perfil</li>
 <li>GET /perfil/{id}</li>
 <li>DELETE /perfil/{id}</li>
 <li>PUT /perfil</li>
 <li>POST /perfil</li>
</ul>
<p>curso-controller</p>
<ul>
 <li>GET /curso</li>
 <li>GET /curso/{id}</li>
 <li>DELETE /curso/{id}</li>
 <li>PUT /curso</li>
 <li>POST /curso</li>
</ul>
<p>el unico endpont que esta liberado sin seguridad es el de login </p>
<p>autenticacion-controller</p>
<ul>
 <li>POST /login </li>
</ul>
<p>los endpoint topico,respuesta,consultas solo si pueden acceder si los usuarios tienes alguno de los siguientes roles ROLE_ADMIN,ROLE_USER </p>
<p>topico-controller</p>
<ul>
 <li>GET /topico </li>
 <li>GET /topico/{id}</li>
 <li>PUT /topico/{id}</li>
 <li>DELETE /topico/{id}</li>
 <li>POST /topico</li>
</ul>
<p>respuesta-controller</p>
<ul>
 <li>GET /respuesta </li>
 <li>GET /respuesta/{id}</li>
 <li>DELETE /respuesta/{id}</li>
 <li>PUT /respuesta</li>
 <li>POST /respuesta</li>
</ul>

<p>consulta-controller</p>
<ul>
 <li>GET /consulta/topicopaginado </li>
 <li>GET /consulta/topicocursonombre/{nombre}</li>
 <li>GET /consulta/topico10ordenado</li>
</ul>
<p>la aplicacion esta integada con springdocs</p>
<img width="437" alt="Captura de pantalla 2024-10-23 163157" src="https://github.com/user-attachments/assets/413e01b3-fea0-41ec-bff0-a6cdd9ac1d87">










