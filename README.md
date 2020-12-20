<h1 align="center"> Progetto-Programmazione-Ad-Oggetti </h1>

<h1 align="center"> WeatherApp </h1>
 
<p align="center">
Applicazione che permette di avere previsioni principalmente sulla visibilità di una città ma anche su temperatura massima, minima e percepita. Inoltre sarà possibile generare statistiche e filtrarle in base ai giorni di predizione.
</p>

## **Scaletta dei contenuti**

* [Introduzione](#intro)
* [Installazione](#install)
* [Configurazione](#config)
* [Diagrammi UML](#uml)
* [Rotte](#rotte)
* [Statistiche](#stats)
* [Esempio di utilizzo](#edu)
* [Struttura progetto](#strutt)
* [Documentazione](#doc)

<a name="intro"></a>
## Introduzione

Il programma WeatherApp offre diverse possibilità. Si concetra principalmente sulle previsioni della visibilità di una città e le relative statistiche, ma offre anche il confronto tra più città per conoscere l'affidabilità delle previsioni. Inoltre è possibile conoscere anche le previsioni sulla temperatura massima, minima e percepita e di farne statistiche.


<a name="install"></a>
## Installazione
WeatherApp è installabile dal Prompt dei Comandi digitando:  
```
git clone https://github.com/FedericaParlapiano/WeatherProva 
```
<a name="config"></a>
## Configurazione
Per accedere al nostro servizio è necessario modificare la variabile ```api_key``` in [ServiceImpl.java] 
(Progetto-Programmazione-Ad-Oggetti/WeatherApp/src/main/java/com/project/WeatherApp/service/ServiceImpl.java).
Si può ottenere una API key gratuitamente accedendo alla pagina (https://openweathermap.org/forecast5#name5).
Infine basterà avviare il web-server eseguendo 
(Progetto-Programmazione-Ad-Oggetti/WeatherApp/src/main/java/com/project/WeatherApp/WeatherAppApplication.java).

<a name="uml"></a>
## Diagrammi UML
![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Use%20Case%20Diagram.jpg)
*Use Case Diagram*
***



![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Class%20Diagram.jpg)
*Class Diagram*
***



![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Sequence%20Diagram.jpg)
*Sequence Diagram. 
***


## Documentazione
Il codice java è interamente documentato in [Javadoc].

### Autori
Progetto realizzato da:
- Federica Parlapiano (50%)
- Francesca Palazzetti (50%)

