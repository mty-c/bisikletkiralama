�nternet Programc�l��� D�nem Projesi

Grup �yeleri / Github commit isimleri / Github Kullan�c� Adlar�

1-)Muhammed Talha YILMAZ : 02170201062 / Talha Y�lmaz / mty-c
2-)Selahattin YILDIRIM : 02180201061 / DazlakGandalf / SelahattinYildirim1
3-)Ahmet �kbal KAYMAZ : 02180201048 / Misafir / ahmetikbalkaymaz
4-)H�seyin ALTIKULA� : 02180001501 / HuseyinAltikulac / HuseyinAltikulac

Projenin En Verimli Haliyle �al��abilmesi ��in ;

1-)Proje Chrome taray�c�s�nda �al��t�r�lmal�.
2-)�nternete ba�l� bir pc de �al��t�r�lmal�.(Baz� resimler hizliresim ba�lant�s� olarak eklendi�i i�in)
3-)DocumentController i�indeki uploadTo dosya yolu projedeki upload klas�r�ne g�re �al��t�r�lacak pc'de de�i�tirilmeli.
4-)Util i�indeki DBConnection ba�lant�s� �al��t�r�lacak pc'ye g�re d�zenlenmeli.
5-)Admin i�lemlerinin ger�ekle�tirilmesi i�in;

kullan�c� ad� : admin
�ifre : 12345

6-)Dosya i�lemleri admin panel �zerindedir.
7-)Kullan�c� panelinde men�deki se�eneklere t�klad���n�z zaman �r�nleri g�rebilmek i�in scroll u a�a�� indirmeniz gerekmektedir.
8-)AdminPanel �zerindeki documents e t�klad���n�z zaman scroll u a�a�� indirmeniz gerekmektedir.
9-)E�er ekledi�iniz �r�n�n kullan�c�s�n� DEFAULT olarak se�erseniz kullan�c� yok olarak �r�n� ekler.

One to Many �li�kisi : Projedeki onetomany ili�kisi bir �r�n�n bir kullan�c�s� olabilir ama bir kullan�c� birden fazla �r�ne sahip olabilir �eklinde yap�ld�.
Many To Many �li�kisi : Projedeki manytomany ili�kisi bir �r�n�n birden fazla kategorisi olabilir ayn� �ekilde bir kategoride de birden fazla �r�n olabilir �eklinde yap�ld�.
Kullan�c� Yetkisine G�re Sitenin �al��mas� ; Admin olarak giri� yap�ld���nda admin panelin g�z�kmesi , normal kullan�c� giri� yapt���nda ise admin panelin g�z�kmemesi.

1-)Admin Kullan�c� : Admin Panel �zerinde sitedeki kullan�c�lar�n ve �r�nlerin eklenmesi , silinmesi , g�ncellenmesi ve tekil kayd�n okunmas� ger�ekle�tirilebilmektedir. (Tekil kay�t okuma Admin panel �zerindeki kullan�c�lar ve documents k�sm�nda vard�r.)
2-)Normal Kullan�c� : Normal kullan�c� giri� yapt���nda farkl� kategorilerdeki �r�nleri g�zlemleyip kiralama �zelli�ine ve kiralad�ktan sonra iade etme yetkisine sahiptir. Farkl� kullan�c�lar taraf�ndan kiralanm�� �r�nler de sitede listelenmektedir.