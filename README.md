<h1 align="center"> Progetto-Programmazione-Ad-Oggetti </h1>

<h1 align="center"> WeatherApp </h1>
 
<p align="center">
La nostra applicazione permette di avere previsioni principalmente sulla visibilità di una città ma anche su temperatura massima, minima e percepita. Inoltre sarà possibile generare statistiche e filtrarle in base ai giorni di predizione.
</p>

## **Scaletta dei contenuti**

* [Introduzione](#intro)
* [Installazione](#install)
* [Configurazione](#config)
* [Diagrammi UML](#uml)
* [Rotte](#rotte)
* [Rotte Aggiuntive](#rotteaggiuntive)
* [Test](#test)
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
Per accedere al nostro servizio è necessario modificare la variabile ```api_key``` in [ServiceImpl.java](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/WeatherApp/src/main/java/com/project/WeatherApp/service/ServiceImpl.java).
Si può ottenere una API key gratuitamente accedendo alla pagina di [OpenWeather](https://openweathermap.org/forecast5#name5).
Infine basterà avviare il web-server eseguendo [WeatherAppApplication.java](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/WeatherApp/src/main/java/com/project/WeatherApp/WeatherAppApplication.java).

<a name="uml"></a>
## Diagrammi UML

*Use Case Diagram*

![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Use%20Case%20Diagram.jpg?raw=true)

***

*Class Diagram*

![alt text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Progetto%20Class%20Diagram.jpg?raw=true)



* com.project.WeatherApp.controller

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Controller.jpg?raw=true)

* com.project.WeatherApp.model

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Model.jpg?raw=true)

* com.project.WeatherApp.service

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/service.jpg?raw=true)

* com.project.WeatherApp.exception

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/exception.jpg?raw=true)

* com.project.WeatherApp.utils

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/utils.jpg?raw=true)

* com.project.WeatherApp.utils.error

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/error.jpg?raw=true)


***


*Sequance Diagram (parte 1)*

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/History%20Sequence%20Diagram%20part1.jpg?raw=true)


*Sequance Diagram (parte 2)*

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Sequence%20Diagram%20part2.jpg?raw=true)


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
[1](#1) | ` GET ` | `/visibility?cityName=Ancona` | *restituisce un JSONArray contenente le informazioni attuali relative alla visibilità e le previsioni relative per i successivi cinque giorni.*
[2](#2) | ` GET ` | `/saveEveryHour?cityName=Fermo` | *restituisce il path in cui è stato salvato il file contenente le informazioni attuali relative alla visibilità aggiornate ogni ora.*
[3](#3) | ` POST ` | `/statsHistory` | *restituisce le statistiche sulla visibilità con cadenza giornaliera, settimanale o trisettimanale sulla base di uno storico che contiene le informazioni sulla visibilità per 21 giorni (solo alcune città sono ammesse).*
[4](#4) | ` POST ` | `/errors` | *consente di effettuare statistiche sulle previsioni azzeccate, con periodicità che varia da 1 a 5 giorni. Inoltre, in base alla richiesta dell'utente, l'applicazione filtrerà le città che rispettano le condizioni espresse circa la soglia di errore.*


* #### Come può l'utente effettuare richieste? Cosa riceverà in risposta? 

Basta avviare il programma come applicazione SpringBoot, assicurarsi di avere Postman (o simili) e seguire le istruzioni che stiamo per darvi.

Innanzitutto non bisogna confondere le richieste di tipo GET con quelle di tipo POST, altrimenti riceverà un messaggio di errore.
Ora illustreremo alcuni esempi su cosa dare in richiesta e cosa dovete aspettarvi in risposta.

<a name="1"></a>
## 1.   /visibility?cityName=

La prima rotta restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano le informazioni sulla visibilità e la data e l'ora a cui le previsioni si riferiscono. Potete inserire qualsiasi città vogliate (purché esista e sia scritta correttamente, altrimenti riceverete un messaggio di errore).

![alt_text](https://github.com/FedericaParlapiano/WeatherProva/blob/master/Immagini/postman.png?raw=true)

<a name=2></a>
## 2.   /saveEveryHour?cityName=

La seconda rotta vi permette di salvare le informazioni attuali sulla visibilità della città che volete. Il programma creerà un file col nome "HourlyReportcityName.txt" che si aggiornerà ogni ora. Se è già presente un file con lo stesso nome, il programma lo aprirà e, senza eliminare ciò che è presente, inizierà a scrivere le previsioni. Alla fine riceverete un messaggio di questo tipo:

```
Il file è stato salvato in  C:\Users\feder\eclipse-workspace\Progetto\Progetto-Programmazione-Ad-Oggetti\WeatherApp/FermoHourlyReport.txt

```

<a name=3></a>
## 3.    /statsHistory

La terza rotta è di tipo POST e richiede un body di questo tipo:

```
{
    "cities": [
      {
        "name": "Ancona"
      },
      {
        "name": "San Martino in Pensilis"
      },
      {
        "name": "Tolentino"
      }
    ],
    "period": "settimanale"
}
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



<a name=4></a>
## 4.   /errors
La quarta rotta è una POST e richiede un body di questo tipo:
  ```
    {
    "cities": [
      {
        "name": "Ancona"
      },
      {
        "name": "San Martino in Pensilis"
      },
      {
        "name": "Tolentino"
      }
    ],
    "error": 0,
    "value" : "=",
    "period" : 1
}

  ```
  
  - **cities** è il JSONArray che contiene i nomi delle città di cui si vuole fare statistica. Le città ammesse sono Ancona, Campobasso, Macerata, Roma, San Martino in Pensilis e Tolentino. Si può inserire una loro combinazione.
  - **error** rappresenta la soglia di errore con cui vuole filtrare le previsioni l'utente
  - **value** serve per indicare se l'utente voglia ottenere la lista delle città che hanno una soglia di errore maggiore, minore o uguale ad error. Può inserire rispettivamente **$gt**, **$lt** o **=**.
  - **period** indica i giorni di predizione su cui calcolare la soglia di errore. L'utente può inserire un numero intero che va da 1 a 5 (inclusi). 


Questa rotta può generare le seguenti ***eccezioni***: 

   * Nel caso in cui l'utente dimentichi di inserire il nome della città viene generata un'eccezione del tipo ***EmptyStringException*** che restituisce un messaggio di questo tipo:
 
```
Hai dimenticato di inserire la città...
     
```

  * Nel caso in cui l'utente inserisca una città non ammessa viene generata un'eccezione del tipo ***CityNotFoundException*** che restituisce un messaggio di questo tipo:

   ```
    Agrigento non è presente nello storico. Puoi scegliere tra: "Ancona", "Campobasso", "Macerata", "Roma", "San Martino in Pensilis" e "Tolentino".
    
   ```
 
  Se l'utente inserisce tutto correttamente, riceverà un JSONArray in risposta come segue
  
 ```
  
 [

    { 
  
        "previsioni azzeccate su 5": 5,
        
        "City": "Ancona",
        
        "error AME": 0
        
    },
    
    {
    
        "previsioni azzeccate su 5": 5,
        
        "City": "Campobasso",
        
        "error AME": 0
        
    },
    
    {
    
        "<10": "Ancona Campobasso "
        
    }
    
  ]
   
``` 
  
 <a name="rotteaggiuntive"></a>
## Rotte Aggiuntive

Inoltre il nostro programma offre funzionalità aggiuntive. Infatti, se l'utente vuole conoscere le statistiche di una qualsiasi città che non è presente nello storico, può farlo ma limitatamente a un giorno o a cinque giorni. Oltre alle informazioni sulla visibilità, sarà possibile richiedere anche previsioni e statistiche su temperatura massima, minima e percepita, attraverso le seguenti rotte:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[5](#5) | ` GET ` | `/weather?cityName=Tolentino` | *restituisce un JSONObject contenente le previsioni su temperatura massima, minima e percepita e sulla visibilità dal giorno della richiesta ai cinque giorni successivi.*
[6](#6) | ` POST ` | `/stats` | *restituisce un JSONObject contenente le statistiche di un'unica città sui parametri indicati in ingresso su 1 o 5 giorni.*
[7](#7) | ` POST ` | `/filters` | *restituisce il JSONArray che contiene tanti JSONOject quante sono le città specificate nella richiesta(si veda dopo) ogni JSONObject contiene il nome della  città e la media del parametro indicato nella richiesta. In più il JSONArray contiene un altro JSONObject al cui interno è contenuta la più alta/bassa media a seconda del valore indicato in ingresso.*


* #### Come può l'utente effettuare richieste? Cosa riceverà in risposta? 

<a name=5></a>
## 5.   /weather?cityName=

```
localhost:8080/restrictCityWeather?cityName=Tolentino

```
Questa rotta restituisce le previsioni meteo della città indicata come parametro dal giorno in cui si fa la richiesta ai 5 giorni successivi. Le previsioni meteo comprendono:

          - temperatura massima
          
          - temperatura minima
          
          - temperatura percepita
          
          - visibilità.
          
Inoltre si otterranno le informazioni circa la città: 

          - nome
          
          - stato
          
          - coordinate
          
          - id


La risposta è un JSONObject di questo tipo:


```
 {
 
    "Weather": [
    
        {
            "temp_min": 285.38,
            "data": "2020-12-22 12:00:00",
            "visibility": 10000,
            "description": "overcast clouds",
            "main": "Clouds",
            "temp_max": 285.96,
            "feels_like": 284.19
        },
        ...
        {
            "temp_min": 275.63,
            "data": "2020-12-27 09:00:00",
            "visibility": 10000,
            "description": "scattered clouds",
            "main": "Clouds",
            "temp_max": 275.63, 
            "feels_like": 272.19
        }  
    ],
    "country": "IT",
    "name": "Tolentino",
    "coordinates": {
        "latitude": 43.2126,
        "longitude": 13.2901  
    },
    "id": 6541069
}   

```

<a name=6></a>
## 6.   /stats

Questa rotta di tipo POST richiede un body di questo tipo:

```
 {
    	"city" : "Roma",
   		"period" : "today"  
 }
 
```
Period può essere una stringa a scelta tra "today" e "five day", a seconda che si vogliano avere le statistiche su un giorno o su cinque giorni.

Questa rotta può generare la seguente ***eccezione***: 


  * Nel caso in cui l'utente inserisca una stringa in period non ammessa viene generata un'eccezione del tipo ***WrongPeriodException*** che restituisce un messaggio di questo tipo:

   ```
    toda non è ammesso
    
   ```

Se l'utente inserisce tutto correttamente, otterrà un JSONObject di questo tipo:

```

{
    "Temp_Min Average": 274.33500000000004,
    "Feels_like Average": 268.77750000000003,
    "Visibility Data": {
        "Max visibility": 10000,
        "Visibility average": 4214.75,
        "Min Visibility": 67,
        "Visibility variance": 1.25
    },
    "CityName": "Roma",
    "Temp_Max Average": 274.43
}

```


<a name=7></a>
## 7.    /filters

Questa rotta di tipo POST richiede un body di questo tipo:

```
{
    "cities": [
      {
        "name": "Roma"
      },
      {
        "name": "New York"
      },
      {
        "name": "Milano"
      }
    ],
    "param": "feels_like",
    "value": "max",
    "period": 1
}
```

- **cities** è il JSONArray che contiene i nomi delle città di cui si vuole fare statistica. Si possono inserire tutte le città esistenti e in qualsiasi numero.

- **param** rappresenta il parametro su cui si vuole fare statistica. L'utente può inserire:

                - temp_max per conoscere le statistiche sulla temperatura massima
                
                - temp_min per conoscere le statistiche sulla temperatura minima
                
                - feels_like per consoscere le statistiche sulla temperatura percepita
                
                - visibility per conoscere le statistiche sulla visibilità.
                
- **value** indica se l'utente vuole sapere quale delle città inserite abbia il parametro più alto o più basso a seconda che si inserisca, rispettivamente:

                - max, MAX, Max
                
                - min, MIN, Min
                
- **period** indica i giorni su cui fare la predizione. L'utente può inserire o 1 o 5.

Questa rotta può generare le seguenti ***eccezioni***: 

   * Nel caso in cui l'utente inserisca un numero diverso da 1 o 5, viene generata una ***WrongPeriodException***, che restituisce un messaggio di questo tipo:
   
     ```
     7 non è un numero ammesso. Inserisci un numero che sia o 1 o 5.
     
     ```

  * Nel caso in cui l'utente inserisca una città non ammessa viene generata una ***WrongParamException***, che restituisce un messaggio di questo tipo:

    ```
    temp non è una stringa ammessa.Inserisci una stringa tra temp_min,temp_max,feels_like e visibility.
    
    ```
  * Nel caso in cui l'utente inseriscsa un valore non ammesso, viene generata una ***WrongValueException***, che restituisce un messaggio di questo tipo:
  
     ```
     buu è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min
   
     ```
  
  Se l'utente inserisce tutto correttamente, otterrà un JSONOArray di questo tipo:
 
 ```
 [

    {​​

        "feels_like_average:": 268.0466666666666,

        "cityName:": "Roma"

    }​​,

    {​​

        "feels_like_average:": 270.74,

        "cityName:": "New York"

    }​​,

    {​​

        "feels_like_average:": 280.3766666666666,

        "cityName:": "Milano"

    }​​,

    {​​

        "max average": 280.3766666666666,

        "City with max average": [

            "Milano"

        ]

    }​​

]

 ```

<a name="test"></a>
## Test

Abbiamo implementato i seguenti [test](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/tree/master/WeatherApp/src/test/java/com/project/WeatherApp) per verificare il corretto funzionamento di alcuni metodi e alcune eccezioni

![alt_text](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/blob/master/UML/Test.jpg)



<a name="doc"></a>
## Documentazione
Il codice java è interamente documentato in [Javadoc](https://github.com/FedericaParlapiano/Progetto-Programmazione-Ad-Oggetti/tree/master/WeatherApp/doc).

<a name="autor"></a>
### Autori
Progetto realizzato da:
- Federica Parlapiano (50%)
- Francesca Palazzetti (50%)

