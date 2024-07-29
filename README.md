# LibraryManagement
Sistema de gestión para una biblioteca encargado de registrar y administrar usuarios, libros y su correspondiente alquiler.

## Ejecutar migraciones
Para ejecutar las migraciones se debe usar el siguiente comando en la carpeta rapiz del proyecto

```shell
docker run -it --workdir="/project" --rm -v $PWD:/project --network=docker_library-network liquibase/liquibase --defaultsFile=db-migrations/liquidbase.properties --changelog-file=db-migrations/changelog.xml update
```