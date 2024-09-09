## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

	Colocamos la etiqueta de @Service para definir BlueprintsService:

	![image](https://github.com/user-attachments/assets/c4b0afe4-1382-4eb3-bdf4-edc8fb852b37)

	Colocamos la etiqueta de @Service en InMemoryBluePrintPersistence para cargarla en el contenedor de SpringBoot

	![image](https://github.com/user-attachments/assets/cc29baf6-f84b-485d-9c97-afa9fb38a9df)



2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

![image](https://github.com/user-attachments/assets/a82235dc-7b50-49ba-8396-a7d3fc7f0c31)

Se crean los metodos en BluePrintServices de getBlueprint() y getBluePrintsByAuthor():

![image](https://github.com/user-attachments/assets/cca59254-e6d3-4ad4-9636-1666e12c5fd7)

![image](https://github.com/user-attachments/assets/fd74c3dd-03ff-4033-963f-7172b3e05543)

Se define el cuerpo para cada metodo en la clase de InMememoryBlueprintPersistence

![image](https://github.com/user-attachments/assets/e9ea18b6-3371-4689-8d3e-86c28c99c146)


3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

	Creamos un metodo main para simular el programa de gestion de planos arquitectonicos:
	![image](https://github.com/user-attachments/assets/d6652015-11b8-464d-b5a3-0b84aa859612)


5. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

6. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
