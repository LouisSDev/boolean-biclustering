# boolean-biclustering

This project was realised as a student project. The goal was to understand and apply bi clustering algorithm on any dataset.

We've built a very simple boolean matrix clustering algorithm, quite ineffective with larger datasets altough it gives some pretty good results with basic dataset as the one we used. 

We implemented it using two different algorithms one being highly inneficient while finding very few clusters, the other being able to check far more possibilities while having decent performances


Here are some of the results we obtained with the dataset `zoo.data`


Tested Biclusterer class fr.isep.algoprogra.biclustering.algorithm.ColumnPositiveScanBiClusterer 10 times in 3290 ms.
         |venomous |tail |hasNoLegs |
pitviper |1        |1    |1         |
seasnake |1        |1    |1         |
stingray |1        |1    |1         |

         |feathers |eggs |backbone |breathes |tail |hasTwoLegs |
chicken  |1        |1    |1        |1        |1    |1          |
crow     |1        |1    |1        |1        |1    |1          |
dove     |1        |1    |1        |1        |1    |1          |
duck     |1        |1    |1        |1        |1    |1          |
flamingo |1        |1    |1        |1        |1    |1          |
gull     |1        |1    |1        |1        |1    |1          |
hawk     |1        |1    |1        |1        |1    |1          |
kiwi     |1        |1    |1        |1        |1    |1          |
lark     |1        |1    |1        |1        |1    |1          |
ostrich  |1        |1    |1        |1        |1    |1          |
parakeet |1        |1    |1        |1        |1    |1          |
penguin  |1        |1    |1        |1        |1    |1          |
pheasant |1        |1    |1        |1        |1    |1          |
rhea     |1        |1    |1        |1        |1    |1          |
skimmer  |1        |1    |1        |1        |1    |1          |
skua     |1        |1    |1        |1        |1    |1          |
sparrow  |1        |1    |1        |1        |1    |1          |
swan     |1        |1    |1        |1        |1    |1          |
vulture  |1        |1    |1        |1        |1    |1          |
wren     |1        |1    |1        |1        |1    |1          |

         |domestic |catsize |hasFourLegs |
pony     |1        |1       |1           |
pussycat |1        |1       |1           |
reindeer |1        |1       |1           |

         |fins |tail |hasNoLegs |
sole     |1    |1    |1         |
stingray |1    |1    |1         |
tuna     |1    |1    |1         |


----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------

Tested Biclusterer class fr.isep.algoprogra.biclustering.algorithm.ColumnCombinationsScanBiClusterer 10 times in 8407 ms.
         |eggs |aquatic |predator |toothed |backbone |fins |tail |catsize |hasNoLegs |
dogfish  |1    |1       |1        |1       |1        |1    |1    |1       |1         |
pike     |1    |1       |1        |1       |1        |1    |1    |1       |1         |
stingray |1    |1       |1        |1       |1        |1    |1    |1       |1         |
tuna     |1    |1       |1        |1       |1        |1    |1    |1       |1         |

         |hair |milk |toothed |backbone |breathes |tail |domestic |catsize |hasFourLegs |
calf     |1    |1    |1       |1        |1        |1    |1        |1       |1           |
goat     |1    |1    |1       |1        |1        |1    |1        |1       |1           |
pony     |1    |1    |1       |1        |1        |1    |1        |1       |1           |
pussycat |1    |1    |1       |1        |1        |1    |1        |1       |1           |
reindeer |1    |1    |1       |1        |1        |1    |1        |1       |1           |

         |aquatic |predator |toothed |backbone |fins |tail |catsize |hasNoLegs |
dogfish  |1       |1        |1       |1        |1    |1    |1       |1         |
dolphin  |1       |1        |1       |1        |1    |1    |1       |1         |
pike     |1       |1        |1       |1        |1    |1    |1       |1         |
porpoise |1       |1        |1       |1        |1    |1    |1       |1         |
stingray |1       |1        |1       |1        |1    |1    |1       |1         |
tuna     |1       |1        |1       |1        |1    |1    |1       |1         |

        |feathers |eggs |airborne |aquatic |backbone |breathes |tail |hasTwoLegs |
duck    |1        |1    |1        |1       |1        |1        |1    |1          |
gull    |1        |1    |1        |1       |1        |1        |1    |1          |
skimmer |1        |1    |1        |1       |1        |1        |1    |1          |
skua    |1        |1    |1        |1       |1        |1        |1    |1          |
swan    |1        |1    |1        |1       |1        |1        |1    |1          |

         |eggs |aquatic |predator |toothed |backbone |fins |tail |hasNoLegs |
bass     |1    |1       |1        |1       |1        |1    |1    |1         |
catfish  |1    |1       |1        |1       |1        |1    |1    |1         |
chub     |1    |1       |1        |1       |1        |1    |1    |1         |
dogfish  |1    |1       |1        |1       |1        |1    |1    |1         |
herring  |1    |1       |1        |1       |1        |1    |1    |1         |
pike     |1    |1       |1        |1       |1        |1    |1    |1         |
piranha  |1    |1       |1        |1       |1        |1    |1    |1         |
stingray |1    |1       |1        |1       |1        |1    |1    |1         |
tuna     |1    |1       |1        |1       |1        |1    |1    |1         |

        |feathers |eggs |airborne |predator |backbone |breathes |tail |hasTwoLegs |
crow    |1        |1    |1        |1        |1        |1        |1    |1          |
gull    |1        |1    |1        |1        |1        |1        |1    |1          |
hawk    |1        |1    |1        |1        |1        |1        |1    |1          |
skimmer |1        |1    |1        |1        |1        |1        |1    |1          |
skua    |1        |1    |1        |1        |1        |1        |1    |1          |
vulture |1        |1    |1        |1        |1        |1        |1    |1          |

         |milk |aquatic |predator |toothed |backbone |breathes |fins |catsize |
dolphin  |1    |1       |1        |1       |1        |1        |1    |1       |
porpoise |1    |1       |1        |1       |1        |1        |1    |1       |
seal     |1    |1       |1        |1       |1        |1        |1    |1       |
sealion  |1    |1       |1        |1       |1        |1        |1    |1       |

         |hair |milk |toothed |backbone |breathes |tail |domestic |hasFourLegs |
calf     |1    |1    |1       |1        |1        |1    |1        |1           |
goat     |1    |1    |1       |1        |1        |1    |1        |1           |
hamster  |1    |1    |1       |1        |1        |1    |1        |1           |
pony     |1    |1    |1       |1        |1        |1    |1        |1           |
pussycat |1    |1    |1       |1        |1        |1    |1        |1           |
reindeer |1    |1    |1       |1        |1        |1    |1        |1           |

        |feathers |eggs |aquatic |predator |backbone |breathes |tail |hasTwoLegs |
gull    |1        |1    |1       |1        |1        |1        |1    |1          |
penguin |1        |1    |1       |1        |1        |1        |1    |1          |
skimmer |1        |1    |1       |1        |1        |1        |1    |1          |
skua    |1        |1    |1       |1        |1        |1        |1    |1          |

         |hair |milk |predator |toothed |backbone |breathes |tail |catsize |hasFourLegs |
boar     |1    |1    |1        |1       |1        |1        |1    |1       |1           |
cheetah  |1    |1    |1        |1       |1        |1        |1    |1       |1           |
leopard  |1    |1    |1        |1       |1        |1        |1    |1       |1           |
lion     |1    |1    |1        |1       |1        |1        |1    |1       |1           |
lynx     |1    |1    |1        |1       |1        |1        |1    |1       |1           |
mink     |1    |1    |1        |1       |1        |1        |1    |1       |1           |
mongoose |1    |1    |1        |1       |1        |1        |1    |1       |1           |
polecat  |1    |1    |1        |1       |1        |1        |1    |1       |1           |
puma     |1    |1    |1        |1       |1        |1        |1    |1       |1           |
pussycat |1    |1    |1        |1       |1        |1        |1    |1       |1           |
raccoon  |1    |1    |1        |1       |1        |1        |1    |1       |1           |
wolf     |1    |1    |1        |1       |1        |1        |1    |1       |1           |

         |hair |milk |predator |toothed |backbone |breathes |catsize |hasFourLegs |
aardvark |1    |1    |1        |1       |1        |1        |1       |1           |
bear     |1    |1    |1        |1       |1        |1        |1       |1           |
boar     |1    |1    |1        |1       |1        |1        |1       |1           |
cheetah  |1    |1    |1        |1       |1        |1        |1       |1           |
leopard  |1    |1    |1        |1       |1        |1        |1       |1           |
lion     |1    |1    |1        |1       |1        |1        |1       |1           |
lynx     |1    |1    |1        |1       |1        |1        |1       |1           |
mink     |1    |1    |1        |1       |1        |1        |1       |1           |
mongoose |1    |1    |1        |1       |1        |1        |1       |1           |
polecat  |1    |1    |1        |1       |1        |1        |1       |1           |
puma     |1    |1    |1        |1       |1        |1        |1       |1           |
pussycat |1    |1    |1        |1       |1        |1        |1       |1           |
raccoon  |1    |1    |1        |1       |1        |1        |1       |1           |
wolf     |1    |1    |1        |1       |1        |1        |1       |1           |

         |hair |milk |predator |backbone |breathes |tail |catsize |hasFourLegs |
boar     |1    |1    |1        |1        |1        |1    |1       |1           |
cheetah  |1    |1    |1        |1        |1        |1    |1       |1           |
leopard  |1    |1    |1        |1        |1        |1    |1       |1           |
lion     |1    |1    |1        |1        |1        |1    |1       |1           |
lynx     |1    |1    |1        |1        |1        |1    |1       |1           |
mink     |1    |1    |1        |1        |1        |1    |1       |1           |
mongoose |1    |1    |1        |1        |1        |1    |1       |1           |
platypus |1    |1    |1        |1        |1        |1    |1       |1           |
polecat  |1    |1    |1        |1        |1        |1    |1       |1           |
puma     |1    |1    |1        |1        |1        |1    |1       |1           |
pussycat |1    |1    |1        |1        |1        |1    |1       |1           |
raccoon  |1    |1    |1        |1        |1        |1    |1       |1           |
wolf     |1    |1    |1        |1        |1        |1    |1       |1           |

         |hair |milk |toothed |backbone |breathes |tail |catsize |hasFourLegs |
antelope |1    |1    |1       |1        |1        |1    |1       |1           |
boar     |1    |1    |1       |1        |1        |1    |1       |1           |
buffalo  |1    |1    |1       |1        |1        |1    |1       |1           |
calf     |1    |1    |1       |1        |1        |1    |1       |1           |
cheetah  |1    |1    |1       |1        |1        |1    |1       |1           |
deer     |1    |1    |1       |1        |1        |1    |1       |1           |
elephant |1    |1    |1       |1        |1        |1    |1       |1           |
giraffe  |1    |1    |1       |1        |1        |1    |1       |1           |
goat     |1    |1    |1       |1        |1        |1    |1       |1           |
leopard  |1    |1    |1       |1        |1        |1    |1       |1           |
lion     |1    |1    |1       |1        |1        |1    |1       |1           |
lynx     |1    |1    |1       |1        |1        |1    |1       |1           |
mink     |1    |1    |1       |1        |1        |1    |1       |1           |
mongoose |1    |1    |1       |1        |1        |1    |1       |1           |
oryx     |1    |1    |1       |1        |1        |1    |1       |1           |
polecat  |1    |1    |1       |1        |1        |1    |1       |1           |
pony     |1    |1    |1       |1        |1        |1    |1       |1           |
puma     |1    |1    |1       |1        |1        |1    |1       |1           |
pussycat |1    |1    |1       |1        |1        |1    |1       |1           |
raccoon  |1    |1    |1       |1        |1        |1    |1       |1           |
reindeer |1    |1    |1       |1        |1        |1    |1       |1           |
wolf     |1    |1    |1       |1        |1        |1    |1       |1           |

         |milk |aquatic |predator |toothed |backbone |breathes |tail |catsize |
dolphin  |1    |1       |1        |1       |1        |1        |1    |1       |
mink     |1    |1       |1        |1       |1        |1        |1    |1       |
porpoise |1    |1       |1        |1       |1        |1        |1    |1       |
sealion  |1    |1       |1        |1       |1        |1        |1    |1       |

         |hair |milk |predator |toothed |backbone |breathes |tail |hasFourLegs |
boar     |1    |1    |1        |1       |1        |1        |1    |1           |
cheetah  |1    |1    |1        |1       |1        |1        |1    |1           |
leopard  |1    |1    |1        |1       |1        |1        |1    |1           |
lion     |1    |1    |1        |1       |1        |1        |1    |1           |
lynx     |1    |1    |1        |1       |1        |1        |1    |1           |
mink     |1    |1    |1        |1       |1        |1        |1    |1           |
mole     |1    |1    |1        |1       |1        |1        |1    |1           |
mongoose |1    |1    |1        |1       |1        |1        |1    |1           |
opossum  |1    |1    |1        |1       |1        |1        |1    |1           |
polecat  |1    |1    |1        |1       |1        |1        |1    |1           |
puma     |1    |1    |1        |1       |1        |1        |1    |1           |
pussycat |1    |1    |1        |1       |1        |1        |1    |1           |
raccoon  |1    |1    |1        |1       |1        |1        |1    |1           |
wolf     |1    |1    |1        |1       |1        |1        |1    |1           |


