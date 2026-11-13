# Relazione

Ingegneria dei dati 2026/2027
Studente: Scilla Falcioli - 589383
Repository del progetto: https://github.com/Shikooo8/ING-DATI-HW3


Ho scelto di dividere le due funzioni principali di Inicizzazione e Ricerca in due classi java diverse. I campi analizzati sono il "titolo", ovvero il nome del file e "contenuto" con ovviamente il suo contenuto.


## Ananyzer scelto
Ho scelto di prendere un PerFieldAnalyzerWrapper, ovvero che avesse dei vettori diversi per i due campi. Per il campo "titolo"  un Analyzer personalizzato (CustomAnalyzer) composto da:tokenizer whitespace, filtro wordDelimiterGraph, filtro flattenGraph e filtro lowercase.Questi sono utili specialmente per controlli gli spazi bianchi e soprattuto se ci siano parole composte con simboli, visto che è frequente nei titoli. Mentre per il contenuto ho pensato che un sempice analyzer per la lingua italiana fosse sufficiente. 


## Query
Ho preso 6 file scritti a mano e altri 50 scritti in modo randomico su temi come meteo, calcio e università, così da avere un range variegato, ma arricurandomi delle intersezioni per avere score non tutti uguali. Le query sono composte con il nome del campo e la string ada cercaregt

### Numero e tempo
Il numero e tempo sono stati registrati dal programma, ovvero 56 file in 0.453 secondi.


### Risultato delle Query

```
File incicizzati: 56 in 453 ms
Sintassi: titolo <termini>  |  contenuto <termini>
Frase esatta tra virgolette. Esempi:
  titolo gita
  contenuto "viaggio a roma"
Scrivi 'esci' per terminare.
> autunno
Query non valida: inizia con 'titolo' o 'contenuto'.
> titolo autunno
8 hits risultati
1. meteo_autunno_43.txt (score 0.854)
2. meteo_autunno_11.txt (score 0.854)
3. meteo_autunno_13.txt (score 0.854)
4. meteo_autunno_48.txt (score 0.854)
5. meteo_autunno_47.txt (score 0.854)
6. meteo_autunno_05.txt (score 0.854)
7. meteo_autunno_23.txt (score 0.854)
8. meteo_autunno_29.txt (score 0.854)
> titolo autunno_29
8 hits risultati
1. meteo_autunno_29.txt (score 2.487)
2. meteo_autunno_43.txt (score 0.854)
3. meteo_autunno_11.txt (score 0.854)
4. meteo_autunno_13.txt (score 0.854)
5. meteo_autunno_48.txt (score 0.854)
6. meteo_autunno_47.txt (score 0.854)
7. meteo_autunno_05.txt (score 0.854)
8. meteo_autunno_23.txt (score 0.854)
> contenuto autunno
10 hits risultati
1. meteo_autunno_29.txt (score 0.885)
2. meteo_autunno_05.txt (score 0.819)
3. universita_esami_22.txt (score 0.804)
4. ricetta_pasta_19.txt (score 0.776)
5. meteo_autunno_11.txt (score 0.763)
6. meteo_autunno_13.txt (score 0.737)
7. meteo_autunno_48.txt (score 0.737)
8. programmazione_java_41.txt (score 0.713)
9. meteo_autunno_47.txt (score 0.691)
10. programmazione_java_18.txt (score 0.680)
> titolo Roma
6 hits risultati
1. gita_roma_06.txt (score 0.975)
2. gita_roma_10.txt (score 0.975)
3. gita_roma_25.txt (score 0.975)
4. gita_roma_50.txt (score 0.975)
5. gita_roma_14.txt (score 0.975)
6. gita_roma_12.txt (score 0.975)
> contenuto mare
2 hits risultati
1. Gita.txt (score 2.152)
2. Diario.txt (score 2.097)
> contenuto roma
18 hits risultati
1. gita_roma_12.txt (score 0.779)
2. gita_roma_50.txt (score 0.772)
3. gita_roma_14.txt (score 0.740)
4. gita_roma_06.txt (score 0.728)
5. gita_roma_25.txt (score 0.668)
6. programmazione_java_36.txt (score 0.625)
7. programmazione_java_15.txt (score 0.625)
8. meteo_autunno_23.txt (score 0.588)
9. universita_esami_40.txt (score 0.545)
10. calcio_serie_a_49.txt (score 0.535)
> titolo universita
12 hits risultati
1. universita_esami_22.txt (score 0.681)
2. universita_esami_37.txt (score 0.681)
3. universita_esami_34.txt (score 0.681)
4. universita_esami_40.txt (score 0.681)
5. universita_esami_03.txt (score 0.681)
6. universita_esami_17.txt (score 0.681)
7. universita_esami_02.txt (score 0.681)
8. universita_esami_35.txt (score 0.681)
9. universita_esami_08.txt (score 0.681)
10. universita_esami_01.txt (score 0.681)
> contenuto preparare insieme ricetta
15 hits risultati
1. universita_esami_34.txt (score 1.362)
2. universita_esami_31.txt (score 1.362)
3. meteo_autunno_05.txt (score 1.261)
4. universita_esami_22.txt (score 1.238)
5. universita_esami_37.txt (score 1.135)
6. universita_esami_03.txt (score 1.135)
7. universita_esami_02.txt (score 1.135)
8. universita_esami_16.txt (score 1.135)
9. universita_esami_17.txt (score 1.116)
10. gita_roma_25.txt (score 1.098)
> contenuto temperatura dell'acqua
20 hits risultati
1. ricetta_pasta_42.txt (score 1.388)
2. meteo_autunno_23.txt (score 0.885)
3. meteo_autunno_29.txt (score 0.885)
4. meteo_autunno_05.txt (score 0.819)
5. meteo_autunno_11.txt (score 0.763)
6. meteo_autunno_43.txt (score 0.737)
7. meteo_autunno_13.txt (score 0.737)
8. meteo_autunno_48.txt (score 0.737)
9. ricetta_pasta_20.txt (score 0.734)
10. programmazione_java_33.txt (score 0.722)
> contenuto universita
0 hits risultati
> contenuto i ragazzi di sono divertiti molto
21 hits risultati
1. universita_esami_01.txt (score 0.520)
2. calcio_serie_a_49.txt (score 0.463)
3. programmazione_java_32.txt (score 0.463)
4. calcio_serie_a_21.txt (score 0.455)
5. gita_roma_10.txt (score 0.447)
6. calcio_serie_a_04.txt (score 0.447)
7. programmazione_java_33.txt (score 0.439)
8. calcio_serie_a_28.txt (score 0.432)
9. calcio_serie_a_27.txt (score 0.425)
10. universita_esami_37.txt (score 0.425)
> 
```