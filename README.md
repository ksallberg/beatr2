Allra först, clona beatr från heffaklump

gå till rooten, skriv:

git submodule init
git submodule update

Nu i eclipse:

* Först, importera PdCore:

File -> Import Existing projects into workspace

	Välj mappen PdCore
	
	Lägg till src, bin, gen, jni/libpd/java i BuildPath Source
	
	Lägg till "Android Classpath Container" från Add library i Libraries

* Sen, till samma workspace som PdCore, importera BeatrProj
	
File -> Import Existing projects into workspace

	Välj mappen BeatrProj
	
	Lägg till src, bin, gen i BuildPath Source
	
	Lägg till "Android Classpath Container" från Add library i Libraries
	
++++

Nu, skapa en run i eclipse. 

Android Application
