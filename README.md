# Dizajnerski obrasci - IT68/2019

## Opis zahteva za projekat iz predmeta Dizajnerski obrasci za 2021/2022

Korišćenjem _Java Swing_ implementirati desktop aplikaciju za rad sa 2D grafikom. Aplikacija mora podržavati funkcionalnosti koje su rađene u projektnom zadatku na predmetu Objektno orijentisane informacione tehnologije.
Izmene/Dodatne funkcionalnosti:

- [x] 1. crtanje oblika različitim bojama (boja ivice i boja unutrašnjosti) za šta je potrebno koristiti _JColorChooser_ klasu,
- [x] 2. unutrašnjost oblika krug sa rupom treba da bude transparentna (java.awt.Graphics2D, java.awt.Shape, java.awt.geom.Area, java.awt.geom.Ellipse2D),
- [x] 3. nazivi klasa, metoda i promenljivih moraju biti na engleskom jeziku,
- [x] 4. aplikacija mora biti realizovan u skladu sa MVC arhitektonskim obrascem,
- [x] 5. dodavanje, brisanje i modifikacija šestougla (hexagon) koristeći Adapter obrazac za hexagon.jar,
- [x] 6. poništavanje izvršenih komandi (_undo_ funkcionalnost) – _Command_ i _Prototype_ obrazac, ponovno izvršenje poništenih komandi (_redo_ funkcionalnost) – _Command_ i _Prototype_ obrazac, _undo_ i _redo_ dugme treba da budu dostupni samo kada je dostupna i funkcionalnost,
- [ ] 7. generisanje i prikaz loga izvršenih komandi u okviru aplikacije,
- [ ] 8. zapis u tekstualnu datoteku loga izvršenih komandi na eksterni memorijski medijum, zapis kompletnog crteža (_Serialization_) na eksterni memorijski medijum, - _Strategy_ obrazac,
- [ ] 9. učitavanje tekstualne datoteke koja sadrži log izvršenih komandi i na osnovu sadržaja, kreiranje odgovarajućeg crteža, komandu po komandu u interakciji sa korisnikom, učitavanje kompletnog crteža,
- [x] 10. promenu pozicije oblika po Z osi, _To Front_ (pozicija po pozicija), _To Back_ (pozicija po pozicija), _Bring To Front_ (na najvišu poziciju), _Bring To Back_ (na najnižu poziciju),
- [x] 11. prikazati trenutno aktivne boje za crtanje ivice i popunjavanje oblika, klikom na boju, otvara se dijalog sa mogućnošću promene trenutno aktivne boje,
- [x] 12. omogućiti selekciju više oblika,
- [x] 13. dugme za brisanje treba da bude dostupno samo u slučaju da postoje selektovani objekti – _Observer_ obrazac,
- [x] 14. dugme za modifikaciju treba da bude dostupan samo u slučaju kada je selektovan samo jedan oblik – _Observer_ obrazac.

### Dodatna objašnjenja

Predmetni projekat **nije predviđen kao grupni rad**, tako da svaki student treba projekat da uradi u potpunosti samostalno. Jedan projekat može da brani samo jedan student.
Tokom rada na projektu obavezno je korišćenje _GitHub_-a, što podrazumeva da se očekuje da kako uspešno uradite ili pokušate da uradite neki deo zadatka, to i _commit_-ujete u vaš repozitorijum. Takođe, obavezno je unošenje i poruke koja objašnjava promene nastele u konkretnom _commit_-u. Dakle, neophodno je da tok izrade projekta bude vidljiv kroz _commit_-e. Projekti koji ne ispune ovaj uslov (npr. samo jedan ili par _commit_-a, svi _commit_-i u kratkom vremenskom periodu jedan za drugim) **neće biti prihvaćeni za odbranu**.

Kod MVC obrasca, kao što je pokazivano na vežbama, model je klasa u kojoj je lista oblika koji se iscrtavaju, a view je _JPanel_ u kojem se ti oblici iscrtavaju. Takođe, na vežbama je pokazan jedan način kako se model, view i controller mogu međusobno referencirati, i koja klasa bi trebalo da ima referencu na koju od klasa iz ovog obrasca. Rešenje u kojem MVC klase kreirate koristeći _Singleton_ dizajnerski obrazac, te koristite globalne promenljive za referenciranje instanci klasa **nije prihvatljivo**.

### Pojašnjenja funkcionisanja aplikacije:

- sve aktivnosti korisnika vezano za crtanje treba da budu sačuvane u log-u, to podrazumeva i logovanje selekcije, kao i _To Front_, _To Back_, _Bring To Front_ i _Bring To Back_ funkcionalnosti, _undo_ i _redo_.

- selektovani oblik, nakon modifikacije, treba da ostane selektovan.
