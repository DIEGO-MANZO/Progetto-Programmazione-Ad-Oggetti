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
* [Autori](#autor)

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
![alt text](https://raw.githubusercontent.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Use%20Case%20Diagram.jpg)
*Use Case Diagram*
***



![alt text](https://raw.githubusercontent.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Class%20Diagram.jpg)
*Class Diagram*
***



![alt_text](https://raw.githubusercontent.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Sequence%20Diagram.jpg)
*Sequence Diagram* 
***


<a name="rotte"></a>
## Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo
```
localhost:8080/
```
Le rotte definite sono le seguenti:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
1 | ` GET ` | `/visibility?cityName="Ancona"` | *restituisce un JSONArray contenente le informazioni attuali relative alla visibilità e le previsioni relative per i successivi cinque giorni.*
2 | ` GET ` | `/saveEveryHour?cityName="Fermo"` | *restituisce il path in cui è stato salvato il file contenente le informazioni attuali relative alla visibilità aggiornate ogni ora.*
3 | ` GET ` | `/todayAverage?cityName="Tolentino"` | *restituisce un JSONObject contenente la media della temperatura massima, minima, percepita e la media, la massima, la minima e la varianza di visibilità del giorno corrente.*
4 | ` GET ` | `/fiveDayAverage?cityName="San Martino in Pensilis"` | *restituisce un JSONObject contenente la media della temperatura massima, minima, percepita e la media, la massima, la minima e la varianza di visibilità relative ai cinque giorni successivi.*
5 | ` POST ` | `/filters` | *restituisce il JSONArray che contiene tanti JSONOject quante sono le città specificate nella richiesta(vedi dopo) ogni JSONObject contine il nome della  città e la media del parametro indicato nella richiesta. In più il JSONArray contine un altro JSONObject al cui interno è contenuta la più alta/bassa media a seconda del valore indicato in ingresso.*
6 | ` POST ` | `/filtersHistory` | *filtra le statistiche sulla visibilità in base ad una soglia di errore.*

1 -restituisce un JSONArray di questo tipo 
![alt_text](https://github.com/FedericaParlapiano/WeatherProva/blob/master/Immagini/1(1).png)

![alt_text](https://github.com/FedericaParlapiano/WeatherProva/blob/master/Immagini/1(2).png)













<a name="doc"></a>
## Documentazione
Il codice java è interamente documentato in [Javadoc].

<a name="autor"></a>
### Autori
Progetto realizzato da:
- Federica Parlapiano (50%)
- Francesca Palazzetti (50%)

