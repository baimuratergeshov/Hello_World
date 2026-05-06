

## TP - SERVEUR WEB EN KOTLIN AVEC BOOTSTRAP


OBJECTIF:
Créer une application web fonctionnelle avec un serveur HTTP en Kotlin
qui affiche plusieurs pages avec Bootstrap pour la mise en forme.

FICHIERS À PRODUIRE:
1. Main.kt (ou hello-world-web.kt)
- Fonction main() : mise en place du serveur HTTP
- Définition des routes (/, /about, /contact)
- Création des instances des classes Bootstrap
- Gestion du démarrage du serveur et ouverture du navigateur

2. BootstrapCard.kt
- Classe représentant une carte Bootstrap
- Propriétés: title (titre), content (contenu)
- Méthode render() : génère le HTML de la carte

3. BootstrapNavbar.kt
- Classe représentant une barre de navigation Bootstrap
- Propriétés: brand (marque/titre), links (liste des liens)
- Méthode render() : génère le HTML de la navbar avec routing

4. BootstrapFooter.kt
- Classe représentant un footer Bootstrap
- Propriétés: text (texte du footer)
- Méthode render() : génère le HTML du footer

5. BootstrapContainer.kt
- Classe représentant un conteneur Bootstrap main
- Propriétés: elements (liste des éléments HTML)
- Méthode render() : génère le HTML du conteneur avec grille responsive

---
LOGIQUE DE CONSTRUCTION DES ÉLÉMENTS
---

🎨 PATTERN DE RENDU (Render Pattern):
Chaque classe Bootstrap possède une méthode render() qui retourne du HTML.
Cela permet une séparation claire entre la logique Kotlin et l'affichage HTML.

📦 BOOTSTRAP COMPONENTS:

1️⃣ BootstrapCard
- Une carte est une unité d'affichage avec un titre et du contenu
- Le rendu utilise les classes Bootstrap: .card, .card-body, .card-title, .card-text
- Chaque carte a une largeur fixe (18rem) pour l'uniformité

2️⃣ BootstrapNavbar
- La navbar est la barre de navigation principal
- Elle contient un brand (titre) et une liste de liens
- Les liens sont mappés à des routes:
     * "Accueil" → "/"
     * "À propos" → "/about"
     * "Contact" → "/contact"
- La navbar est responsive: elle se replie sur mobile avec un toggle button

3️⃣ BootstrapFooter
- Le footer est un simple conteneur avec un texte de copyright
- Il utilise les classes Bootstrap pour l'alignement et le style
- Il apparaît au bas de chaque page

4️⃣ BootstrapContainer
- C'est le conteneur principal qui organise les cartes
- Utilise une grille Bootstrap responsive:
     * col-12: 100% de largeur sur petits écrans (mobile)
     * col-sm-6: 50% de largeur sur moyens écrans (tablette)
     * col-md-4: 33% de largeur sur grands écrans (desktop)
- Chaque colonne a une marge inférieure (mb-4) pour l'espacement
- Accepte une liste générique d'éléments HTML

🔄 COMPOSITION DES PAGES:

Structure générale d'une page:
1. HTML Head (meta, title, Bootstrap CSS CDN)
2. Navbar (navbar.render())
3. Contenu spécifique (titre, cartes, formulaire, etc.)
4. Footer (footer.render())
5. Bootstrap JS CDN pour les interactions

🌐 ROUTING:
- "/" (Accueil): affiche la navbar, un titre "Hello World", 6 cartes, footer
- "/about": affiche la navbar, texte descriptif, footer
- "/contact": affiche la navbar, formulaire de contact, footer

💡 CONCEPTS CLÉ:

- String Templates Kotlin ($ {}):
Les variables sont interpolées directement dans les chaînes HTML
Exemple: ${navbar.render()} insère le HTML généré par la navbar

- trimIndent():
Supprime les indentations inutiles dans les chaînes multi-lignes
Produit un HTML plus propre sans espaces supplémentaires

- joinToString():
Utilisé pour transformer une liste en chaîne HTML
Exemple: links.joinToString("\n") crée les items de la navbar

- Lambda Expressions:
cards.map { card -> ... } transforme chaque carte en élément HTML
Permet une transformation élégante et fonctionnelle

- HttpServer/HttpExchange:
Gère le serveur HTTP et les requêtes/réponses
Envoie le HTML au navigateur avec le bon type de contenu

---




## TP - SERVEUR WEB EN KOTLIN AVEC BOOTSTRAP


## 📋 PLAN DE DÉVELOPPEMENT PAR TÂCHES:


PHASE 1 - STRUCTURE DE BASE

☐ Tâche 1.1: Créer les classes Bootstrap (Card, Navbar, Footer, Container)
☐ Tâche 1.2: Implémenter la méthode render() pour chaque classe
☐ Tâche 1.3: Tester le rendu HTML de chaque composant isolément

PHASE 2 - SERVEUR HTTP

☐ Tâche 2.1: Initialiser le serveur HTTP sur le port 8080
☐ Tâche 2.2: Créer la route "/" (accueil) avec 6 cartes
☐ Tâche 2.3: Tester la route "/" dans le navigateur
☐ Tâche 2.4: Vérifier le responsive design Bootstrap

PHASE 3 - PAGES SUPPLÉMENTAIRES

☐ Tâche 3.1: Créer la route "/about" avec description
☐ Tâche 3.2: Créer la route "/contact" avec formulaire
☐ Tâche 3.3: Tester la navigation entre les pages
☐ Tâche 3.4: Vérifier les liens de la navbar

PHASE 4 - MISE EN FORME ET STYLE

☐ Tâche 4.1: Améliorer le CSS des cartes (couleurs, ombres)
☐ Tâche 4.2: Personnaliser la navbar (couleurs, logo)
☐ Tâche 4.3: Styliser le footer (background, texte)
☐ Tâche 4.4: Ajouter des animations Bootstrap (hover, transitions)

---
## ---- DANS UN FUTUR PROCHE ---
---

PHASE 5 - FONCTIONNALITÉS AVANCÉES

☐ Tâche 5.1: Ajouter la validation du formulaire /contact
☐ Tâche 5.2: Implémenter la gestion du formulaire (POST)
☐ Tâche 5.3: Créer une page d'erreur 404
☐ Tâche 5.4: Ajouter un logging des requêtes

PHASE 6 - REFACTORING ET OPTIMISATION

☐ Tâche 6.1: Extraire les templates HTML dans des fichiers séparés
☐ Tâche 6.2: Créer un gestionnaire de routes centralisé
☐ Tâche 6.3: Optimiser le code (DRY, SOLID principles)
☐ Tâche 6.4: Ajouter des commentaires et documentation

PHASE 7 - TESTS ET DÉPLOIEMENT
☐ Tâche 7.1: Tester toutes les routes et pages
☐ Tâche 7.2: Vérifier la compatibilité multi-navigateurs
☐ Tâche 7.3: Tester le responsive sur mobile/tablette
☐ Tâche 7.4: Préparer le déploiement (build, compilation)
