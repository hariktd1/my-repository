This POC offers following features with OAuth2 and JWT .
1)User can register into the system with roles . Roles can be USER or ADMIN.
2)Registed users can have the previlage to create new sites  and access site details as well.
	* USER/ADMIN can create sites
	* USER (roles) has permissions on user data
	* ADMIN (roles) has permissions on whole data

3)OAuth2 JWT authentication with spring-boot, using grant types `password` and `refresh token`;
4)Published authenticated REST resources `/api/sites`, `/api/users` and one public `/api/signin` for user registration;
5) method-level authorization based on `@PreAuthorize` 

***** How to access API ?*************

1) Consume /api/signin with email, password and role
2) Consume /oauth/token with username , password and grant_type as password. 
   Don't forget to give header 'Authorization' -  Basic  <Base64 of clientId and client-secret > .
   This api will return access_token , refresh_token , token_type Bearer etc.

3) Consume /api/sites for crud operation and pass header 'Authorization ' Bearer <access_token>
	



