-- Host: localhost    Database: personne
-- ------------------------------------------------------

-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(5) NOT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;