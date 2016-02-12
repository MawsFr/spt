Mustapha NEZZARI L3 MIAGE

ReadMe

TP3 :

Voici le projet Eclipse de l’application reprenant en grande partie les lignes du site culture-generale.fr

Voici comment le logiciel fonctionne :
- Ouvrez le logiciel et choisissez un fichier questionnaire (il n’y a ici que le fichier questionnaire.xml mais vous pouvez créer le votre qui devra respecter la dtd)
- Choisissez le Quizz dans la liste et cliquez sur le bouton “Faire le quizz”
- Amusez vous et obtenez votre score
- Vous pouvez passer au quizz suivant ou revenir à la sélection de quizz

NB : Si le coté interface graphique ne vous intéresse pas spécialement, regardez du coté des classes du package “model”. Elle représentent l’intelligence du jeu, en particulier la classe QuizzManager qui contient la fonction load() qui charge le fichier xml.
NB2 : Si le projet rame un peu, c'est surement à cause des animations (inutiles je vous l'accorde) que vous pouvez desactiver grace au boolean "animated" dans la classe "Tools", qu'il faut bien sur mettre à false. 