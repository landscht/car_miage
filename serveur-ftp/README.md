# TP1 serveur FTP

## Lancer le projet
- Récupérer le projet
`git clone https://github.com/landscht/car_miage`
- Lancer la classe **Application.java** qui se trouve à la racine du TP
- Se connecter au serveur à l'aide d'un terminal ou d'un client FTP en désactivant le mode *Anonymous* `ftp localhost 20`
- Les identifiants sont `admin admin`

## Étendre les commandes
### La solution
J'ai mis en place un système qui permet de répertorier les commandes dans une Map. De ce fait, nous pouvons retrouver et lancer une commande à l'aide de son nom associé.
Il y a donc une classe abstraite qui va récupérer la requête et l'utilisateur qui a lancé celle ci et executer la méthode *exec*. Ainsi chaque nouvelle commande hérite de 
commande et implémente la méthode exec selon ce que dois faire la commande.

### Commande avec transfert
Les commandes nécessitant un transfert, hérite de `CommandTransfert` qui elle meme hérite de `Command`. `CommandTransfert` évite de devoir réécrire le protocole de transfert
de données dans chaque commande nécessitant un transfert.

### Comment ça marche ?
- Le client entre la commande `CD ..`
- Le serveur va récupérer `CWD ..`
- Il récupère le nom de la commande `String cmd = str.split(" ")[0];`
- Il peut maintenant retrouver la commande dans la map grâce à son nom `CommandType.COMMAND.containsKey(cmd);`
- Ici il y a 2 scénario possible.
  - ✔ La map contient bien la commande, on peut l'éxécuter `CommandType.COMMAND.get(cmd).run(this, str);`
  - 𐄂 La map ne contient pas la commande, on envoie le message 502 Method not implemented
