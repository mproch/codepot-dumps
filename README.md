Jak widać...

Czego używaliśmy?

* jps - pokazuje PIDy procesów Javy
* jcmd - jw., ale umożliwia także wydawanie poleceń takich jak:
  * jcmd \<PID\> GC.class_histogram - histogram klas
  * jcmd \<PID\> GC.heap_dump \<PATH\> - zrzut heapa do podanego pliku
  * jcmd \<PID\> Thread.print - zrzut wątków
  * jcmd \<PID\> help - lista dostępnych poleceń
* jmap -histo <PID> - histogram
* jmap -dump:live,file=\<PATH\> \<PID\> - zrzut heapa
* ps -eLf - lista procesów razem z ich wątkami
* kill -3 \<PID\> - zrzut wątków do stdout procesu javy
* jstack \<PID\> - zrzut wątków do stdout
* jvisualvm - do wszystkiego ;)
* top -Hp <PID> - rozbicie zużycia CPU w rozbiciu na wątki procesu
