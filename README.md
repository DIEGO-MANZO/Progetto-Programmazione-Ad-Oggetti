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
![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Use%20Case%20Diagram.jpg)
*Use Case Diagram*
***



![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Class%20Diagram.jpg)
*Class Diagram*
***



![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Sequence%20Diagram.jpg)
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
3 | ` POST ` | `/statsHistory` | *restituisce le statistiche sulla visibilità con cadenza giornaliera, settimanale o trisettimanale sulla base di uno storico che contiene le informazioni sulla visibilità per 21 giorni (solo alcune città sono ammesse).*
4 | ` POST ` | `/errors` | *consente di effettuare statistiche sulle previsioni azzeccate, con periodicità che varia da 1 a 5 giorni. Inoltre, in base alla richiesta dell'utente, l'applicazione filtrerà le città che rispettano le condizioni espresse circa la soglia di errore.*


* #### Come può l'utente effettuare richieste? Cosa riceverà in risposta? 

Basta avviare il programma come applicazione SpringBoot, assicurarsi di avere Postman (o simili) e seguire le istruzioni che stiamo per darvi.

Innanzitutto non bisogna confondere le richieste di tipo GET con quelle di tipo POST, altrimenti riceverà un messaggio di errore.
Ora illustreremo alcuni esempi su cosa dare in richiesta e cosa dovete aspettarvi in risposta.

1 - La prima rotta restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano le informazioni sulla visibilità e la data e l'ora a cui le previsioni si riferiscono. Potete inserire qualsiasi città vogliate (purché esista e sia scritta correttamente, altrimenti riceverete un messaggio di errore).

![alt_text](https://github.com/FedericaParlapiano/WeatherProva/Immagini/postman.png)


2 - La seconda rotta vi permette di salvare le informazioni attuali sulla visibilità della città che volete. Il programma creerà un file col nome "HourlyReportcityName.txt" che si aggiornerà ogni ora. Se è già presente un file con lo stesso nome, il programma lo aprirà e, senza eliminare ciò che è presente, inizierà a scrivere le previsioni. Alla fine riceverete un messaggio di questo tipo:



Inoltre il nostro programma offre funzionalità aggiuntive. Infatti, se l'utente vuole conoscere le statistiche di una qualsiasi città che non è presente nello storico, può farlo ma limitatamente a un giorno o a cinque giorni. Oltre alle informazioni sulla visibilità, sarà possibile richiedere anche previsioni e statistiche su temperatura massima, minima e percepita, attraverso le seguenti rotte:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
5 | ` GET ` | `/restrictCityWeather?cityName="Tolentino` | *restituisce un JSONObject contenente le previsioni dal giorno della richiesta ai tre giorni.*
6 | ` POST ` | `/stats` | *restituisce un JSONObject contenente le statistiche di un'unica città sui parametri indicati in ingresso su 1 o 5 giorni.*
7 | ` POST ` | `/filters` | *restituisce il JSONArray che contiene tanti JSONOject quante sono le città specificate nella richiesta(si veda dopo) ogni JSONObject contiene il nome della  città e la media del parametro indicato nella richiesta. In più il JSONArray contine un altro JSONObject al cui interno è contenuta la più alta/bassa media a seconda del valore indicato in ingresso.*







<a name="doc"></a>
## Documentazione
Il codice java è interamente documentato in [Javadoc].

<a name="autor"></a>
### Autori
Progetto realizzato da:
- Federica Parlapiano (50%)
- Francesca Palazzetti (50%)

