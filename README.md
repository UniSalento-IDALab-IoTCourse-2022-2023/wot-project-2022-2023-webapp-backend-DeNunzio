# Back-End Dashboard 

I# Back-End Dashboard

Il back-end della Web-Application ha il ruolo di esporre le dashboard che evidenziano le anomalie. Il codice è scritto in Java, utilizzando il framework Spring. Il lato server dell'applicazione ha la responsabilità di memorizzare i percorsi e salvare le anomalie in tempo reale dopo che vengono analizzate. Il back-end è affiancato dal database NoSQL MongoDB per memorizzare i dati.

Gli endpoint principali sono:

### /api/addRoute

Tramite una POST con un json come segue a questo endpoint si crea un nuovo percorso in cui si specifica quali sono i range che le Box dovrebbero rispettare.

```json
{
    "description": "Trasporto di cibo pronto",
    "anomalies": [
        {
            "type": "temperature",
            "minValue": 25,
            "maxValue": 35
        },
        {
            "type": "humidity",
            "minValue": 20,
            "maxValue": 50
        },
        {
            "type": "pressure",
            "minValue": 900,
            "maxValue": 1100
        },
        {
            "type": "co2",
            "minValue": 300,
            "maxValue": 500
        },
        {
            "type": "light",
            "minValue": 0,
            "maxValue": 1
        },
        {
            "type": "horizontal",
            "minValue": 0,
            "maxValue": 1
        }
    ]
}


Questa operazione comunicherà nel back-end l’informazione che è iniziata una nuova corsa, e salverà automaticamente l’orario di inizio.
Il valore ritornato da questa richiesta è l’ID della route, che sarà necessario nei prossimi endpoint.
● /api/addAnomaly
Ogni volta che lo script con il compito di analizzare l’ambiente (visto nel paragrafo precedente) trova un’anomalia, invia tramite WebSocket l’informazione alla mobile application. Questa applicazione si occuperà di notificare questo backend dell’anomalia tramite una POST a questo endpoint con un json del tipo:
{
"routeId" : "64b2bff646e15f35df88d95a", "type": "temperature",
"value": "24",
"time": "18:25"
}
Il json contiene informazioni sulla corsa a cui fa riferimento, sul tipo di anomalia, sul valore misurato e sull’orario a cui è successo.
Questa operazione deve essere eseguita ogni volta che si trova un’anomalia, così che la dashboard possa aggiornarsi in tempo reale.
● /api/endRoute
Quando un percorso finisce, tramite questo endpoint si invia l’informazione al back-end, il quale salva automaticamente l’orario di fine corsa.
{
"id" : "64b2bff646e15f35df88d95a"
}
● /api/getRoutes
Questo endpoint ritorna tutte le route salvate nel database tramite un json come nella pagina seguente, completo di orario di inizio, orario di fine, e dettagli sulle anomalie riscontrate.

{
"id": "64b2bff646e15f35df88d95a",
"date": "15-07-2023",
"startClock": "17:49:10",
"endClock": "17:51:53",
"description": "Trasporto di cibo pronto", "anomalies": [
{
"type": "temperature", "time": [
            "18:00",
            "18:10",
            "18:25"
        ],
        "minValue": 25,
        "maxValue": 35,
        "value": [
            "20",
            "22",

          "24"
] },
{
"type": "humidity", "time": [
            "18:06",
            "18:20",
            "18:25"
        ],
        "minValue": 20,
        "maxValue": 50,
        "value": [
            "60",
            "65",
            "60"
] }
] }



## Architettura
L’architettura scelta è composta dalle seguenti componenti:

  ●	Nordic Thingy:52: questo è il dispositivo alla base del prototipo che si occupa delle misurazioni dei valori ambientali, e dell’invio di queste tramite BLE al Raspberry Pi. 
  
  ●	Raspberry Pi: Il Raspberry Pi riceve i dati misurati dal Thingy via BLE, e li analizza. Se questo trova delle anomalie dei valori rispetto ai range prestabiliti, invia questa informazione tramite WebSocket al gateway (ovvero lo smartphone del conducente).  
  
  ●	Smartphone del conducente: Il telefono del conducente ha il ruolo di gateway. Sullo smartphone gira un applicazione che permette di avviare una corsa, farla terminare, e ricevere tutte le anomalie riscontrate. Ogni volta che riceve l’informazione di un'anomalia via WebSocket da parte del Raspberry, la notifica al conducente, e la comunica alla dashboard in cloud.
  
  ●	Dashboard: La dashboard mette a disposizione un'interfaccia utente che consente di visualizzare i dati raccolti dal sistema IoT in modo chiaro e comprensibile. Attraverso la dashboard, gli utenti possono monitorare lo stato dei parametri rilevati per visualizzare eventuali anomalie. La dashboard offre funzionalità avanzate come grafici interattivi e filtri per migliorare la comprensione dei dati. 
  
  ●	Cloud: Il cloud rappresenta l'infrastruttura server remota che ospita i servizi di backend necessari per la web application con l’obiettivo di mostrare le dashboard dei percorsi. 

<p align="center">
  <img src="./architecture.png" alt="" style="display: block; margin: 0 auto;" />
</p>
  
Questa architettura permette una comunicazione efficiente tra i diversi componenti del sistema, consentendo una gestione tempestiva delle anomalie rilevate dai sensori. I dati vengono acquisiti, analizzati, inviati al telefono del conducente, e infine archiviati e visualizzati nel cloud attraverso una dashboard intuitiva.

## Collegamenti agli altri componenti
- [WOT - Ionic App](https://github.com/UniSalento-IDALab-IoTCourse-2022-2023/wot-project-2022-2023-ionicApp-Mele)
- [WOT - Front End Dashboard](https://github.com/UniSalento-IDALab-IoTCourse-2022-2023/wot-project-2022-2023-FrontEndAngular-Mele)
- [WOT - Back End Dashboard](https://github.com/UniSalento-IDALab-IoTCourse-2022-2023/wot-project-2022-2023-webapp-backend-DeNunzio)
- [WOT - Back End Raspberry (Nordic)](https://github.com/UniSalento-IDALab-IoTCourse-2022-2023/wot-project-backend-nordic-pi-DeNunzio)
  

