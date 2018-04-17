# di-thegoodparts-tdd-milano
Caso di Studio "Correttore Ortografico" per il Talk "Dependency injection: The Good Parts" (16/04/2018 TDD Milano)

## Files di configurazione del Container di Spring

File di configurazione dell'injector (Bean Configuration)
```
/di-thegoodparts-tdd-milano/src/main/resources/injector-config.xml
```
Properties utilizzate nell'injector
```
/di-thegoodparts-tdd-milano/src/main/resources/injector.properties
```

## Configurazione dei Profili
Per avviare l'applicazione è necessario indicare dei profili validi per l'injector

I profili disponibili sono i seguenti:
* TEST
* PRODUCTION
* DYNAMIC_APP
* STATIC_APP

E' possibile impostare solo un profilo tra TEST e PRODUCTION ed un solo profilo tra DYNAMIC_APP e STATIC_APP

Di seguito vengono elencate le possibili configurazioni da passare come variabile d'ambiente:
```
-Dspring.profiles.active=PRODUCTION,STATIC_APP
```
```
-Dspring.profiles.active=PRODUCTION,DYNAMIC_APP
```
```
-Dspring.profiles.active=TEST,STATIC_APP
```
```
-Dspring.profiles.active=TEST,DYNAMIC_APP
```
## Main class di esempio

```
groppedev.dithegoodparts.application.main.launcher.ApplicationStaticLauncher
```
Combinazioni di profili validi:
```
-Dspring.profiles.active=TEST,STATIC_APP 
```
o
```
-Dspring.profiles.active=PRODUCTION,STATIC_APP 
```

## Suite Test JUnit
```
groppedev.dithegoodparts.test.junit.JUnitTest
```

## Suite Test di Integrazione
```
groppedev.dithegoodparts.test.integration.IntegrationTest
```
