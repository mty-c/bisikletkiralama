Ýnternet Programcýlýðý Dönem Projesi

Grup Üyeleri / Github commit isimleri / Github Kullanýcý Adlarý

1-)Muhammed Talha YILMAZ : 02170201062 / Talha Yýlmaz / mty-c
2-)Selahattin YILDIRIM : 02180201061 / DazlakGandalf / SelahattinYildirim1
3-)Ahmet Ýkbal KAYMAZ : 02180201048 / Misafir / ahmetikbalkaymaz
4-)Hüseyin ALTIKULAÇ : 02180001501 / HuseyinAltikulac / HuseyinAltikulac

Projenin En Verimli Haliyle Çalýþabilmesi Ýçin ;

1-)Proje Chrome tarayýcýsýnda çalýþtýrýlmalý.
2-)Ýnternete baðlý bir pc de çalýþtýrýlmalý.(Bazý resimler hizliresim baðlantýsý olarak eklendiði için)
3-)DocumentController içindeki uploadTo dosya yolu projedeki upload klasörüne göre çalýþtýrýlacak pc'de deðiþtirilmeli.
4-)Util içindeki DBConnection baðlantýsý çalýþtýrýlacak pc'ye göre düzenlenmeli.
5-)Admin iþlemlerinin gerçekleþtirilmesi için;

kullanýcý adý : admin
Þifre : 12345

6-)Dosya iþlemleri admin panel üzerindedir.
7-)Kullanýcý panelinde menüdeki seçeneklere týkladýðýnýz zaman ürünleri görebilmek için scroll u aþaðý indirmeniz gerekmektedir.
8-)AdminPanel üzerindeki documents e týkladýðýnýz zaman scroll u aþaðý indirmeniz gerekmektedir.
9-)Eðer eklediðiniz ürünün kullanýcýsýný DEFAULT olarak seçerseniz kullanýcý yok olarak ürünü ekler.

One to Many Ýliþkisi : Projedeki onetomany iliþkisi bir ürünün bir kullanýcýsý olabilir ama bir kullanýcý birden fazla ürüne sahip olabilir þeklinde yapýldý.
Many To Many Ýliþkisi : Projedeki manytomany iliþkisi bir ürünün birden fazla kategorisi olabilir ayný þekilde bir kategoride de birden fazla ürün olabilir þeklinde yapýldý.
Kullanýcý Yetkisine Göre Sitenin Çalýþmasý ; Admin olarak giriþ yapýldýðýnda admin panelin gözükmesi , normal kullanýcý giriþ yaptýðýnda ise admin panelin gözükmemesi.

1-)Admin Kullanýcý : Admin Panel üzerinde sitedeki kullanýcýlarýn ve ürünlerin eklenmesi , silinmesi , güncellenmesi ve tekil kaydýn okunmasý gerçekleþtirilebilmektedir. (Tekil kayýt okuma Admin panel üzerindeki kullanýcýlar ve documents kýsmýnda vardýr.)
2-)Normal Kullanýcý : Normal kullanýcý giriþ yaptýðýnda farklý kategorilerdeki ürünleri gözlemleyip kiralama özelliðine ve kiraladýktan sonra iade etme yetkisine sahiptir. Farklý kullanýcýlar tarafýndan kiralanmýþ ürünler de sitede listelenmektedir.