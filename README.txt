Application Android, faite avec le langage de programmation Kotlin,
construite avec Jetpack Compose, qui affiche une liste de Pokémon récupérés depuis l'API PokéAPI.

______________________API et Fonctionnalités______________________

API : Cette application utilise l'[API PokéAPI](https://pokeapi.co/) pour récupérer les données des Pokémon.

Fonctionnalités :

- Affiche une liste de Pokémon avec leurs noms et images.
- Récupère des Pokémon aléatoires depuis l'API.
- Possède un dark mode (qui change en fonction du thème du téléphone)
- Possède une gestion des langues (français et anglais, basé sur celle du téléphone)
- Retour haptique lors de la visualisation d'un pokemon (son et vibration)


Choix Techniques :

- Jetpack Compose: Utilisé pour construire l'interface utilisateur de manière déclarative.
- ViewModel: Utilisé pour gérer l'état de l'interface utilisateur et la récupération des données.
- Coroutines: Utilisées pour les opérations asynchrones comme les appels réseau.
- Coil: Utilisé pour un chargement et une mise en cache efficaces des images.
- Material Design 3: Utilisé pour une conception d'interface utilisateur moderne et cohérente.
- StateFlow et collectAsState: Utilisés pour observer les changements dans l'état de l'interface utilisateur.

--------------------------------------------------------------------------------

______________________Architecture______________________

Le projet suit une architecture MVVM (Model-View-ViewModel) basique :

Modèle :

- `Pokemon`: Classe de données représentant un objet Pokémon.
- `Location`: Classe de données représentant un lieu dans le monde Pokémon.

Vue :

- `HomePage`: Composable affichant la liste des Pokémon.
- `PokemonItem`: Composable représentant un seul élément Pokémon dans la liste.
- `PokemonDetail`: Composable détaillant le Pokémon choisi dans la liste.

ViewModel :

- `PokeInfoViewModel`: Gère l'état de l'interface utilisateur et la logique de récupération des données.
     Expose un `StateFlow` de `PokemonUiState` pour représenter l'état actuel de l'interface utilisateur.
     Récupère des Pokémon aléatoires depuis l'API en utilisant des coroutines.
     Met à jour le `PokemonUiState` avec les données récupérées.

Couche de Données :

- L'application utilise Retrofit pour effectuer des appels API à l'API PokéAPI.
- L'interface `PokemonApi` définit les points de terminaison de l'API.
- La classe `PokeInfoRepository` gère la récupération des données depuis l'API et les fournit au ViewModel.
