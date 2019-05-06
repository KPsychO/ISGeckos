# ISGeckos
Proyecto para la asignatura "Ingenieria del Software", la mierda peor impartida (en caso de mi profesor) de la historia.

Esto va a ser un caos... pero bueno: NORMAS

  - 1. Los commits se hacen a la Branch "Development" y ya los mergeamos David y yo (PsychO)
  - 2. NO SE SUBE CODIGO QUE NO FUNCIONA -> Motivo de pagarme una birra y os ganais un par de hostias.
  - 3. NO se borra codigo comun de los demas (Salvo la mierda en "main" o "MainView" para debugear, eso me la chupa.
  - 4. Nuestro nuevo sisetma de almacenamiento de informacion son .txt con JSON, la libreria que hemos usado en TP esta importada en el proyecto, para generar JSONS aleatorios como los que veis de PublishedGames.txt o Users.txt, se usa esto: https://www.json-generator.com/
  - 5. HAY QUE CURRAR, pero de forma inteligente. StackOverflow, Github, y el codigo de los demas, son nuestros mejores amigos.
  - 6. Seguir la estructura de carpetas:
      - Subsistema
        - Control (DAOs, DTOs o lo que te salga de la polla)
        - View (Las ventanas)
        - otros cosos que no se me hayan ocurrido
        
  - 7. Debemos tener un "SubsistemaDAO" (interfaz), que luego se implementa en "SubsistemaDAOCoso" para ponersela dura.
