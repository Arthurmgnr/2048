package main.utils;

import java.util.Map;
import java.util.HashMap;

public class TranslationManager {
    private static final Map<String, String> en = new HashMap<>();
    private static final Map<String, String> fr = new HashMap<>();
    private static Map<String, String> currentLanguage = en;

    // Home
    static {
        en.put("home.login.button", "Login");
        en.put("home.login.tooltip", "Click here to go to the login screen");
        en.put("home.register.button", "Register");
        en.put("home.register.tooltip", "Click here to go to the registration screen");

        fr.put("home.login.button", "Connexion");
        fr.put("home.login.tooltip", "Cliquez ici pour aller à l'écran de connexion");
        fr.put("home.register.button", "Inscription");
        fr.put("home.register.tooltip", "Cliquez ici pour aller à l'écran d'inscription");
    }

    // Register
    static {
        en.put("register.register.title", "Register");
        en.put("register.username.label", "Username");
        en.put("register.register.button", "Register");
        en.put("register.register.tooltip", "Click here to create an account");
        en.put("register.back.button", "Back");
        en.put("register.back.tooltip", "Return to home screen");
        en.put("register.previousAvatar.tooltip", "Click here to display the previous avatar");
        en.put("register.nextAvatar.tooltip", "Click here to display the next avatar");
        en.put("register.language.label", "Language");

        fr.put("register.register.title", "Inscription");
        fr.put("register.username.label", "Nom d'utilisateur");
        fr.put("register.register.button", "Inscription");
        fr.put("register.register.tooltip", "Cliquez ici pour créer un compte");
        fr.put("register.back.button", "Retour");
        fr.put("register.back.tooltip", "Revenir à l'écran d'accueil");
        fr.put("register.previousAvatar.tooltip", "Cliquez ici pour afficher l'avatar précédent");
        fr.put("register.nextAvatar.tooltip", "Cliquez ici pour afficher l'avatar suivant");
        fr.put("register.language.label", "Langue");
    }

    // Login
    static {
        en.put("login.login.title", "Login");
        en.put("login.username.label", "Username");
        en.put("login.login.button", "Login");
        en.put("login.login.tooltip", "Click here to log in");
        en.put("login.back.button", "Back");
        en.put("login.back.tooltip", "Return to home screen");

        fr.put("login.login.title", "Connexion");
        fr.put("login.username.label", "Nom d'utilisateur");
        fr.put("login.login.button", "Connexion");
        fr.put("login.login.tooltip", "Cliquez ici pour vous connecter");
        fr.put("login.back.button", "Retour");
        fr.put("login.back.tooltip", "Revenir à l'écran d'accueil");
    }

    // ProfileGame
    static {
        en.put("profileGame.home.tooltip", "Return to home screen");
        en.put("profileGame.bestScore.label", "Best score");
        en.put("profileGame.play.button", "Play");
        en.put("profileGame.play.tooltip", "Play a game of 2048");
        en.put("profileGame.profile.button", "Profile");
        en.put("profileGame.profile.tooltip", "Go to profile screen");
        en.put("profileGame.game.button", "Game");
        en.put("profileGame.message.newUser", "Welcome ");
        en.put("profileGame.message.oldUser", "Welcome back ");

        fr.put("profileGame.home.tooltip", "Revenir à l'écran d'accueil");
        fr.put("profileGame.bestScore.label", "Meilleur score");
        fr.put("profileGame.play.button", "Jouer");
        fr.put("profileGame.play.tooltip", "Jouer une partie de 2048");
        fr.put("profileGame.profile.button", "Profil");
        fr.put("profileGame.profile.tooltip", "Aller à l'écran du profil");
        fr.put("profileGame.game.button", "Jeu");
        fr.put("profileGame.message.newUser", "Bienvenue ");
        fr.put("profileGame.message.oldUser", "Bon retour parmi nous ");
    }

    // Profile
    static {
        en.put("profile.edit.tooltip", "Click here to edit your profile information");
        en.put("profile.creationDate.label", "Creation date");
        en.put("profile.lang.label", "Language");
        en.put("profile.bestScore.label", "Best score");
        en.put("profile.averageScore.label", "Average score");
        en.put("profile.averageNumberMoves.label", "Average number of moves");
        en.put("profile.numberGames.label", "Number of games");
        en.put("profile.numberGamesWon.label", "Number of games won");
        en.put("profile.profile.button", "Profile");
        en.put("profile.game.button", "Game");
        en.put("profile.game.tooltip", "Go to game screen");

        fr.put("profile.edit.tooltip", "Cliquez ici pour modifier les informations de votre profil");
        fr.put("profile.creationDate.label", "Date de création");
        fr.put("profile.lang.label", "Langue");
        fr.put("profile.bestScore.label", "Meilleur score");
        fr.put("profile.averageScore.label", "Score moyen");
        fr.put("profile.averageNumberMoves.label", "Nombre moyen");
        fr.put("profile.numberGames.label", "Nombre de parties");
        fr.put("profile.numberGamesWon.label", "Nombre de parties gagnées");
        fr.put("profile.profile.button", "Profil");
        fr.put("profile.game.button", "Jeu");
        fr.put("profile.game.tooltip", "Aller à l'écran de jeu");
    }

    // EditProfile
    static {
        en.put("editProfile.title", "Profile editing");
        en.put("editProfile.previousAvatar.tooltip", "Click here to display the previous avatar");
        en.put("editProfile.nextAvatar.tooltip", "Click here to display the next avatar");
        en.put("editProfile.back.button", "Back");
        en.put("editProfile.back.tooltip", "Return to profile screen");
        en.put("editProfile.save.button", "Save");
        en.put("editProfile.save.tooltip", "Save changes");

        fr.put("editProfile.title", "Edition du profil");
        fr.put("editProfile.previousAvatar.tooltip", "Cliquez ici pour afficher l'avatar précédent");
        fr.put("editProfile.nextAvatar.tooltip", "Cliquez ici pour afficher l'avatar suivant");
        fr.put("editProfile.back.button", "Retour");
        fr.put("editProfile.back.tooltip", "Retourner à l'écran du profil");
        fr.put("editProfile.save.button", "Enregistrer");
        fr.put("editProfile.save.tooltip", "Enregistrer les modifications");
    }

    // Game
    static {
        en.put("game.exit.tooltip", "Exit the game");
        en.put("game.score.label", "Score");
        en.put("game.bestScore.label", "Best score");
        en.put("game.moves.label", "Moves");
        en.put("game.exit.message", "Do you really want to leave the game?");
        en.put("game.exit.yes.button", "Yes");
        en.put("game.exit.yes.tooltip", "Quit the game");
        en.put("game.exit.no.button", "No");
        en.put("game.exit.no.tooltip", "Continue the game");
        en.put("game.win.quit.button", "Leave");
        en.put("game.win.quit.tooltip", "Return to game screen");
        en.put("game.win.retry.button", "Retry");
        en.put("game.win.retry.tooltip", "Restart a new game");
        en.put("game.win.continue.button", "Continue");
        en.put("game.win.continue.tooltip", "Continue the game");
        en.put("game.lost.quit.button", "Leave");
        en.put("game.lost.quit.tooltip", "Return to game screen");
        en.put("game.lost.retry.button", "Retry");
        en.put("game.lost.retry.tooltip", "Restart a new game");

        fr.put("game.exit.tooltip", "Quitter la partie");
        fr.put("game.score.label", "Score");
        fr.put("game.bestScore.label", "Meilleur score");
        fr.put("game.moves.label", "Mouvements");
        fr.put("game.exit.message", "Voulez-vous vraiment quitter la partie ?");
        fr.put("game.exit.yes.button", "Oui");
        fr.put("game.exit.yes.tooltip", "Quitter la partie");
        fr.put("game.exit.no.button", "Non");
        fr.put("game.exit.no.tooltip", "Continuer la partie");
        fr.put("game.win.quit.button", "Quitter");
        fr.put("game.win.quit.tooltip", "Revenir à l'écran de jeu");
        fr.put("game.win.retry.button", "Recommencer");
        fr.put("game.win.retry.tooltip", "Recommencer une nouvelle partie");
        fr.put("game.win.continue.button", "Continuer");
        fr.put("game.win.continue.tooltip", "Continuer la partie");
        fr.put("game.lost.quit.button", "Quitter");
        fr.put("game.lost.quit.tooltip", "Revenir à l'écran de jeu");
        fr.put("game.lost.retry.button", "Recommencer");
        fr.put("game.lost.retry.tooltip", "Recommencer une nouvelle partie");
    }

    // Win/Lost message
    static {
        en.put("messageWinLost.reached2048", "Well done, you've reached 2048, but don't stop there!");
        en.put("messageWinLost.notReached2048", "Too bad... you didn't reach 2048, but ");
        en.put("messageWinLost.outdated2048", "Well done, ");
        en.put("messageWinLost.2", "it's a good start!");
        en.put("messageWinLost.4", "you can do even better!");
        en.put("messageWinLost.8", "you're on the right track!");
        en.put("messageWinLost.16", "keep moving!");
        en.put("messageWinLost.32", "you're making progress!");
        en.put("messageWinLost.64", "you're getting the hang of it!");
        en.put("messageWinLost.128", "victory is at hand!");
        en.put("messageWinLost.256", "you're almost there!");
        en.put("messageWinLost.512", "you're making great progress!");
        en.put("messageWinLost.1024", "you've already come a long way!");
        en.put("messageWinLost.2048", "you've reached the first goal!");
        en.put("messageWinLost.4096", "you're doing the impossible!");
        en.put("messageWinLost.8192", "your skills are incredible!");
        en.put("messageWinLost.16384", "you've exceeded all expectations!");
        en.put("messageWinLost.32768", "you're a true master of the game!");
        en.put("messageWinLost.65536", "you really are superhuman!");
        en.put("messageWinLost.131072", "you write the history of 2048!");

        fr.put("messageWinLost.reached2048", "Bravo, tu as atteint 2048, mais ne t'arrêtes pas en si bon chemin !");
        fr.put("messageWinLost.notReached2048", "Dommage... tu n'as pas atteint 2048, mais ");
        fr.put("messageWinLost.outdated2048", "Bravo, ");
        fr.put("messageWinLost.2", "c'est un bon début !");
        fr.put("messageWinLost.4", "tu peux encore mieux faire !");
        fr.put("messageWinLost.8", "tu es sur la bonne voie !");
        fr.put("messageWinLost.16", "continue d'avancer !");
        fr.put("messageWinLost.32", "tu es en train de progresser !");
        fr.put("messageWinLost.64", "tu commences à bien t'en sortir !");
        fr.put("messageWinLost.128", "la victoire est proche !");
        fr.put("messageWinLost.256", "tu y es presque !");
        fr.put("messageWinLost.512", "tu fais de super progrès !");
        fr.put("messageWinLost.1024", "tu as déjà fait un long chemin !");
        fr.put("messageWinLost.2048", "tu as atteint le premier objectif !");
        fr.put("messageWinLost.4096", "tu es en train de réaliser l'impossible !");
        fr.put("messageWinLost.8192", "tes compétences sont incroyables !");
        fr.put("messageWinLost.16384", "tu as dépassé toutes les attentes !");
        fr.put("messageWinLost.32768", "tu es un véritable maître du jeu !");
        fr.put("messageWinLost.65536", "tu es vraiment surhumain !");
        fr.put("messageWinLost.131072", "tu écris l'histoire du 2048 !");
    }

    // MessageConstants
    static {
        en.put("messageConstants.usernameEmpty", "Username cannot be empty!");
        en.put("messageConstants.usernameAlreadyExists", "Username already exists!");
        en.put("messageConstants.usernameDoesntExist", "Username doesn't exist!");
        en.put("messageConstants.registrationFailed", "Registration failed");
        en.put("messageConstants.registrationSuccessful", "Registration successful");
        en.put("messageConstants.loginSuccessful", "Login successful");

        fr.put("messageConstants.usernameEmpty", "Le nom d'utilisateur ne peut pas être vide !");
        fr.put("messageConstants.usernameAlreadyExists", "Le nom d'utilisateur existe déjà !");
        fr.put("messageConstants.usernameDoesntExist", "Le nom d'utilisateur n'existe pas !");
        fr.put("messageConstants.registrationFailed", "Échec de l'enregistrement");
        fr.put("messageConstants.registrationSuccessful", "Inscription réussie");
        fr.put("messageConstants.loginSuccessful", "Connexion réussie");
    }

    // Date
    static {
        en.put("date.format", "MMMM dd, yyyy");

        fr.put("date.format", "d MMMM yyyy");
    }

    public static void setLanguage(String languageCode) {
        switch (languageCode) {
            case "FR":
                currentLanguage = fr;
                break;
            case "EN":
            default:
                currentLanguage = en;
                break;
        }
    }

    public static String get(String key) {
        return currentLanguage.getOrDefault(key, "???");
    }
}
