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
Si può ottenere una API key gratuitamente accedendo alla pagina https://openweathermap.org/forecast5#name5.
Infine basterà avviare il web-server eseguendo 
(Progetto-Programmazione-Ad-Oggetti/WeatherApp/src/main/java/com/project/WeatherApp/WeatherAppApplication.java).

<a name="uml"></a>
## Diagrammi UML
![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Use%20Case%20Diagram.jpg?raw=true)
*Use Case Diagram*
***



![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Class%20Diagram.jpg?raw=true)
*Class Diagram*
***



![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Sequence%20Diagram.jpg?raw=true)
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

![alt_text](https://github.com/FedericaParlapiano/WeatherProva/blob/master/Immagini/postman.png?raw=true)


2 - La seconda rotta vi permette di salvare le informazioni attuali sulla visibilità della città che volete. Il programma creerà un file col nome "HourlyReportcityName.txt" che si aggiornerà ogni ora. Se è già presente un file con lo stesso nome, il programma lo aprirà e, senza eliminare ciò che è presente, inizierà a scrivere le previsioni. Alla fine riceverete un messaggio di questo tipo:

```
Il file è stato salvato in C:/Users/feder/eclipse-workspace/RovaniemiHourlyReport.txt     DA SISTEMARE

```

3 - La terza rotta è di tipo POST e richiede un body di questo tipo:

```
{​​

    "cities": [

      {​​

        "name": "Ancona"

      }​​,

      {​​

        "name": "Campobasso"

      }​​

    ],

    "period": "settimanale"

}​​
```

- **cities** è il JSONArray che contiene i nomi delle città di cui si vuole fare statistica. Le città ammesse sono Ancona, Campobasso, Macerata, Roma, San Martino in Pensilis e Tolentino. Si può inserire una loro combinazione.
- **period** rappresenta la periodicità con cui si può fare la statistica. Si può scegliere tra "giornaliera", "settimanale" o "trisettimanale".

Questa rotta può generare le seguenti ***eccezioni***: 

   * Nel caso in cui l'utente dimenticasse di inserire il nome della città viene generata un'eccezione del tipo ***EmptyStringException*** che restituisce un messaggio di questo tipo:
   
    ```
     Hai dimenticato di inserire la città...
    ```

  * Nel caso in cui l'utente inserisca una città non ammessa viene generata un'eccezione del tipo ***CityNotFoundException*** che restituisce un messaggio di questo tipo:

   ```
    New York non è presente nello storico. Puoi scegliere tra: "Ancona", "Campobasso", "Macerata", "Roma", "San Martino in Pensilis" e "Tolentino".
   ```

   * Invece se viene inserito un periodo diverso da quelli sopra citati viene generata un'eccezione del tipo ***WrongPeriodException*** che restituisce un messaggio di questo tipo:

   ```
    mensile non è permessa. Devi inserire una stringa tra "giornaliera","settimanale" e "trisettimanale".
   ```

Se l'utente inserisce tutto correttamente, riceverà un JSONArray in risposta come segue

   ```
[

    [

        "Ancona",

        {​​

            "average": 9779,

            "week": 1,

            "min": 4233,

            "max": 10000,

            "variance": 1

        }​​,

        {​​

            "average": 9839,

            "week": 2,

            "min": 4233,

            "max": 10000,

            "variance": 1

        }​​,
      
      ...
   ]
   
   ```



4 - La quarta rotta è una POST e richiede un body di questo tipo:
  ```
    {​​
         "cities": [
           
           {​​
             
             "name": "Tolentino"
            
            }​​,
           
           {​​
            
            "name": "San Martino in Pensilis"
           
           }​​,

        ],
       
       "error": 1,
       
       "value": "$gt",
       
       "period": 3
}​​

  ```
  
  - **cities** è il JSONArray che contiene i nomi delle città di cui si vuole fare statistica. Le città ammesse sono Ancona, Campobasso, Macerata, Roma, San Martino in Pensilis e Tolentino. Si può inserire una loro combinazione.
  - **error** rappresenta la soglia di errore con cui vuole filtrare le previsioni l'utente
  - **value** serve per indicare se l'utente voglia ottenere la lista delle città che hanno una soglia di errore maggiore, minore o uguale ad error. Può inserire rispettivamente **$gt**, **$lt** o **=**.
  - **period** indica i giorni di predizioni su cui calcolare la soglia di errore. L'utente può inserire un numero intero che va da 1 a 5 (inclusi). 


Questa rotta può generare le seguenti ***eccezioni***: 

   * Nel caso in cui l'utente dimenticasse di inserire il nome della città viene generata un'eccezione del tipo ***EmptyStringException*** che restituisce un messaggio di questo tipo:
   
    ```
     Hai dimenticato di inserire la città...
    ```

  * Nel caso in cui l'utente inserisca una città non ammessa viene generata un'eccezione del tipo ***CityNotFoundException*** che restituisce un messaggio di questo tipo:

   ```
    Agrigento non è presente nello storico. Puoi scegliere tra: "Ancona", "Campobasso", "Macerata", "Roma", "San Martino in Pensilis" e "Tolentino".
    
   ```
 
  


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

