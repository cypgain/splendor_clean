Il s'agit de la version finale de notre projet Splendor.

Toutes les fonctionnalités sont présentes dont:

Jeu:
- Choix de la langue
- Choix du nombre de joueur (jusqu'a 4)
- Prendre des jetons
- Acheter Cartes plateau ou réservée
- Points de prestige
- Reserver une carte
- Visite des nobles
- Reposer des jetons
- Fin de la partie

Debug :
- Gestion des scénarios (Charger, sauvegarder)
- Relancer avec choix du nombre de joueur (2 à 4)
- Aide à la création de scénarios de tests (Passer le tour, modifier les jetons).


Pour vérifier le detail de chaque scénario présent, ouvrez le fichier Scénario.pdf présent à la racine.


Pour ouvrir le menu debug, il faut appuyer sur la touche D lors de la première apparition du plateau de jeu.
A noter qu'il n'est plus possible d'interragir avec la touche D si un tour de jeu est passé. 
Il faut donc relancer le jeu ou une partie si vous souhaitez interragir à nouveau avec cette touche.


Pour compiler (Veuillez vous placer à la racine):

javac @compile.list -d out -encoding utf8
cd out
java splendor_equipe_h.Controleur


Ou pour lancer le jeu directement :

Windows : lancer run.bat
Linux   : lancer run.sh (avoir au préalable fait   chmod -x run.sh   dans le terminal)

